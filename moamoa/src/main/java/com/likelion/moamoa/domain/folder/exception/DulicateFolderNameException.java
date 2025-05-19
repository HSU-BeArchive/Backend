package com.likelion.moamoa.domain.folder.exception;

import com.likelion.moamoa.global.exception.BaseException;

public class DulicateFolderNameException extends BaseException {
    public DulicateFolderNameException() {
        super(FolderErrorCode.FOLDER_DUPLICATE_409);
    }
}
