package todoapp;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

public class Main
{
	private static class IllegalUsageException extends Exception
	{
		public final String message;

		public IllegalUsageException()
		{
			this(null);
		}

		public IllegalUsageException(String message)
		{
			super();
			this.message = message;
		}
	}

	private static final String[] usage = {
		"[VERB] [OPTIONS]",
		"",
		"list [FILTER ..]",
		"  list all tasks (default)",
		"  Optional keywords can be specified for filtering the tasks. The default is to list all tasks not marked as done",
		"  Special keywords include: done, todo, today, tomorrow, yesterday. You can also filter by dates",
		"",
		"done ID",
		"  Mark task with the specified ID as done",
		"",
		"undo ID",
		"  Mark task with the specified ID as not done",
		"",
		"add 'dd-MM-yyyy hh:mm' 'name'",
		"  Add task with the specified deadline and name to the list"
	};

	private static File tasksFile = TasksReader.getDataFile();
	private static TasksReader tasksReader = new TasksReader(tasksFile);
	private static TaskList tasks = tasksReader.getTasks();

	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			printUsage();
			return;
		}

		String verb = args[0];
		String[] argsLeft = Arrays.copyOfRange(args, 1, args.length);

		try
		{
			if (verb.equals("list"))
			{
				list(argsLeft);
			}
			else if (verb.equals("done") || verb.equals("undo"))
			{
				mark(argsLeft, verb.equals("done"));
			}
			else if (verb.equals("add"))
			{
				add(argsLeft);
			}
			else
			{
				throw new IllegalUsageException();
			}
		}
		catch (IllegalUsageException e)
		{
			if (e.message != null)
			{
				System.out.println(e.message);
			}
			else
			{
				printUsage();
			}
		}

		TasksWriter tasksWriter = new TasksWriter(tasksFile);
		tasksWriter.write(tasks);
	}

	/**
	 * Print usage information for this program.
	 */
	public static void printUsage()
	{
		System.out.println(String.join("\n", Main.usage));
		System.out.println(String.format("\nThe data file is stored at %s",
				TasksReader.getDataFile().getAbsolutePath()));
	}

	/**
	 * Handle `list` verb.
	 */
	private static void list(String[] args)
	{
		for (Task task : tasks)
		{
			System.out.println(task);
		}
	}

	/**
	 * Handle `done` and `undo` verbs
	 */
	private static void mark(String[] args, boolean done) throws IllegalUsageException
	{
		if (args.length != 1)
		{
			throw new IllegalUsageException();
		}

		int taskId = Integer.parseInt(args[0]);
		Task task = tasks.findById(taskId);

		if (task == null)
		{
			throw new IllegalUsageException("No task with such ID in the list");
		}

		task.setDone(done);
		System.out.println("Marked task '" + task.getName() +"' as " + (done ? "done" : "not done"));
	}

	/**
	 * Handle `add` verb
	 */
	private static void add(String[] args) throws IllegalUsageException
	{
		if (args.length < 2)
		{
			throw new IllegalUsageException();
		}

		LocalDateTime deadline = DateParser.parse(args[0]);

		if (deadline == null)
		{
			throw new IllegalUsageException("Invalid date format");
		}

		String taskName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
		Task newTask = new Task(taskName, deadline);
		tasks.add(newTask);

		System.out.println("Added task '" + taskName + "' with deadline " + deadline + " to the list");
	}
}
