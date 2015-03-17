package todoapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TasksWriter
{
	private File file;
	private String filename;

	/**
	 * Create a new TasksWriter instance
	 */
	public TasksWriter(String filename)
	{
		this.file = new File(filename);
		this.filename = filename;
	}

	/**
	 * Truncate the file and write all the tasks to the file
	 */
	public void write(TaskList tasks) throws FileNotFoundException
	{
		file.delete();
		file = new File(filename);

		PrintWriter printWriter = new PrintWriter(file);

		for (Task task : tasks)
		{
			printWriter.println(task);
		}

		printWriter.close();
	}
}
