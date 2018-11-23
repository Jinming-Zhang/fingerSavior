package gui;

import TaskDriver.Task;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.control.*;

public class Frame extends Application {
	// elements in the frame
	private Task LinDriver;
	
	public Button mouse, keyNo1;
	public Button terminate;

	private boolean tasking;

	private void initialize() {
		mouse = new Button("Click Left Key");
		keyNo1 = new Button("Click number 1");
		terminate = new Button("Terminat tasks");
		
		LinDriver = new Task();
		tasking = false;
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
		// Scene
		Scene scene = new Scene(root, 600, 300);
		// Stage
		stg.setScene(scene);
		stg.show();
	}

	@Override
	public void stop() {
		LinDriver.interrupt();
		System.exit(0);
	}

	/********************* event handlers ***********************************/
	private void enableKeyButton() {
		keyNo1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				//LinDriver.interrupt();
				System.out.println("calling press key " + LinDriver.getState().toString());
				LinDriver = new Task();
				System.out.println("State of Lin: " + LinDriver.getState().toString());
				LinDriver.start();
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
					LinDriver.interrupt();
			}
		});
	}
}
