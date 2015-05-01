package todoapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class TasksWriter
{
	private File file;

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
	public void write(TaskList tasks)
	{

		String filePath = file.getAbsolutePath();

		file.delete();
		file = new File(filePath);

		try
		{
			PrintWriter printWriter = new PrintWriter(file);

			for (Task task : tasks)
			{
				printWriter.println(taskToCSV(task));
			}

			printWriter.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Invalid file");
			return;
		}
	}

	/**
	 * Returns task instance as a CSV String
	 */
	private String taskToCSV(Task task)
	{
		String deadline = task.getDeadline().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String taskDone = task.getDone() ? "1" : "0";

		return task.getId() + "," + deadline + "," + task.getName() + "," + taskDone;
	}
}
