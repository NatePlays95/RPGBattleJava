package src.objects;

//Actor sao os personagens de jogador, com equipamento e inventario
public class Actor extends Battler{
  //item array inventory
  //item weapon
  //item armor

	public int statPoints = 0; //pontos para aumentar stats, ganha ao subir de nivel
	
  Actor(){
    this.isAI = false;
  }
  public Actor(String name){
    this();
		this.name = name;
		this.exp = 0; this.gold = 0;
		this.baseHp = 8;
    this.hp = this.getMaxHp();
  }

  public void gainExp(int val){
		this.exp += val;
	}
	public void gainGold(int val){
		this.gold += val;
	}
	public boolean spendGold(int val){
		//retornar boolean, falso se não pôde pagar
		if (this.gold >= val){
			this.gold -= val;
			return true;
		}
		return false;
	}


	public void healToFull(){
		hpHeal(getMaxHp());
	}
	
	public int expToLevel(){ //how much exp total for this level?
		int x = this.level;
    return (int) Math.floor(0.3*x*x*x + 3*x);
	}

	public boolean attemptLevelUp(){ //call from menus
		boolean didLevelUp = false;
		while(this.exp >= this.expToLevel()) {
			this.level += 1;
			this.statPoints += 2;
			didLevelUp = true;
		}
		return didLevelUp;
	}

	public void levelUpParams(int[] newParams){
		this.params = newParams;
	}
	
}