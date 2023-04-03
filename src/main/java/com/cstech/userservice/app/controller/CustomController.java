package com.cstech.userservice.app.controller;

import com.cstech.userservice.app.model.CustomResponseModel;

public class CustomController {
	
    protected CustomResponseModel doResponseOk() {
        return doResponse(true, null);
    }
    
    protected <T> CustomResponseModel<T> doResponseOk(T data) {
        return doResponse(true, data);
    }    
    
    protected <T> CustomResponseModel<T> doResponseOk(String msg, T data) {
        return new CustomResponseModel(true, msg, data);
    }    

    protected <T> CustomResponseModel<T> doResponseKo() {
        return doResponse(false, null);
    }
    
    protected <T> CustomResponseModel<T> doResponseKo(T data) {
        return doResponse(false, data);
    }

    protected <T> CustomResponseModel<T> doResponseKo(String msg) {
        return doResponse(false, msg, null);
    }

    protected CustomResponseModel<Void> doResponse(boolean success) {
        return doResponse(success, null);
    }
    
    protected <T> CustomResponseModel<T> doResponse(T data) {
        return new CustomResponseModel(true, data);
    }    
    
    protected <T> CustomResponseModel<T> doResponse(boolean success, T data) {
        return new CustomResponseModel(success, data);
    }
    
    protected <T> CustomResponseModel<T> doResponse(boolean success, String msg, T data) {
        return new CustomResponseModel(success, msg, data);
    }
    
}
