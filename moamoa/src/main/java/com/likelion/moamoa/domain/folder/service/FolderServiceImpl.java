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
import com.likelion.moamoa.domain.folder.web.dto.FolderSummeryRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {
    private final FolderRepository folderRepository;
    private final UserRepository userRepository;

    // 폴더 생성
    @Override
    public CreateFolderRes createFolder(Long userId, CreateFolderReq createFolderReq) {
        // 0. userId -> User Entity 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        // 1. createFolderReq -> Folder Entity 생성
        // 폴더 중복 예외
        if(folderRepository.existsByFolderNameAndUser_UserId(createFolderReq.getFolderName(), userId)) {
            throw new DuplicateFolderNameException();
        }
        Folder folder = Folder.builder()
                .folderName(createFolderReq.getFolderName())
                .folderOrder(folderRepository.count()) // 0번부터 시작
                .user(user)
                .build();

        // 2. Folder Entity DB 저장
        Folder saveFolder = folderRepository.save(folder);

        // 3. Folder Entity -> CreateFolderRes로 변환
        return new CreateFolderRes(
                saveFolder.getUser().getUserId(),
                saveFolder.getFolderId(),
                saveFolder.getFolderName(),
                saveFolder.getFolderOrder()
        );
    }

    // 폴더 전체 조회
    @Override
    @Transactional
    public FolderSummeryRes getAllByFolder(Long userId) {
        // 1. userId -> User Entity 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
        // 2. Folder Entity DB 조회
        return new FolderSummeryRes(
                folderRepository.findAllByUser_UserId(userId).stream()
                        .map(folder -> new FolderSummeryRes.FolderSummery(
                                folder.getFolderId(),
                                folder.getFolderName(),
                                folder.getFolderOrder()
                        ))
                        .collect(Collectors.toList())
        );
    }

    // 폴더 삭제
    @Override
    public void deleteOneFolder(Long userId, Long folderid) {
        Folder folder = folderRepository.findById(folderid)
                .orElseThrow(NotFoundFolderException::new);

        if (!folder.getUser().getUserId().equals(userId)) {
            throw new NotFoundUserException();
        }

        // 폴더 삭제하면 모두 앞당기기
        long orderValue = folder.getFolderOrder();
        folderRepository.findAllByUser_UserId(userId).stream()
                .filter(f -> f.getFolderOrder() > orderValue)
                .forEach(f -> f.setFolderOrder(f.getFolderOrder() - 1));

        folderRepository.delete(folder);
    }

}
