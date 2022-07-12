package src.util;

public class Sleep{
  public static void sleep(int miliseconds){
    try{ Thread.sleep(miliseconds); }
    catch(InterruptedException ex){
      Thread.currentThread().interrupt();
      ex.printStackTrace();
    }
  }
}