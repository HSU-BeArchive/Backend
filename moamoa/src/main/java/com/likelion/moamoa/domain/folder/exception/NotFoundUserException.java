package com.likelion.moamoa.domain.folder.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.likelion.moamoa.global.exception.BaseException;

public class NotFoundUserException extends BaseException {
    public NotFoundUserException() {
        super(FolderErrorCode.FOLDER_USER_NOT_FOUND_404);
    }
}
