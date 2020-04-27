package com.mp.common.exception;

/**
 * @author tuchuan
 * @description
 * @date 2019/3/14 17:57
 */
public class DaoException extends RuntimeException{

    public DaoException() {
        super();
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}