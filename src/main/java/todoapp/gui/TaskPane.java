package todoapp.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import todoapp.Task;

public class TaskPane extends VBox
{
	private Task task;

	public TaskPane(Task task)
	{
		super(10);
		this.task = task;

		getChildren().add(new Label(task.toString()));
	}
}
