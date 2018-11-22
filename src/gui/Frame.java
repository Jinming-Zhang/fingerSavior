package gui;
import java.awt.AWTException;

import TaskDriver.Task;
import javafx.application.Application; 
import javafx.stage.Stage; 
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.*;
import javafx.scene.control.*; 
import javafx.scene.layout.*;
public class Frame extends Application{
  static Task tk;
  @SuppressWarnings("restriction")
  @Override
  public void start(Stage stg) throws Exception {
    // Scene elements
    Button mouse = new Button("Click Left Key");
    mouse.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {
            if(tk.running){
              tk.running = false;
            }else{
              tk.running = true;
            }
          }
        });
    
    // Root Scene node
    Group root = new Group (mouse);
    
    // Scene
    Scene scene = new Scene(root, 600, 300);
    // Stage
    stg.setScene(scene);
    stg.show();
  }

    @Override
    public void stop(){
      
    }
  public static void main(String[] args){
    tk = new Task();
    Thread td = new Thread(tk);
    td.start();
    launch(args); 
  }
}
