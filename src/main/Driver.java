package main;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import mouse.Mouse;
import system.Software;
import keyboards.Keyboard;

public class Driver {

  public static void main(String[] args) throws AWTException {
    Robot rb = new Robot();

    while (true) {

      // delays for special game 
      /*
      Software.hold(2000);
      Mouse.clickMouse(rb, InputEvent.BUTTON1_DOWN_MASK);
      Software.hold(11268); 
      Keyboard.clickKey(robot, KeyEvent.VK_1);
      */
      // common
      //Mouse.clickMouse(rb, InputEvent.BUTTON1_DOWN_MASK); Software.hold(10); 
      Keyboard.clickKey(rb, KeyEvent.VK_1);      Software.hold(10);

    }
  }

}
