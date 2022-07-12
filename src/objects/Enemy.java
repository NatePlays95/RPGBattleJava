package src.objects;

import src.managers.DataManager;

public class Enemy extends Battler{
  
  //Item array drops
  //int attack = 1;
  //int defense = 0;
  
  Enemy(){ //constructor base
    this.isAI = true;
  }
  public Enemy(String name){ //constructor publico
    this();
		String dataStr = DataManager.searchEnemy(name);
		if (dataStr == null){
			System.out.println("ERROR: Can't find enemy with that name");
			//throw new Exception(); 
			//return;
		}
		String[] dataValues = dataStr.split(",");
		
		this.name = dataValues[0];
		this.level = Integer.parseInt(dataValues[1]);
		this.exp = Integer.parseInt(dataValues[2]);
		this.gold = Integer.parseInt(dataValues[3]);

		this.baseHp =  Integer.parseInt(dataValues[4]);
		for (int i = 0; i < 6; i++){
			this.params[i] = Integer.parseInt(dataValues[5+i]);
		}
		this.attack = new AttackRange(Integer.parseInt(dataValues[11]), Integer.parseInt(dataValues[12]));
		this.defense = Integer.parseInt(dataValues[13]);
		this.aiType = EBattlerAIType.valueOf(dataValues[14]);

		//finish initialization
		this.hp = this.getMaxHp();
  } 

}