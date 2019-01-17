package com.jack.winter.rest.advice;

import com.jack.winter.rest.response.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
@ResponseBody
public class WebExceptionHandle {
	private static final Logger logger = LoggerFactory.getLogger(WebExceptionHandle.class);
	
	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseObject handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		ResponseObject response= new ResponseObject().setSuccess(false).setStatus(HttpStatus.BAD_REQUEST.value()).setMsg(e.getMessage());
		//打印异常类、出现异常的语句及响应信息
		logger.error("cause: {},stackTrace: {}, response: {}",e.getClass(),e.getStackTrace()[0],response.toString());
		return response;
	}
	/**
	 * 404 - NOT FOUND
	 * 处理404还需在配置文件添加如下两个配置
	 * spring.mvc.throw-exception-if-no-handler-found=true
	 * spring.resources.add-mappings=false
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseObject handleHttpMessageNotFoundException() {
		ResponseObject response= new ResponseObject().setSuccess(false).setStatus(HttpStatus.NOT_FOUND.value()).setMsg("Request url not found");
		logger.error(response.toString());
		return response;
	}
	
	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseObject handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		ResponseObject response= new ResponseObject().setSuccess(false).setStatus(HttpStatus.METHOD_NOT_ALLOWED.value())
				.setMsg(e.getMessage());
		//打印异常类、出现异常的语句及响应信息
		logger.error("cause: {},stackTrace: {}, response: {}",e.getClass(),e.getStackTrace()[0],response.toString());
		return response;
	}

	/**
	 * 415 - Unsupported Media Type
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseObject handleHttpMediaTypeNotSupportedException(Exception e) {
		ResponseObject response=  new ResponseObject().setSuccess(false).setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
				.setMsg(e.getMessage());
		//打印异常类、出现异常的语句及响应信息
		logger.error("cause: {},stackTrace: {}, response: {}",e.getClass(),e.getStackTrace()[0],response.toString());
		return response;
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseObject handleException(Exception e) {
		ResponseObject response=  new ResponseObject().setSuccess(false).setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.setMsg(e.getMessage());
		//打印异常类、出现异常的语句及响应信息
		logger.error("cause: {},stackTrace: {}, response: {}",e.getClass(),e.getStackTrace()[0],response.toString());
		return response;
	}
}
