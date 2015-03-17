package todoapp;

import java.io.File;
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
		 * @return value as a Date instance
		 */
		public Date nextDate()
		{
			long deadline = Long.parseLong(next());
			return new Date(deadline * 1000);
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
			return next() == "1";
		}
	}

	/**
	 * Create a new CSVReader instance
	 */
	public CSVReader(File file) throws Exception
	{
		this.scanner = new Scanner(file);
	}

	/**
	 * @return next line in CSV file as Line instance
	 */
	public Line readLine()
	{
		String line = "";

		while (scanner.hasNextLine())
		{
			line = scanner.nextLine();

			if (!line.startsWith("#"))
			{
				break;
			}
		}

		return new Line(line.split(","));
	}
}
