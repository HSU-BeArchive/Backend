package com.likelion.moamoa.domain.folder.service;

import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.auth.repository.UserRepository;
import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.folder.exception.DuplicateFolderNameException;
import com.likelion.moamoa.domain.folder.exception.NotFoundFolderException;
import com.likelion.moamoa.domain.folder.exception.NotFoundUserException;
import com.likelion.moamoa.domain.folder.repository.FolderRepository;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderReq;
import com.likelion.moamoa.domain.folder.web.dto.CreateFolderRes;
import com.likelion.moamoa.domain.folder.web.dto.FolderSummaryRes;
import com.likelion.moamoa.domain.folder.web.dto.FolderSummaryRes.FolderSummary;
import com.likelion.moamoa.domain.reference.entity.Reference;
import com.likelion.moamoa.domain.reference.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;

    private final ImageService imageService;

    // 폴더 생성
    @Override
    public CreateFolderRes createFolder(Long userId, CreateFolderReq createFolderReq) {
        // userId -> User Entity 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        // 폴더 중복 예외
        if(folderRepository.existsByFolderNameAndUser_UserId(createFolderReq.getFolderName(), userId)) {
            throw new DuplicateFolderNameException();
        }

        Folder folder = Folder.builder()
                .folderName(createFolderReq.getFolderName())
                .folderOrder(folderRepository.countFolderByUser_UserId(userId)) // 0번부터 시작
                .user(user)
                .build();

        // Folder Entity DB 저장
        Folder saveFolder = folderRepository.save(folder);

        // Folder Entity -> CreateFolderRes로 변환
        return new CreateFolderRes(
                saveFolder.getUser().getUserId(),
                saveFolder.getFolderId(),
                saveFolder.getFolderName(),
                saveFolder.getFolderOrder()
        );
    }

    // 폴더 전체 조회
    @Override
    public FolderSummaryRes getAllByFolder(Long userId) {
        // userId -> User 확인
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        List<Folder> folders = folderRepository.findAllByUser_UserId(userId);
        List<FolderSummary> folderSummaryList = new ArrayList<>();

        for (Folder folder : folders) {
            FolderSummary folderSummary = new FolderSummary(
                    folder.getFolderId(),
                    folder.getFolderName(),
                    folder.getFolderOrder()
            );
            folderSummaryList.add(folderSummary);
        }

        return new FolderSummaryRes(user.getUserId(), folderSummaryList);
    }

    // 폴더 삭제
    @Override
    @Transactional
    public void deleteOneFolder(Long userId, Long folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        if (!folder.getUser().getUserId().equals(userId)) {
            throw new NotFoundUserException();
        }

        // 폴더 삭제하면 모두 앞당기기
        Long orderValue = folder.getFolderOrder();
        folderRepository.findAllByUser_UserId(userId).stream()
                .filter(f -> f.getFolderOrder() > orderValue)
                .forEach(f -> f.setFolderOrder(f.getFolderOrder() - 1));

        // 폴더 안에 있는 사진들 S3에서 삭제하기
        for (Reference ref : folder.getReferences()) {
            imageService.deleteImageFromS3(ref.getImgUrl());
        }

        folderRepository.delete(folder);
    }
}
