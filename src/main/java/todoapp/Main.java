package todoapp;

public class Main
{
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

		switch (args[0])
		{
			case "list":
				System.out.println("TODO: List tasks and apply filters");
				break;

			case "done":
				if (args.length != 2)
				{
					printUsage();
					return;
				}

				System.out.println("TODO: Mark task as done");
				break;

			case "undo":
				if (args.length != 2)
				{
					printUsage();
					return;
				}

				System.out.println("TODO: Mark task as not done");
				break;
		}

		// @todo: write tasks
	}

	public static void printUsage()
	{
		System.out.println(String.join("\n", Main.usage));
	}
}

