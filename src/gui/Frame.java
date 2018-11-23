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
	public Button terminate;

	public Text restTimeLabel;
	public TextField restTime;
	private void initialize() {
		mouse = new Button("Click Left Key");
		keyNo1 = new Button("Click number 1");
		terminate = new Button("Terminat tasks");
		restTimeLabel = new Text("Rest Time");
		restTime = new TextField("100");
		
		lin = new Lin();
	}

	@Override
	public void start(Stage stg) throws Exception {
		initialize();
		
		enableKeyButton();
		enableTerminateButton();
		// Root Scene node
		GridPane root = new GridPane();
		root.setMinSize(600, 300);
		root.add(keyNo1, 0, 0);
		root.add(terminate, 0, 1);
		root.add(restTimeLabel, 3, 0);
		root.add(restTime, 3, 1);
		// Scene
		Scene scene = new Scene(root, 600, 300);
		// Stage
		stg.setScene(scene);
		stg.show();
	}

	@Override
	public void stop() {
		lin.interrupt();
		System.exit(0);
	}

	/********************* event handlers ***********************************/
	private void enableKeyButton() {
		keyNo1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String linState = lin.getState().toString();
				System.out.println("State of Lin: " + linState);
				//if(!linState.equals("TERMINATED")) {
					lin.interrupt();
				//}
				lin = new Lin();
				// add tasks
				lin.addTask("sleep," + restTime.getText());
				lin.start();
			}
		});
	}

	private void enableMouseButton() {
		mouse.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void enableTerminateButton() {
		terminate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
					lin.interrupt();
			}
		});
	}
}
