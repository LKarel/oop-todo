package todoapp.gui;

import java.io.File;
import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import todoapp.DateParser;
import todoapp.Task;
import todoapp.TaskList;
import todoapp.TasksReader;
import todoapp.TasksWriter;

public class GUI extends Application
{
	private static TaskList tasks;

	private static void readTasks()
	{
		File tasksFile = TasksReader.getDataFile();
		TasksReader tasksReader = new TasksReader(tasksFile);
		tasks = tasksReader.getTasks();
	}

	@Override
	public void start(Stage primaryStage)
	{
		readTasks();

		primaryStage.setTitle("Todo app");
		primaryStage.setMinWidth(300);

		VBox root = new VBox();
		TasksPane tasksPane = new TasksPane(tasks);

		HBox inputBar = new HBox(5);
		inputBar.setPadding(new Insets(10));

		boolean dateOk = false;

		LabeledTextField dateField = new LabeledTextField("Deadline");
		dateField.setPrefWidth(110);
		dateField.setMinWidth(LabeledTextField.USE_PREF_SIZE);

		LabeledTextField nameField = new LabeledTextField("What to do?");
		nameField.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(nameField, Priority.ALWAYS);

		dateField.valueProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				dateField.getStyleClass().removeAll("textField-error");

				if (newValue.length() > 0 && DateParser.parse(newValue) == null)
				{
					dateField.getStyleClass().add("textField-error");
				}
			}
		});

		dateField.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent key)
			{
				if (key.getCode().equals(KeyCode.ENTER))
				{
					nameField.requestFocus();
				}
			}
		});

		nameField.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			@Override
			public void handle(KeyEvent key)
			{
				if (key.getCode().equals(KeyCode.ENTER))
				{
					LocalDateTime deadline = DateParser.parse(dateField.getText());

					if (deadline != null)
					{
						tasks.add(new Task(nameField.getText(), deadline));

						dateField.getStyleClass().removeAll("textField-error");
						dateField.setText("");
						nameField.setText("");

						tasksPane.notifyDataChanged();
					}
				}
			}
		});

		inputBar.getChildren().add(dateField);
		inputBar.getChildren().add(nameField);

		ScrollPane scroll = new ScrollPane();
		scroll.setContent(tasksPane);
		scroll.setStyle("-fx-background-color:transparent;");
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

		root.getChildren().add(inputBar);
		root.getChildren().add(new Separator());
		root.getChildren().add(scroll);

		Scene scene = new Scene(root, 300, 250);
		scene.getStylesheets().add("style.css");

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop()
	{
		File tasksFile = TasksReader.getDataFile();
		TasksWriter tasksWriter = new TasksWriter(tasksFile);
		tasksWriter.write(tasks);
	}
}
