package com.likelion.moamoa.domain.folder.service;

import com.likelion.moamoa.domain.auth.entity.User;
import com.likelion.moamoa.domain.auth.repository.UserRepository;
import com.likelion.moamoa.domain.folder.entity.Folder;
import com.likelion.moamoa.domain.folder.exception.DulicateFolderNameException;
import com.likelion.moamoa.domain.folder.exception.UserNotFoundException;
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
                .orElseThrow(UserNotFoundException::new);

        // 1. createFolderReq -> Folder Entity 생성
        // 폴더 중복 예외
        if(folderRepository.existsByFolderNameAndUser_UserId(createFolderReq.getFolderName(), userId)) {
            throw new DulicateFolderNameException();
        }
        Folder folder = Folder.builder()
                .folderName(createFolderReq.getFolderName())
                .folderColor(createFolderReq.getFolderColor())
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
                saveFolder.getFolderColor(),
                saveFolder.getFolderOrder()
        );
    }

    @Override
    @Transactional
    public FolderSummeryRes getAllByFolder(Long userId) {
        // 1. userId -> User Entity 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        // 2. Folder Entity DB 조회
        return new FolderSummeryRes(
                folderRepository.findAllByUser_UserId(userId).stream()
                        .map(folder -> new FolderSummeryRes.FolderSummery(
                                folder.getFolderId(),
                                folder.getFolderName(),
                                folder.getFolderColor(),
                                folder.getFolderOrder()
                        ))
                        .collect(Collectors.toList())
        );
    }


}
