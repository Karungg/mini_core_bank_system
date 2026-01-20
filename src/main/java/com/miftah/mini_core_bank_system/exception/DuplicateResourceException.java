package com.miftah.mini_core_bank_system.exception;

import lombok.Getter;

@Getter
public class DuplicateResourceException extends RuntimeException {

    private final String field;
    private final String messageKey;

    public DuplicateResourceException(String field, String messageKey) {
        super();
        this.field = field;
        this.messageKey = messageKey;
    }
}
