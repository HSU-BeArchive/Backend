package com.likelion.moamoa.domain.folder.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class DuplicateFolderNameException extends BaseException {
    public DuplicateFolderNameException() {
        super(FolderErrorCode.FOLDER_DUPLICATE_NAME_409);
    }
}
