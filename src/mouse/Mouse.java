package mouse;

import java.awt.Robot;

public class Mouse {
  
  public static void clickMouse(Robot rb, int mousekey){
    rb.mousePress(mousekey);
    rb.mouseRelease(mousekey);
  }
}
