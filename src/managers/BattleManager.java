package src.managers;

import src.objects.*;
import src.util.Sleep;
import src.util.Roll;

public class BattleManager{
  enum BMPhase{ PHASE_TURN, PHASE_ACTOR, PHASE_ENEMY };
  enum BMResult{ NOT_DECIDED, VICTORY, DEFEAT, ESCAPE };
  BMPhase phase = BMPhase.PHASE_TURN;
  BMResult result = BMResult.NOT_DECIDED;
  
  public Battler actor;
  public Battler enemy;

	BMOutput output;
	BMInput input;

  public int turnCount = 0;

  public BattleManager(Battler a, Battler b){
    this.actor = a; this.enemy = b;
  }

  public boolean run(){ //retorna falso se derrota, verdadeiro se vitoria
    start();
    
    while(true){
      
      switch(this.phase){
        case PHASE_TURN:
          phaseTurn();
          this.phase = BMPhase.PHASE_ACTOR;
          break;
        case PHASE_ACTOR:
          phaseActor();
          this.phase = BMPhase.PHASE_ENEMY;
          break;
        case PHASE_ENEMY:
          phaseEnemy();
          this.phase = BMPhase.PHASE_TURN;
          break; //esses breaks saem do switch
      }
      testForDefeat();
      if (this.result != BMResult.NOT_DECIDED){
        break; //esse break sai do loop
      }
    }
    
    end();

		//retorna falso se derrota, verdadeiro se vitoria
		boolean ret = (this.result != BMResult.DEFEAT); 
		//System.out.println("resultado: "); System.out.println(ret);
		return ret;
  }

  void start(){
    //throw missing battler and missing output/input exceptions
		output.displayStart(this);
  }
  void end(){
		//teste de vitoria ou derrota vai aqui
    //premios, gold, exp, itens vao aqui
    
		if (this.result == BMResult.ESCAPE){
			output.displayEscape(this);
		} else if (this.result == BMResult.DEFEAT) {
			output.displayDefeat(this);
			//curar personagens depois da batalha se necessario

		} else if (this.result == BMResult.VICTORY) {
			int expYield = enemy.getExp();
			int goldYield = enemy.getGold();
			output.displayVictory(this,goldYield, expYield);
			if (actor.isActor()){
				((Actor)actor).gainExp(expYield); ((Actor)actor).gainGold(goldYield);
			}
			
			//TODO: subir de nivel aqui
		}
		
		output.displayEnd(this);
    
  }


  
  //valores temporarios
  void phaseTurn(){
    this.turnCount += 1;
		output.displayTurn(this);
  }
	
  void phaseActor(){
		this.actor.restoreState();
		
		//escolher acao vai aqui
		if (actor.isAI){ //caso raro
			AIChooseAction(actor, enemy);
		} else {
			InputChooseAction(actor, enemy);
		}
  }
	
  void phaseEnemy(){
		this.enemy.restoreState();

		//escolher acao pela IA vai aqui
		if (enemy.isAI){
			AIChooseAction(enemy, actor);
		} else {
			InputChooseAction(enemy, actor);
		}
  }

	void AIChooseAction(Battler user, Battler target){ //quem usa a ação
		if (user.isCharged()){
			actionAttack(user, target); return;
		}
		EBattlerAction choice = user.AIChooseAction(target, turnCount);
		switch (choice){
			case ESCAPE:
				actionEscape(user); break;
			case ATTACK:
				actionAttack(user, target); break;
			case GUARD:
				actionGuard(user); break;
			case CHARGE:
				actionCharge(user); break;
			case HEAL:
				actionHeal(user); break;
			case MAGIC:
				actionMagic(user, target); break;
			default:
				actionAttack(user, target); break;
		}
	}

	void InputChooseAction(Battler user, Battler target){
		if (user.isCharged()){
			actionAttack(user, target); return;
		}
		EBattlerAction choice = input.enterChooseAction();
		switch (choice){
			case ESCAPE:
				actionEscape(user); break;
			case ATTACK:
				actionAttack(user, target); break;
			case GUARD:
				actionGuard(user); break;
			case CHARGE:
				actionCharge(user); break;
			case HEAL:
				actionHeal(user); break;
			case MAGIC:
				actionMagic(user, target); break;
			default:
				actionAttack(user, target); break;
		}
	}


	boolean hitFormula(Battler attacker, Battler defender){
		int adex = attacker.getParam(1); int bdex = defender.getParam(1); int ahit = attacker.getWeaponHit();
		int hitCheck = (int) Math.floor(150*((float)(1+adex+ahit)/(adex+bdex+1)) - defender.getLuckRoll()*2);
		int hitRoll = Roll.d100();
		boolean retval = (hitRoll <= hitCheck);
		//debug
		//System.out.println(String.format("debug: hit check %d roll %d",hitCheck,hitRoll)); 
		//System.out.println("success: "+String.valueOf(retval));
		//debug
		return retval;
	}
	
	int attackFormula(Battler attacker, Battler defender){
		//crit goes here?
		
		int astr = attacker.getParam(0); int aatk = attacker.getAttack();
		int bdef = defender.getDefense();
		int charge = attacker.isCharged() ? 4 : 1;//if attacker is charged, 3, else, 1
		int guard = defender.isGuarding() ? 3 : 1;
	  //dmg = math.floor( (atk + attacker.getAttack()*charge )*atk / (atk + dfs*guard) )
		int dmg = (int)Math.floor((float)(1+astr + (aatk)*charge)*astr/(astr + (1+bdef)*guard));
		dmg = Math.max(0, dmg);

		return dmg;
	}
	
	int magicFormula(Battler attacker, Battler defender){
		//formula: roll twice
		int mag = attacker.getParam(3);
		int roll1 = (int)(Math.floor(Math.random()*mag));
		int roll2 = (int)(Math.floor(Math.random()*mag));
		return (int) Math.floor((roll1 + roll2)/2);
	}

	
  //ações, para cada BattlerAction
  void actionEscape(Battler user){
    BattleAction action = new BattleAction(); //action.wasSuccess = true;
		action.type = EBattlerAction.ESCAPE;
		action.user = user;

		//TODO: escape roll logic
		
		this.result = BMResult.ESCAPE; action.wasSuccess = true;

		output.displayActionOther(this,action);
  }
  
	
	void actionAttack(Battler attacker, Battler defender){
		BattleAction action = new BattleAction();
		action.type = EBattlerAction.ATTACK;
		action.user = attacker; action.target = defender;
		//registra o resultado pra passar pra output
		action.wasSuccess = hitFormula(attacker, defender);
		
		if (action.wasSuccess) { //acerto
			action.setValue(attackFormula(attacker, defender)); 
			//action.setValue( (int)(Math.random()*5) + (attacker.isCharged() ? 3 : 0) - (defender.isGuarding() ? 5 : 0) ); //temporario	
			defender.hpDamage(action.value);
		} //else { //errou }
		
    output.displayActionAttack(this,action);
  }
	
  void actionMagic(Battler attacker, Battler defender){
		BattleAction action = new BattleAction();
		action.type = EBattlerAction.MAGIC;
		action.user = attacker; action.target = defender;

		//spell hit formula = 100 - luckroll*2
    int hitCheck = 100 - defender.getLuckRoll()*2;
		int hitRoll = Roll.d100();
		action.wasSuccess = (hitRoll <= hitCheck);
		
		if (action.wasSuccess) { //acerto
			//damage formula ignores defense
			int dmg = magicFormula(attacker, defender);
			action.setValue(dmg);
			
			defender.hpDamage(action.value);
		}

		output.displayActionMagic(this,action);
		
  }
  void actionCharge(Battler user){
		BattleAction action = new BattleAction(); action.wasSuccess = true;
		action.type = EBattlerAction.CHARGE; 
		action.user = user;
		
    user.setState(EBattlerState.CHARGING);
		
    output.displayActionOther(this,action);
  }

  void actionGuard(Battler user){
		BattleAction action = new BattleAction(); action.wasSuccess = true;
		action.type = EBattlerAction.GUARD;
		action.user = user;
		
    user.setState(EBattlerState.GUARDING);
		
    output.displayActionOther(this,action);
  }

  void actionHeal(Battler user){
		BattleAction action = new BattleAction(); action.wasSuccess = true;
		action.type = EBattlerAction.HEAL;
		action.user = user;

		//heal formula
		int mag = user.getParam(3);
		int heal = mag - (int)(Math.floor(Math.random()*mag/2));
		action.setValue(heal);
		user.hpHeal(action.value);

		output.displayActionHeal(this,action);
  }
  

  //vê quem tem hp pra morrer, e em que turno.
  void testForDefeat(){
		if (actor.getHp() <= 0){
			this.result = BMResult.DEFEAT; return;
		}
		if (enemy.getHp() <= 0){
			this.result = BMResult.VICTORY; return;
		}
  }

	//VIEWS
	public void attachOutput(BMOutput out){
		this.output = out;
		//System.out.println(this.output.toString());
	}
	public void detachOutput(){
		this.output = null;
	}
	//CONTROLLERS
	public void attachInput(BMInput in){
		this.input = in;
	}
	public void detachInput(){
		this.input = null;
	}
}