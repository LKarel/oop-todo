package todoapp.gui;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

			TaskPane pane = new TaskPane(task);
			pane.setCallback(new TaskPane.Callback()
			{
				@Override
				public void onTaskChanged()
				{
					if (task.getDone())
					{
						FadeTransition ft = new FadeTransition(Duration.millis(400), pane);
						ft.setFromValue(1);
						ft.setToValue(0);
						ft.setOnFinished(new EventHandler<ActionEvent>()
						{
							@Override
							public void handle(ActionEvent event)
							{
								notifyDataChanged();
							}
						});

						ft.play();
					}
					else
					{
						notifyDataChanged();
					}
				}
			});

			taskPanes.put(taskId, pane);
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
			if (!task.getDone())
			{
				getChildren().add(getTaskPane(task.getId()));
			}
		}
	}
}
