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
