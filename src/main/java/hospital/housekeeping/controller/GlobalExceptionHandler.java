package hospital.housekeeping.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	private enum LogStatus {
		STACK_TRACE,
		MESSAGE_ONLY
	}
	
	
	@Data
	private class ExceptionMessage{
		private String message;
		private String statusReason;
		private int statusCode;
		private String timeStamp;
		private String uri;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code= HttpStatus.CONFLICT)
	public ExceptionMessage handleException(
			Exception ex, WebRequest webRequest) {
		return buildExceptionMessage(ex, webRequest, HttpStatus.CONFLICT, LogStatus.STACK_TRACE);
	}


	private ExceptionMessage buildExceptionMessage(Exception ex, WebRequest webRequest, HttpStatus status,
			LogStatus logStatus) {
		String message = ex.toString();
		String statusReason = status.getReasonPhrase();
		int statusCode = status.value();
		String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		String uri = null;
		
		if (webRequest instanceof ServletWebRequest swr) {
			uri = swr.getRequest().getRequestURI();
		}
		
		if(logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", ex.toString());
		} else {
			log.error("Exception: {}", ex);
		}
		
		ExceptionMessage em = new ExceptionMessage();
		em.setMessage(message);
		em.setStatusCode(statusCode);
		em.setStatusReason(statusReason);
		em.setTimeStamp(timeStamp);
		em.setUri(uri);
		
		return em;
	}
	
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code= HttpStatus.NOT_FOUND)
	public ExceptionMessage handleNoSuchElementException(NoSuchElementException ex, WebRequest webRequest) {
		return buildNoSuchExceptionMessage(ex, webRequest, HttpStatus.NOT_FOUND, LogStatus.MESSAGE_ONLY);
	}


	private ExceptionMessage buildNoSuchExceptionMessage(NoSuchElementException ex, WebRequest webRequest,
			HttpStatus status, LogStatus logStatus) {
		String message = ex.toString();
		String statusReason = status.getReasonPhrase();
		int statusCode = status.value();
		String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		String uri = null;
		
		if (webRequest instanceof ServletWebRequest swr) {
			uri = swr.getRequest().getRequestURI();
		}
		
		if(logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", ex.toString());
		} else {
			log.error("Exception: {}", ex);
		}
		
		ExceptionMessage em = new ExceptionMessage();
		em.setMessage(message);
		em.setStatusCode(statusCode);
		em.setStatusReason(statusReason);
		em.setTimeStamp(timeStamp);
		em.setUri(uri);
		
		return em;
	}



	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ExceptionMessage handleIllegalArgumentException(IllegalArgumentException ex, WebRequest webRequest) {
		return buildIllegalExceptionMessage(ex, webRequest, HttpStatus.FORBIDDEN, LogStatus.MESSAGE_ONLY);
	}


	private ExceptionMessage buildIllegalExceptionMessage(IllegalArgumentException ex, WebRequest webRequest,
			HttpStatus status, LogStatus logStatus) {
		String message = ex.toString();
		String statusReason = status.getReasonPhrase();
		int statusCode = status.value();
		String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		String uri = null;
		
		if (webRequest instanceof ServletWebRequest swr) {
			uri = swr.getRequest().getRequestURI();
		}
		
		if(logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", ex.toString());
		} else {
			log.error("Exception: {}", ex);
		}
		
		ExceptionMessage em = new ExceptionMessage();
		em.setMessage(message);
		em.setStatusCode(statusCode);
		em.setStatusReason(statusReason);
		em.setTimeStamp(timeStamp);
		em.setUri(uri);
		
		return em;
	}

	
	@ExceptionHandler(InvalidDefinitionException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ExceptionMessage handleInvalidDefinitionExcepiton(InvalidDefinitionException ex, WebRequest webRequest) {
		return buildInvalidDefExceptionMessage(ex, webRequest, HttpStatus.FORBIDDEN, LogStatus.MESSAGE_ONLY);
	}
	
	private ExceptionMessage buildInvalidDefExceptionMessage(InvalidDefinitionException ex, WebRequest webRequest,
			HttpStatus status, LogStatus logStatus) {
		String message = ex.toString();
		String statusReason = status.getReasonPhrase();
		int statusCode = status.value();
		String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		String uri = null;
		
		if (webRequest instanceof ServletWebRequest swr) {
			uri = swr.getRequest().getRequestURI();
		}
		
		if(logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", ex.toString());
		} else {
			log.error("Exception: {}", ex);
		}
		
		ExceptionMessage em = new ExceptionMessage();
		em.setMessage(message);
		em.setStatusCode(statusCode);
		em.setStatusReason(statusReason);
		em.setTimeStamp(timeStamp);
		em.setUri(uri);
		
		return em;
	}
	
}




