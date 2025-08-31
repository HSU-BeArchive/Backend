package com.likelion.moamoa.global.response.code.folder;

import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundUserException extends BaseException {
    public NotFoundUserException() {
        super(FolderErrorCode.FOLDER_NOT_FOUND_USER_404);
    }
}
