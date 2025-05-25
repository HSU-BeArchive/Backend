package com.likelion.moamoa.domain.folder.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class InvalidFolderOrderException extends BaseException {
    public InvalidFolderOrderException() {
        super(FolderErrorCode.FOLDER_INVALID_ORDER_400);
    }
}
