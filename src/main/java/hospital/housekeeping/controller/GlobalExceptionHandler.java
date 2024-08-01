package hospital.housekeeping.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

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
}
