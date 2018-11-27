package gui;

import java.util.ArrayList;

import TaskDriver.Lin;
import constants.Layout;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.scene.control.*;

public class Frame extends Application {
	// elements in the frame
	private Lin lin;
	
	private ArrayList<String> newTasks;
	
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
		newTasks = new ArrayList<String>();
	}

	@Override
	public void start(Stage stg) throws Exception {
		initialize();

		enableMouseButton();
		enableKeyButton();
		enableTerminateButton();
		enableStartButton();
		
		// start / terminate
		HBox performer = new HBox();
		//HBox.set
		//performer.setMinWidth(Layout.FrameWidth);
		performer.getChildren().addAll(start, terminate);
		performer.setSpacing((Layout.FrameWidth - 100) / 2);
		// task design pane
		GridPane taskCreater = new GridPane();
		//setupGridPane(taskCreater);
		//taskCreater.setMinWidth(Layout.FrameHeight / 2);
		taskCreater.add(mouse, 0, 0);
		taskCreater.add(keyNo1, 0, 1);
		taskCreater.add(restTimeLabel, 1, 0);
		taskCreater.add(restTime, 1, 1);
		taskCreater.setGridLinesVisible(true);
		// task view pane
		GridPane taskViewer= new GridPane();
		//setupGridPane(taskViewer);
		//taskViewer.setMinWidth(Layout.FrameHeight / 2);
		taskViewer.add(tasks, 0, 0);
		taskViewer.setGridLinesVisible(true);
		// Root Scene node
		FlowPane root = new FlowPane();
		//setupGridPane(root);
		
		//root.setMinSize(600, 300);
		root.getChildren().addAll(taskCreater, taskViewer,performer);
		/*add(taskCreater, 0, 0);
		root.add(taskViewer, 1, 0);
		root.add(performer, 0, 1);
		root.setGridLinesVisible(true);*/

		// Scene
		Scene scene = new Scene(root, Layout.FrameWidth, Layout.FrameHeight);
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
				// add tasks
				newTasks.add("key");
				newTasks.add("sleep," + restTime.getText());
				refreshTaskBoard();
			}
		});
	}

	private void enableMouseButton() {
		mouse.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				newTasks.add("mouse, click, " + restTime.getText());
				newTasks.add("sleep," + restTime.getText());
				refreshTaskBoard();
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
				if (lin.getState() != Thread.State.TERMINATED) {
					lin.interrupt();
				}
				lin = new Lin();
				for(String nextTask: newTasks) {
					lin.addTask(nextTask);
				}
				refreshTaskBoard();
				lin.start();
			}
		});
	}

	/************************ layouts ***********************************/
	private void setupGridPane(GridPane gp) {
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.setVgap(5); 
		gp.setHgap(5); 
		gp.setAlignment(Pos.CENTER); 
	}
	/************************ help functions ***********************************/
	private void refreshTaskBoard() {
		String linJob = "";
		for(String job: newTasks) {
			linJob += job;
			linJob += '\n';
		}
		tasks.setText(linJob);
	}
}
