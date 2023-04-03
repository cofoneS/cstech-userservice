package com.cstech.userservice.app.model;

public class CustomResponseModel<T> {
	
    private Boolean success;
    private String msg;
    private T data;
    
	public CustomResponseModel(Boolean success) {
		this.success = success;
	}
	
	public CustomResponseModel(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	
	public CustomResponseModel(Boolean success, String msg, T data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	
	public CustomResponseModel(Boolean success, T data) {
		this.success = success;
		this.data = data;
	}	
	
    public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	

}
