import java.io.File;
import java.util.Date;

public class CSVReader
{
	private File file;

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

	public CSVReader(File file)
	{
		this.file = file;
	}

	/**
	 * @return True if file exists, else false. 
	 */
	public boolean isValid()
	{
		return file.exists();
	}
}
