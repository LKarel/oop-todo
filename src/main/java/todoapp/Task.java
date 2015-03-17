package todoapp;

public class Task
{
	private static int maxId = 0;

	private int id;
	private long deadline;
	private String name;
	private boolean done;

	/**
	 * Create a new Task instance
	 */
	public Task(int id, String name, long deadline, boolean done)
	{
		this.id = id;
		this.name = name;
		this.deadline = deadline;
		this.done = done;

		if (id > Task.maxId)
		{
			Task.maxId = id;
		}
	}

	/**
	 * Create a new Task instance and auto-generate the ID.
	 */
	public Task(String name, long deadline)
	{
		this(Task.maxId + 1, name, deadline, false);
	}

	/**
	 * Get id.
	 *
	 * @return id as int.
	 */
	public int getId()
	{
	    return id;
	}

	/**
	 * Get deadline.
	 *
	 * @return deadline as long.
	 */
	public long getDeadline()
	{
		return deadline;
	}

	/**
	 * Set deadline.
	 *
	 * @param deadline the value to set.
	 */
	public void setDeadline(long deadline)
	{
		this.deadline = deadline;
	}

	/**
	 * Get name.
	 *
	 * @return name as String.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set name.
	 *
	 * @param name the value to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Get done.
	 *
	 * @return done as boolean.
	 */
	public boolean getDone()
	{
		return done;
	}

	/**
	 * Set done.
	 *
	 * @param done the value to set.
	 */
	public void setDone(boolean done)
	{
		this.done = done;
	}

	public String toString()
	{
		String done = this.done ? "x" : " ";

		// @todo convert timestamp to ISO8601 date
		return String.format("[%s] %2d %d %s", done, id, deadline, name);
	}
}
