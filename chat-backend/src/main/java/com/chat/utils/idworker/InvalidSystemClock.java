package com.chat.utils.idworker;

public class InvalidSystemClock extends RuntimeException {
    public InvalidSystemClock(String message) {
        super(message);
    }
}
