package com.cstech.userservice.utility;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Utility {
	
	public static LocalDateTime epochToLocalDateTime(Long epocTime) {
		if(epocTime != null) {
			return Instant.ofEpochMilli(epocTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
		}else {
			return null;
		}
	}
	
	public static OffsetDateTime epochToOffsetDateTime(Long epocTime) {
		if(epocTime != null) {
			return Instant.ofEpochMilli(epocTime).atZone(ZoneId.systemDefault()).toOffsetDateTime();
		}else {
			return null;
		}
	}
	
	public static Timestamp epochToTimestamp(Long epocTime) {
		if(epocTime != null) {
			return Timestamp.valueOf(Instant.ofEpochMilli(epocTime).atZone(ZoneId.systemDefault()).toLocalDateTime());
		}else {
			return null;
		}
	}
	
	public static Timestamp doTimestamp() {
		return Timestamp.valueOf(OffsetDateTime.now().toLocalDateTime());
	}	

	public static Long doEpocTime(Timestamp time) {
		return (time != null ? time.getTime() : null);
	}	
	
	public static Long doEpocTime(OffsetDateTime datetime) {
		return (datetime != null ? datetime.toEpochSecond() : null);
	}		
	
	/**
	 * Birthdate have to format  YYYYMMDD
	 *
	 * @param dateInStr
	 * @return
	 */
	public static OffsetDateTime doOffsetDateTime(String dateInStr) {
		return (dateInStr != null && dateInStr.length() == 6 ? OffsetDateTime.parse(dateInStr, DateTimeFormatter.BASIC_ISO_DATE) : null);
	}
	
	/**
	 * OffsetDateTime have to format  YYYYMMDD
	 *
	 * @param dateInStr
	 * @return
	 */	
	public static String doOffsetDateTime(OffsetDateTime date) {
		return (date != null ? date.format(DateTimeFormatter.BASIC_ISO_DATE) : null);
	}	
	
	public static String upperCase(String str) {
		return (str != null ? str.toUpperCase() : null);
	}
	
	public final static long epochDateTime() {
		return OffsetDateTime.now().toEpochSecond();
	}
	
	public static boolean checkCriteriaField(String field) {
		return ( field != null && field.length() > 0 ? true : false);
	}	
	
	public static String formatField(String field) {
		return ( field != null ? field.toUpperCase() : field);
	}	
	
	public static String formatLikeField(String field) {
		return ( field != null ? field.toUpperCase().concat("%") : field);
	}	
}
