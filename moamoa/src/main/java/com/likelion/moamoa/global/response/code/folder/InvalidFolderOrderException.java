package com.likelion.moamoa.global.response.code.folder;

import com.likelion.moamoa.global.exception.BaseException;

public class InvalidFolderOrderException extends BaseException {
    public InvalidFolderOrderException() {
        super(FolderErrorCode.FOLDER_INVALID_ORDER_400);
    }
}
