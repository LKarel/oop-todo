package todoapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class TasksReader
{
	private File file;

	/**
	 * Create a new TasksReader instance
	 */
	public TasksReader(File file)
	{
		this.file = file;
	}

	/**
	 * @return TaskList of all the tasks in the file
	 */
	public TaskList getTasks()
	{
		TaskList tasks = new TaskList();

		try
		{
			CSVReader reader = new CSVReader(file);

			while (reader.canRead())
			{
				CSVReader.Line line = reader.readLine();

				int id = line.nextInt();
				Date deadline = line.nextDate();
				String name = line.nextString();
				boolean done = line.nextBoolean();

				tasks.add(new Task(id, name, deadline, done));
			}
		}
		catch (FileNotFoundException e)
		{
		}

		return tasks;
	}

	/**
	 * Returns a File instance pointing to the data file.
	 */
	public static File getDataFile()
	{
		File ret = null;

		try
		{
			ret = new File(System.getProperty("user.home"), "todo.csv");
		}
		catch (SecurityException e)
		{
		}

		if (ret == null)
		{
			ret = new File("todo.csv");
		}

		return ret;
	}
}
