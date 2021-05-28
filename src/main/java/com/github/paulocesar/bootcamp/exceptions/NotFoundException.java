package com.github.paulocesar.bootcamp.exceptions;

import com.github.paulocesar.bootcamp.util.MessageUtils;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {

        super(MessageUtils.NO_RECORDS_FOUND);
    }
}
