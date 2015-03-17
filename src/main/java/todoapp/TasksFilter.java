package todoapp;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;

public class TasksFilter
{
	private TaskList source = new TaskList();

	private BitSet byDone = new BitSet();
	private ArrayList<Date> byDates = new ArrayList<Date>();
	private ArrayList<String> byKeywords = new ArrayList<String>();

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
	public void byDate(Date date)
	{
		// @todo make the date more generic, store as int
		byDates.add(date);
	}

	/**
	 * Select tasks that match this keyword
	 */
	public void byKeyword(String keyword)
	{
		byKeywords.add(keyword.toLowerCase());
	}

	/**
	 * Select tasks based on their done/not done status.
	 */
	public void byDone(boolean done)
	{
		byDone.set(done ? 1 : 0);
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
			if (!byDone.isEmpty())
			{
				if (!byDone.get(task.getDone() ? 1 : 0))
				{
					// The task's `done` value does not match what was required
					continue;
				}
			}

			boolean valid = true;

			for (String keyword : byKeywords)
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
