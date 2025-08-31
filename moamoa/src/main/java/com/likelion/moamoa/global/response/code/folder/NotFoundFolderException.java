package com.likelion.moamoa.global.response.code.folder;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundFolderException extends BaseException {
    public NotFoundFolderException() {
        super(FolderErrorCode.FOLDER_NOT_FOUND_404);
    }
}
