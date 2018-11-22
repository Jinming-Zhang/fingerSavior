package TaskDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import keyboards.Keyboard;
import system.Software;
import constants.MouseConstants;
import mouse.Mouse;


public class Task implements Runnable{
  public boolean running;
  private ArrayList<String> tasks;
  private Robot lin;
  
  public Task(){
    tasks = new ArrayList<String>(5);
    initializeLin();
    running = false;
    tasks.add("mouse, click, 10");
  }
  
  private void performTasks(){
    for(String nextTask: this.tasks){
      String [] taskDetail = nextTask.split(", ");
      if(taskDetail[0].equals("mouse")){
        System.out.println("Clicking mouse");
        //Mouse.clickMouse(lin, MouseConstants.left_key);
        Keyboard.clickKey(lin, KeyEvent.VK_1);      Software.hold(10);
        Software.hold(10);
      }
    }
  }
  @Override
  public void run() {
    System.out.println("Task starts");
    while(true){
      if(running){
        performTasks();
      }
      System.out.println("press key");
    }
  }

  private void initializeLin(){
    try {
      lin = new Robot();
    } catch (AWTException e) {
      System.out.println("Cant find lin");
      e.printStackTrace();
    }
  }
}
