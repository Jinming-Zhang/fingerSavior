package mouse;

import java.awt.Robot;

public class Mouse {
  
  public static void clickMouse(Robot rb, int mousekey){
    rb.keyPress(mousekey);
    rb.keyRelease(mousekey);
  }
}
