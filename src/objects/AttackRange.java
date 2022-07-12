package src.objects;

public class AttackRange implements java.io.Serializable{
  int min = 0;
  int max = 0;
  public AttackRange(int minIn, int maxIn){
    this.min = minIn; this.max = maxIn;
  }
  public int roll(){
    return (int)Math.floor(Math.random()*(this.max-this.min+1) + this.min);
  }
}