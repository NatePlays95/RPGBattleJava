package src.managers;

import src.objects.BattleAction;

public interface BMOutput{

	public abstract void displayStart(BattleManager bm);
	public abstract void displayTurn(BattleManager bm);
	public abstract void displayEnd(BattleManager bm);

	public abstract void displayEscape(BattleManager bm);
	public abstract void displayDefeat(BattleManager bm);
	public abstract void displayVictory(BattleManager bm, int gold, int exp);

	public abstract void displayActionAttack(BattleManager bm, BattleAction action);
	public abstract void displayActionHeal(BattleManager bm, BattleAction action);
	public abstract void displayActionMagic(BattleManager bm, BattleAction action);
	
	//charge, guard, escape
	public abstract void displayActionOther(BattleManager bm, BattleAction action);


	
}