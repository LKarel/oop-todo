package todoapp.gui;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import todoapp.TaskList;
import todoapp.TasksReader;

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

		StackPane root = new StackPane();
		root.getChildren().add(new TasksPane(tasks));

		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}
}
