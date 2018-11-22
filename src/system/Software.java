package system;

public class Software {
  
  public static void hold(long milisec){
    try {
      Thread.sleep(milisec);
    } catch (InterruptedException e) {
      e.printStackTrace();
      System.out.println("Cant sleep!!");
    }
  }
}
