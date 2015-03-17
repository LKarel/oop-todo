package todoapp;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;

public class TasksFilter
{
	private TaskList source = new TaskList();

	private BitSet done = new BitSet();
	private ArrayList<Date> dates = new ArrayList<Date>();
	private ArrayList<String> keywords = new ArrayList<String>();

	/**
	 * Create a new TasksFilter for a set of tasks.
	 */
	public TasksFilter(TaskList source)
	{
		for (Task task : source)
		{
			this.source.add(task);
		}
	}

	/**
	 * Select all tasks that are due on the specified date
	 */
	public void date(Date date)
	{
		// @todo make the date more generic, store as int
		dates.add(date);
	}

	/**
	 * Select tasks that match this keyword
	 */
	public void keyword(String keyword)
	{
		keywords.add(keyword.toLowerCase());
	}

	/**
	 * Select tasks based on their done/not done status.
	 */
	public void done(boolean done)
	{
		this.done.set(done ? 1 : 0);
	}

	/**
	 * Return all tasks that match the filter constraints.
	 */
	public TaskList getTasks()
	{
		TaskList out = new TaskList();

		for (Task task : source)
		{
			// @todo compare dates here

			// The `done` restriction has been set
			if (!done.isEmpty())
			{
				if (!done.get(task.getDone() ? 1 : 0))
				{
					// The task's `done` value does not match what was required
					continue;
				}
			}

			boolean valid = true;

			for (String keyword : keywords)
			{
				if (!task.getName().toLowerCase().contains(keyword))
				{
					valid = false;
					break;
				}
			}

			if (!valid)
			{
				continue;
			}

			out.add(task);
		}

		return out;
	}
}
