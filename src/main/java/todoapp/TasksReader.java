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
	 * @Return TaskList of all the tasks in the file
	 */
	public TaskList getTasks() throws FileNotFoundException
	{
		CSVReader reader = new CSVReader(file);
		TaskList tasks = new TaskList();

		while (reader.canRead())
		{
			CSVReader.Line line = reader.readLine();

			int id = line.nextInt();
			Date deadline = line.nextDate();
			String name = line.nextString();
			boolean done = line.nextBoolean();

			Task task = new Task(id, name,deadline, done);
			tasks.add(task);
		}

		return tasks;
	}

}
