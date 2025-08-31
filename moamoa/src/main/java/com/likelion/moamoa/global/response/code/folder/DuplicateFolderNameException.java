package com.likelion.moamoa.global.response.code.folder;

import com.likelion.moamoa.global.exception.BaseException;

public class DuplicateFolderNameException extends BaseException {
    public DuplicateFolderNameException() {
        super(FolderErrorCode.FOLDER_DUPLICATE_NAME_409);
    }
}
