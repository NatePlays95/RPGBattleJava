package src.objects;

public class Item{
  String name;


  
  public boolean isWeapon(){
    if (this instanceof ItemWeapon) return true;
    return false;
  }
  public boolean isArmor(){
    if (this instanceof ItemArmor) return true;
    return false;
  }
}
