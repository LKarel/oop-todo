package todoapp;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task>
{
	/**
	 * Constructor.
	 */
	public TaskList()
	{
		super();
	}

	/**
	 * Return a shallow copy of this TaskList instance.
	 */
	@Override
	public TaskList clone()
	{
		TaskList copy = new TaskList();

		for (Task task : this)
		{
			copy.add(task);
		}

		return copy;
	}

	/**
	 * Find a task by id
	 *
	 * @param int id
	 * @return Task
	 */
	public Task findById(int id)
	{
		for (Task task : this)
		{
			if (task.getId() == id)
			{
				return task;
			}
		}

		return null;
	}
}
