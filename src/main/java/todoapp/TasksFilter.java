package todoapp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.BitSet;

public class TasksFilter
{
	private TaskList source = new TaskList();

	private BitSet byDone = new BitSet();
	private ArrayList<Integer> byDates = new ArrayList<Integer>();

	/**
	 * Create a new TasksFilter for a set of tasks.
	 */
	public TasksFilter(TaskList tasks)
	{
		source = tasks;
	}

	/**
	 * Select all tasks that are due on the specified date
	 */
	public void byDate(LocalDateTime date)
	{
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();

		byDates.add(year * 10000 + month * 100 + day);
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
			if (!byDates.isEmpty() && !byDates.contains(task.getNumericDate()))
			{
				continue;
			}

			// The `done` restriction has been set
			if (!byDone.isEmpty())
			{
				if (!byDone.get(task.getDone() ? 1 : 0))
				{
					// The task's `done` value does not match what was required
					continue;
				}
			}

			out.add(task);
		}

		return out;
	}
}
