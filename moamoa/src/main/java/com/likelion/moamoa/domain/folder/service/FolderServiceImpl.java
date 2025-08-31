package com.likelion.moamoa.domain.folder.service;

import com.likelion.moamoa.domain.user.entity.User;
import com.likelion.moamoa.domain.user.repository.UserRepository;
import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.global.response.code.folder.DuplicateFolderNameException;
import com.likelion.moamoa.global.response.code.folder.InvalidFolderOrderException;
import com.likelion.moamoa.global.response.code.folder.NotFoundFolderException;
import com.likelion.moamoa.global.response.code.folder.NotFoundUserException;
import com.likelion.moamoa.domain.folder.repository.FolderRepository;
import com.likelion.moamoa.domain.folder.web.dto.*;
import com.likelion.moamoa.domain.folder.web.dto.FolderSummaryRes.FolderSummary;
import com.likelion.moamoa.domain.reference.entity.Reference;
import com.likelion.moamoa.domain.reference.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        if(folderRepository.existsByFolderNameAndUser_Id(createFolderReq.getFolderName(), userId)) {
            throw new DuplicateFolderNameException();
        }

        Folder folder = Folder.builder()
                .folderName(createFolderReq.getFolderName())
                .folderOrder(folderRepository.countFolderByUser_Id(userId)) // 0번부터 시작
                .user(user)
                .build();

        // Folder Entity DB 저장
        Folder saveFolder = folderRepository.save(folder);

        // Folder Entity -> CreateFolderRes로 변환
        return new CreateFolderRes(
                saveFolder.getUser().getId(),
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

        List<Folder> folders = folderRepository.findAllByUser_Id(userId);
        List<FolderSummary> folderSummaryList = new ArrayList<>();

        for (Folder folder : folders) {
            FolderSummary folderSummary = new FolderSummary(
                    folder.getFolderId(),
                    folder.getFolderName(),
                    folder.getFolderOrder()
            );
            folderSummaryList.add(folderSummary);
        }

        return new FolderSummaryRes(user.getId(), folderSummaryList);
    }

    // 폴더 이름 변경
    @Transactional
    @Override
    public ModifyFolderRes modifyFolderName(
            Long userId,
            Long folderId,
            ModifyFolderReq modifyFolderReq
    ) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        if (!folder.getUser().getId().equals(userId)) {
            throw new NotFoundUserException();
        }

        // 폴더 이름 중복 예외
        if(folderRepository.existsByFolderNameAndUser_Id(modifyFolderReq.getFolderName(), userId)) {
            throw new DuplicateFolderNameException();
        }

        String folderNameBefore = folder.getFolderName();
        folder.setFolderName(modifyFolderReq.getFolderName());

        return new ModifyFolderRes(
                folder.getUser().getId(),
                folder.getFolderId(),
                folderNameBefore,
                folder.getFolderName()
        );
    }

    // 폴더 삭제
    @Override
    @Transactional
    public void deleteOneFolder(Long userId, Long folderId) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        if (!folder.getUser().getId().equals(userId)) {
            throw new NotFoundUserException();
        }

        // 폴더 삭제하면 모두 앞당기기
        Long orderValue = folder.getFolderOrder();
        folderRepository.findAllByUser_Id(userId).stream()
                .filter(f -> f.getFolderOrder() > orderValue)
                .forEach(f -> f.setFolderOrder(f.getFolderOrder() - 1));

        // 폴더 안에 있는 사진들 S3에서 삭제하기
        for (Reference ref : folder.getReferences()) {
            imageService.deleteImageFromS3(ref.getImgUrl());
        }

        folderRepository.delete(folder);
    }

    // 펄더 순서 변경
    @Transactional
    @Override
    public ChangeFolderRes changeFolderOrder(Long userId, Long folderId, ChangeFolderReq changeFolderReq) {
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(NotFoundFolderException::new);

        if (!folder.getUser().getId().equals(userId)) {
            throw new NotFoundUserException();
        }

        Long orderBefore = folder.getFolderOrder();
        Long orderAfter = changeFolderReq.getFolderOrderAfter();

        if (orderAfter < 0 || orderAfter >= folderRepository.countFolderByUser_Id(userId)) {
            throw new InvalidFolderOrderException();
        }

        if (orderBefore < orderAfter) {
            folderRepository.findAllByUser_Id(userId).stream()
                    .filter(f -> f.getFolderOrder() > orderBefore && f.getFolderOrder() <= orderAfter)
                    .forEach(f -> f.setFolderOrder(f.getFolderOrder() - 1));
        } else {
            folderRepository.findAllByUser_Id(userId).stream()
                    .filter(f -> f.getFolderOrder() >= orderAfter && f.getFolderOrder() < orderBefore)
                    .forEach(f -> f.setFolderOrder(f.getFolderOrder() + 1));
        }

        folder.setFolderOrder(orderAfter);

        return new ChangeFolderRes(
                folder.getUser().getId(),
                folder.getFolderId(),
                orderBefore,
                folder.getFolderOrder()
        );
    }
}
