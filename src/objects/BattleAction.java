package src.objects;

public class BattleAction{
	public Battler user; public Battler target;
	
	public boolean wasSuccess = false; //se a ação acertou ou não
	public boolean wasCritical = false;

  public EBattlerAction type = EBattlerAction.NONE; 

	public int value = 0; //dano ou cura, etc

	public void setValue(int val){
		this.value = Math.max(0, val);
	}
}