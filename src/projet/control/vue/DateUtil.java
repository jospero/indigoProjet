package projet.control.vue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	private static final String Date_pattern="dd.MM.yy";
	private static final DateTimeFormatter DATE_FORMATTER=DateTimeFormatter.ofPattern(Date_pattern);
	
	public static String format(LocalDate date)
	{
		if(date==null)
		{
			return null;
		}
		
		return DATE_FORMATTER.format(date);
	}
	
	public static LocalDate parse(String dateString)
	{
		try {
			return DATE_FORMATTER.parse(dateString,LocalDate::from);
		} catch (Exception e) {
		 return null;
		}
		
	}
	
	public static boolean validDate(String dateString)
	{
		return DateUtil.parse(dateString) !=null;
	}

}
