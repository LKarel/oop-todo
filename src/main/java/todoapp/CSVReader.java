package todoapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class CSVReader
{
	private Scanner scanner;

	public class Line
	{
		private int column;
		private String[] columns;

		/**
		 * Create a new Line instance
		 */
		public Line(String[] columns)
		{
			this.columns = columns;
		}

		/**
		 * @return next column in line and increase column value by one
		 */
		private String next()
		{
			return columns[column++];
		}

		/**
		 * @return value as a Integer
		 */
		public int nextInt()
		{
			return Integer.parseInt(next());
		}

		/**
		 * @return value as a LocalDateTime instance
		 */
		public LocalDateTime nextLocalDateTime()
		{
			try
			{
				return LocalDateTime.parse(next(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			}
			catch (DateTimeParseException e)
			{
				return null;
			}
		}

		/**
		 * @return value as a String
		 */
		public String nextString()
		{
			return next();
		}

		/**
		 * @return value as a boolean
		 */
		public boolean nextBoolean()
		{
			return next().equals("1");
		}
	}

	/**
	 * Create a new CSVReader instance
	 */
	public CSVReader(File file) throws FileNotFoundException
	{
		this.scanner = new Scanner(file);
	}

	/**
	 * Test whether it is possible to read next line
	 */
	public boolean canRead()
	{
		return scanner.hasNextLine();
	}

	/**
	 * @return next line in CSV file as Line instance
	 */
	public Line readLine()
	{
		String line = "";

		while (canRead())
		{
			line = scanner.nextLine();

			if (!line.startsWith("#"))
			{
				break;
			}
		}

		return new Line(line.split(","));
	}

	public void close()
	{
		scanner.close();
	}
}
