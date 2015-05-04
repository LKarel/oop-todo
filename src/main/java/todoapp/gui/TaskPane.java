package todoapp.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;

import todoapp.Task;

public class TaskPane extends HBox
{
	private static final DateTimeFormatter DATE_FORMAT =
		DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm");

	private Task task;

	public TaskPane(Task task)
	{
		super(5);
		this.task = task;

		CheckBox mark = new CheckBox();
		mark.setSelected(task.getDone());

		mark.selectedProperty().addListener(new ChangeListener<Boolean>()
		{
			public void changed(ObservableValue<? extends Boolean> ov,
				Boolean oldValue, Boolean newValue)
			{
				task.setDone(newValue);
			}
		});

		Label deadline = new Label(task.getDeadline().format(DATE_FORMAT));
		deadline.setPrefWidth(120);
		deadline.setMinWidth(120);

		Label text = new Label(task.getName());

		getChildren().add(mark);
		getChildren().add(deadline);
		getChildren().add(text);
	}
}
