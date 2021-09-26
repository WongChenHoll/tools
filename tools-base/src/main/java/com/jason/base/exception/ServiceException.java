package com.jason.base.exception;

/**
 * @author WangChenHol
 * @date 2021-9-26 12:41
 **/
public class ServiceException extends Exception{

    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
    }
}
