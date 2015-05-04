package todoapp.gui;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import todoapp.Task;
import todoapp.TaskList;

public class TasksPane extends VBox
{
	private TaskList tasks;
	private Map<Integer, TaskPane> taskPanes = new HashMap<Integer, TaskPane>();

	public TasksPane(TaskList tasks)
	{
		super(10);
		this.tasks = tasks;

		setPadding(new Insets(5, 10, 5, 10));
		insertItems();
	}

	private TaskPane getTaskPane(int taskId)
	{
		if (!taskPanes.containsKey(taskId))
		{
			Task task = tasks.findById(taskId);

			if (task == null)
			{
				throw new IllegalArgumentException("Invalid task ID passed");
			}

			taskPanes.put(taskId, new TaskPane(task));
		}

		return taskPanes.get(taskId);
	}

	public void notifyDataChanged()
	{
		insertItems();
	}

	private void insertItems()
	{
		getChildren().clear();

		Collections.sort(tasks, new Comparator<Task>()
		{
			@Override
			public int compare(Task a, Task b)
			{
				return a.getDeadline().compareTo(b.getDeadline());
			}
		});

		for (Task task : tasks)
		{
			getChildren().add(getTaskPane(task.getId()));
		}
	}
}
