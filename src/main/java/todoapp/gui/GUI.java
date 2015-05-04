package todoapp.gui;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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

		StackPane root = new StackPane();

		ScrollPane scroll = new ScrollPane();
		scroll.setContent(new TasksPane(tasks));
		scroll.setStyle("-fx-background-color:transparent;");
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

		root.getChildren().add(scroll);

		primaryStage.setScene(new Scene(root, 300, 250));
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
