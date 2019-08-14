package es.vqs.joinfacesmap.utils;

import java.util.Calendar;

public class CalendarUtils {

	public static Calendar getCalendarFirstDayWeek(Integer week, Integer year) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		return cal;
	}

	public static Calendar getCalendarLastDayWeek(Integer week, Integer year) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		return cal;
	}
}
