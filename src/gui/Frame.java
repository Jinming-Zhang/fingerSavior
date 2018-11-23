package gui;

import TaskDriver.Lin;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.scene.control.*;

public class Frame extends Application {
	// elements in the frame
	private Lin lin;

	public Button mouse, keyNo1;
	public Button start, terminate;

	public Text restTimeLabel;
	public TextField restTime;

	public TextArea tasks;

	private void initialize() {
		mouse = new Button("Click Left Key");
		keyNo1 = new Button("Click number 1");
		start = new Button("Drive Lin");
		terminate = new Button("Terminat tasks");

		restTimeLabel = new Text("Rest Time");
		restTime = new TextField("100");

		tasks = new TextArea();
		tasks.setPrefSize(250, 75);

		lin = new Lin();
	}

	@Override
	public void start(Stage stg) throws Exception {
		initialize();

		enableKeyButton();
		enableTerminateButton();
		enableStartButton();
		// Root Scene node
		GridPane root = new GridPane();
		root.setMinSize(600, 300);
		root.add(keyNo1, 0, 0);
		root.add(terminate, 0, 1);
		root.add(start, 0, 2);
		root.add(restTimeLabel, 2, 0);
		root.add(restTime, 2, 1);
		root.add(tasks, 3, 0);
		// Scene
		Scene scene = new Scene(root, 600, 300);
		// Stage
		stg.setScene(scene);
		refreshTaskBoard();
		stg.show();
	}

	@Override
	public void stop() {
		lin.interrupt();
		lin.myState();
		lin.interrupt();
		lin.myState();
		System.exit(0);
	}

	/********************* event handlers ***********************************/
	private void enableKeyButton() {
		keyNo1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				lin.myState();
				lin.interrupt();
				lin = new Lin();
				// add tasks
				lin.addTask("sleep," + restTime.getText());
				refreshTaskBoard();
			}
		});
	}

	private void enableMouseButton() {
		mouse.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				lin.addTask("mouse, click, " + restTime.getText());
			}
		});
	}

	private void enableTerminateButton() {
		terminate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				lin.interrupt();
				lin.myState();
				tasks.clear();
			}
		});
	}

	private void enableStartButton() {
		start.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				lin.interrupt();
				refreshTaskBoard();
				lin.start();

			}
		});
	}

	/************************ help functions ***********************************/
	private void refreshTaskBoard() {
		tasks.appendText(lin.myTasks());
	}
}
