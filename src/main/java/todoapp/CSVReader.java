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

		public Line(String[] columns)
		{
			this.columns = columns;
		}

		private String next()
		{
			return columns[column++];
		}

		public int nextInt()
		{
			return Integer.parseInt(next());
		}

		public Date nextDate()
		{
			long deadline = Long.parseLong(next());
			return new Date(deadline * 1000);
		}

		public String nextString()
		{
			return next();
		}

		public boolean nextBoolean()
		{
			return next() == "1";
		}
	}

	public CSVReader(File file) throws Exception
	{
		this.scanner = new Scanner(file);
	}

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
