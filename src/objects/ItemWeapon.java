package src.objects;

public class ItemWeapon extends Item{
  AttackRange attack;
  
  public int roll(){
    return attack.roll();
  }
}