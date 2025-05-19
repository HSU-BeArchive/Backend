package com.likelion.moamoa.domain.folder.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundFolderException extends BaseException {
    public NotFoundFolderException() {
        super(FolderErrorCode.FOLDER_NOT_FOUND_404);
    }
}
