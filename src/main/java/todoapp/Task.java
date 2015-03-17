package todoapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task
{
	private static int maxId = 0;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

	private int id;
	private Date deadline;
	private String name;
	private boolean done;

	/**
	 * Create a new Task instance
	 */
	public Task(int id, String name, Date deadline, boolean done)
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
	public Task(String name, Date deadline)
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
	 * @return deadline as Date.
	 */
	public Date getDeadline()
	{
		return deadline;
	}

	/**
	 * Set deadline.
	 *
	 * @param deadline the value to set.
	 */
	public void setDeadline(Date deadline)
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
		String done = this.done ? "\u2713" : "\u2717";
		String deadline = Task.dateFormat.format(this.deadline);

		return String.format("%-2d %s %s %s", id, deadline, done, name);
	}
}
