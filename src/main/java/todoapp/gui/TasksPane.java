package todoapp.gui;

import java.util.HashMap;
import java.util.Map;

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

		for (Task task : tasks)
		{
			getChildren().add(getTaskPane(task.getId()));
		}
	}
}
