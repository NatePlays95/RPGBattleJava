package src.managers;

import src.objects.BattleAction;
import src.util.Sleep;

public class BMOutToConsole implements BMOutput{

	public void displayStart(BattleManager bm){
		System.out.println("BATTLE START");
		Sleep.sleep(2000);
	}
	public void displayEnd(BattleManager bm){
		Sleep.sleep(500);
		System.out.println("BATTLE END");
	}
	

	public void displayTurn(BattleManager bm){
		String str = String.format("\nTurn %d\n", bm.turnCount);
		System.out.println(str);
		Sleep.sleep(500);
		str = String.format("%s - %s", bm.actor.name, bm.actor.hpText());
		System.out.println(str);
		str = String.format("%s - %s", bm.enemy.name, bm.enemy.hpText());
		System.out.println(str+"\n");
		Sleep.sleep(500);
	}
	

	public void displayEscape(BattleManager bm){
		Sleep.sleep(500);
		System.out.println("\n"+bm.actor.name + " escaped successfully!");
	}
	public void displayDefeat(BattleManager bm){
		Sleep.sleep(500);
		System.out.println("\n"+bm.actor.name + " was defeated. You lost.");
	}
	public void displayVictory(BattleManager bm, int goldYield, int expYield){
		Sleep.sleep(500);
		System.out.println("\n"+bm.enemy.name + " was defeated! You won!");
		Sleep.sleep(1000);
		String str = bm.actor.name + " got " + goldYield + " gold and " + expYield + " exp!"; System.out.println(str);
		
	}
	
	
	public void displayActionAttack(BattleManager bm, BattleAction action){
		String name = action.user.name;
		System.out.println(name + " attacks!");
		Sleep.sleep(500);
		if (!action.wasSuccess){
				System.out.println("but it missed!");
		} else {
			if (action.value == 0){
				System.out.println("Hits, but deals no damage.");
			} else {
				String str = String.format("Hits for %d damage!", action.value);
				System.out.println(str);
			}
		}
		Sleep.sleep(1000);
	}

	
	public void displayActionMagic(BattleManager bm, BattleAction action){
		String name = action.user.name;
		int mag = action.user.getParam(3);
		//nome da magia muda com Inteligencia
		if (mag >= 12){
			System.out.println(name + " casts Fireball!");
		} else if (mag >= 6){
			System.out.println(name + " casts Blizzard!");
		} else {
			System.out.println(name + " casts Thunder!");
		}
		Sleep.sleep(500);
		if (!action.wasSuccess || action.value == 0){
				System.out.println("but it fizzled out!");
		} else {
				String str = String.format("Hits for %d damage!", action.value);
				System.out.println(str);
		}
		Sleep.sleep(1000);
	}

	
	public void displayActionHeal(BattleManager bm, BattleAction action){
		String name = action.user.name;
		System.out.println(String.format(name+" heals themself for %d HP.", action.value));
		Sleep.sleep(1000);
	}

	
	public void displayActionOther(BattleManager bm, BattleAction action){
		String name = action.user.name;
		switch (action.type){
				
			case CHARGE:
				System.out.println(name+" charges a strong attack...");
				break;
			case GUARD:
				System.out.println(name+" raises their guard against incoming attacks.");
				break;
			case ESCAPE:
				System.out.println(name+" tries to escape...");
				Sleep.sleep(2000);
				break;
				
			default: break;
		}
		Sleep.sleep(1000);
	}

	
	
}