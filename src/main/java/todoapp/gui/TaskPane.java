package todoapp.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import todoapp.Task;

public class TaskPane extends HBox
{
	private static final DateTimeFormatter DATE_FORMAT =
		DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private Task task;
	private Callback callback;

	public TaskPane(Task task)
	{
		super(5);
		this.task = task;

		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(4, 0, 4, 0));

		CheckBox mark = new CheckBox();
		mark.setSelected(task.getDone());

		mark.selectedProperty().addListener(new ChangeListener<Boolean>()
		{
			public void changed(ObservableValue<? extends Boolean> ov,
				Boolean oldValue, Boolean newValue)
			{
				task.setDone(newValue);

				if (callback != null)
				{
					callback.onTaskChanged();
				}
			}
		});

		Label deadline = new Label(task.getDeadline().format(DATE_FORMAT));
		deadline.setMinWidth(Label.USE_PREF_SIZE);
		deadline.setFont(new Font("Verdana", 13));
		deadline.setTextFill(Color.web("#666"));

		Label text = new Label(task.getName());
		text.setFont(new Font("Verdana", 14));

		getChildren().add(mark);
		getChildren().add(deadline);
		getChildren().add(text);
	}

	public void setCallback(Callback cb)
	{
		callback = cb;
	}

	public interface Callback
	{
		void onTaskChanged();
	}
}
