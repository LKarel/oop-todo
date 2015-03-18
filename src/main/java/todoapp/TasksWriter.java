package todoapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TasksWriter
{
	private static File file = null;

	/**
	 * Create a new TasksWriter instance
	 */
	public TasksWriter(File file)
	{
		this.file = file;
	}

	/**
	 * Truncate the file and write all the tasks to the file
	 */
	public static void write(TaskList tasks)
	{
		if (file == null)
		{
			file = TasksReader.getDataFile();
		}

		String filePath = file.getAbsolutePath();

		file.delete();
		file = new File(filePath);

		try
		{
			PrintWriter printWriter = new PrintWriter(file);

			for (Task task : tasks)
			{
				printWriter.println(task.toCSV());
			}

			printWriter.close();
		}
		catch (FileNotFoundException e)
		{
		}
	}
}
