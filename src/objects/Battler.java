package src.objects;

public class Battler implements java.io.Serializable{
  public String name = "Empty Battler";
  
  int level = 1;
  int exp = 1;
  int gold = 5;

  int hp; //hp 
  int maxHp;
  int baseHp = 10;
  public int[] params = {1,1,1,1,1,1};
  //forca, destreza, constituição, inteligencia, carisma, sorte

	AttackRange attack = new AttackRange(0,1);
	int defense = 0;
	
  public EBattlerState state;
  public boolean isAI;
  EBattlerAIType aiType = EBattlerAIType.ALWAYS_ATTACK;
  
  public Battler(){
		this.hp = this.getMaxHp();
	} //constructor
  
	public boolean isActor(){
		return (this instanceof Actor);
	}
	public boolean isEnemy(){
		return (this instanceof Enemy);
	}

	
  //setters e getters
  public int getMaxHp(){
    //formula: hp maximo = hp base + 1/nivel-1 + 2 vezes constituição
    this.maxHp = this.baseHp + (Math.max(this.level-1, 0)) + 2*this.params[2]; //atualiza o hp maximo
    return this.maxHp;
  }
  public int getHp(){
    return Math.max(0, this.hp);
  }
  public void setHp(int value){
    this.hp = Math.max(Math.min(value, this.maxHp), 0);
  }
	public float getHpRate(){
		return (float)this.getHp() / this.getMaxHp();
	}

  public void hpDamage(int dmg){
    this.setHp(this.hp - dmg);
  }
  public void hpHeal(int heal){
    this.setHp(this.hp + heal);
  }

  public String hpText(){
    return String.format("%d/%d", this.getHp(), this.getMaxHp());
  }

	public int getParam(int index){
		return this.params[index];
	}
	public int getLevel() { return this.level; }
	public int getExp() { return this.exp; }
	public int getGold() { return this.gold; }
  //testes de estado
  public boolean isGuarding(){ return (this.state == EBattlerState.GUARDING); }
  public boolean isCharging(){ return (this.state == EBattlerState.CHARGING); }
  public boolean isCharged(){ return (this.state == EBattlerState.CHARGED); }
  public void setState(EBattlerState stateIn){
    this.state = stateIn;
  }
  public int restoreState(){ //faz o progresso do estado atual para o proximo estado... retorna 1 para ataque forçado
    if (isCharging()){
      setState(EBattlerState.CHARGED);
      return 1;
    }
    if (isGuarding() || isCharged()) {
      setState(EBattlerState.NORMAL);
      return 0;
    }
    return 0;
  }

  //mais stats
  public int getWeaponHit() { return 0; }
  public int getAttack() { return this.attack.roll(); }
  public int getDefense() { return this.defense; }
  public int getLuckRoll(){
    return (int)Math.floor(Math.random() * this.params[5]+1);
  }

	public void setAIType(EBattlerAIType type){
		this.aiType = type;
	}
	//IA
  public EBattlerAction AIChooseAction(Battler target, int turncount){
    EBattlerAction retAction;
    
		//if charged, forced attack
		if (this.isCharged()) return EBattlerAction.ATTACK;

		//test AI types
		switch (this.aiType){
        
      case ALWAYS_ATTACK:
        retAction = EBattlerAction.ATTACK; break;
        
      case GUARD_EVERY_OTHER_TURN:
        if (turncount%2 == 0) {
          retAction = EBattlerAction.GUARD;
        } else retAction = EBattlerAction.ATTACK;
        break;

			case CHARGE_RANDOM:
				if (Math.random() < 0.5){
					retAction = EBattlerAction.CHARGE;
				} else retAction = EBattlerAction.ATTACK;
				break;

			case HEAL_WHEN_DANGER:
				//if hpRate < 30%
				if ( this.getHpRate() <= 0.3 ){ 
					retAction = EBattlerAction.HEAL;
				//else random guard
				} else if (Math.random() < 0.3){ 
					retAction = EBattlerAction.GUARD;
				} else retAction = EBattlerAction.ATTACK;
				break;

			case OBSERVANT:
				//always block charges
				if (target.state == EBattlerState.CHARGING){
					retAction = EBattlerAction.GUARD;
				//may charge when player blocks
				} else if ((Math.random() < 0.5)&&(target.state == EBattlerState.GUARDING)) {
					retAction = EBattlerAction.CHARGE;
				} else retAction = EBattlerAction.ATTACK;
				break;

			case ALWAYS_MAGIC:
        retAction = EBattlerAction.MAGIC; break;

			case HEAL_AND_MAGIC:
				//if hpRate < 50% and random chance 50%
				if ( this.getHpRate() <= 0.5 && (Math.random() < 0.5)){ 
					retAction = EBattlerAction.HEAL;
				//else random guard
				} else retAction = EBattlerAction.MAGIC;
				break;

			case MAGIC_DEFENSIVE:
				//smart always heals on low
				if ( this.getHpRate() <= 0.2 ){ 
					retAction = EBattlerAction.HEAL;
				//smart blocks charges
				} else if (target.state == EBattlerState.CHARGING){
					retAction = EBattlerAction.GUARD;
				//mix guard and magic
				} else if (turncount % 3 == 2){ 
					retAction = EBattlerAction.GUARD;
				} else retAction = EBattlerAction.MAGIC;
				break;
				
			case SPELLBLADE:
				//use magic if hit rate is too low
				if (Math.random() < 0.8 && target.getParam(1) > this.getParam(1)+5){
					retAction = EBattlerAction.MAGIC;
				//use charge if target has too much hp
				} else if (target.getHpRate() > 0.6 && turncount % 5 == 0){
					retAction = EBattlerAction.CHARGE;
				//mix attack and magic
				} else if (Math.random() < 0.5){
					retAction = EBattlerAction.ATTACK;
				} else retAction = EBattlerAction.MAGIC;
				break;
				

      default:
        retAction = EBattlerAction.ATTACK;
    }
    return retAction;
  }



	public String toString(){
		String retstr = this.name+",";
		retstr += this.hpText()+",";
		retstr += this.level + " " + this.exp + ",";
		retstr += this.params.toString();
		if (isActor()) retstr+=" type Actor";
		if (isEnemy()) retstr+=" type Enemy";
		return retstr;
	}
}