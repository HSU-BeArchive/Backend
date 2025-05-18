package com.likelion.moamoa.domain.folder.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.likelion.moamoa.global.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(FolderErrorCode.FOLDER_404_USER_NOT_FOUND);
    }
}
