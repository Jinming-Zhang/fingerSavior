package keyboards;

import java.awt.Robot;

public class Keyboard {
  /*
   * Click the given key
   */
  public static void clickKey(Robot rb, int keyno){
    rb.keyPress(keyno);
    rb.keyRelease(keyno);
  }
  
  
}
