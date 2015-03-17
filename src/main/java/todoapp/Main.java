package todoapp;

import java.util.Arrays;

public class Main
{
	private static class IllegalUsageException extends Exception
	{
		public IllegalUsageException()
		{
			super();
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
		"  Mark task with the specified ID as not done"
	};

	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			printUsage();
			return;
		}

		// @todo: load all tasks from tasks.csv

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
			else
			{
				throw new IllegalUsageException();
			}
		}
		catch (IllegalUsageException e)
		{
			printUsage();
		}

		// @todo: write tasks
	}

	/**
	 * Print usage information for this program.
	 */
	public static void printUsage()
	{
		System.out.println(String.join("\n", Main.usage));
	}

	/**
	 * Handle `list` verb.
	 */
	private static void list(String[] args)
	{
		System.out.println("TODO: List tasks and apply filters");
	}

	/**
	 * Handle `done` and `undo` verbs
	 */
	private static void mark(String[] args, boolean done)
		throws IllegalUsageException
	{
		if (args.length != 1)
		{
			throw new IllegalUsageException();
		}

		System.out.println("TODO: Mark task as " + (done ? "done" : "not done"));
	}
}

