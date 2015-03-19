package todoapp;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateParser
{
	private static final List<DateTimeFormatter> formats;

	static {
		formats = new ArrayList<DateTimeFormatter>();
		formats.add(DateTimeFormatter.ISO_LOCAL_DATE);
		formats.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		formats.add(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	public static LocalDateTime parse(String str)
	{
		LocalDateTime datetime = null;

		try
		{
			datetime = parseRelative(str);
		}
		catch (IllegalArgumentException e)
		{
		}

		if (datetime == null)
		{
			for (DateTimeFormatter format : formats)
			{
				try
				{
					datetime = LocalDateTime.parse(str, format);
					break;
				}
				catch (DateTimeParseException e)
				{
				}
			}
		}

		return datetime;
	}

	/**
	 * Parse a relative time.
	 */
	public static LocalDateTime parseRelative(String str)
		throws IllegalArgumentException
	{
		String[] split = str.split(" ");
		LocalDateTime datetime = LocalDateTime.now();

		datetime = datetime.minusHours(datetime.getHour());
		datetime = datetime.minusMinutes(datetime.getMinute());
		datetime = datetime.minusSeconds(datetime.getSecond());

		switch (split[0])
		{
			case "today": datetime = datetime.plusDays(0); break;
			case "tomorrow": datetime = datetime.plusDays(1); break;
			default: throw new IllegalArgumentException();
		}

		if (split.length == 2)
		{
			LocalTime time;

			try
			{
				time = LocalTime.parse(split[1]);
			}
			catch (DateTimeParseException e)
			{
				throw new IllegalArgumentException();
			}

			datetime = datetime.plusHours(time.getHour());
			datetime = datetime.plusMinutes(time.getMinute());
		}

		return datetime;
	}
}
