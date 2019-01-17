package com.jack.winter.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *     用于响应请求
 * @author lilongjie
 *
 */
public class ResponseObject {
	//标记请求处理是否成功
	@JsonInclude(Include.NON_NULL)
	private boolean success;
	//处理结果信息
	private String msg;
	//http状态码
	private int status;
	//请求处理结果集
	private Object data;
	public boolean isSuccess() {
		return success;
	}
	public ResponseObject setSuccess(boolean success) {
		this.success = success;
		return this;
	}
	public String getMsg() {
		return msg;
	}
	public ResponseObject setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public Object getData() {
		return data;
	}
	public ResponseObject setData(Object data) {
		this.data = data;
		return this;
	}
	public ResponseObject(boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	public ResponseObject(boolean success) {
		super();
		this.success = success;
	}
	
	public int getStatus() {
		return status;
	}
	public ResponseObject setStatus(int status) {
		this.status = status;
		return this;
	}
	@Override
	public String toString() {
		return "ResponseObject [success=" + success + ", msg=" + msg + ", status=" + status + ", data=" + data + "]";
	}
	public ResponseObject(boolean success, String msg, int status, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.status = status;
		this.data = data;
	}
	public ResponseObject() {
		super();
	}
}
