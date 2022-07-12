package src.managers;

import java.util.Scanner;
import src.objects.EBattlerAction;

public class BMInFromConsole implements BMInput{
	Scanner scanner = new Scanner(System.in);
	
	public EBattlerAction enterChooseAction(){
		EBattlerAction retAction = null;
		System.out.println("CHOOSE YOUR ACTION");
		while (true){
			
			System.out.println("[1]Attack - [2]Charge - [3]Guard - [4]Magic - [5]Heal: ");

			int input = scanner.nextInt();
			switch(input){
				case 1:
					retAction = EBattlerAction.ATTACK; break;
				case 2:
					retAction = EBattlerAction.CHARGE; break;
				case 3:
					retAction = EBattlerAction.GUARD; break;
				case 4:
					retAction = EBattlerAction.MAGIC; break;
				case 5:
					retAction = EBattlerAction.HEAL; break;
				//case 6:
				//	retAction = EBattlerAction.ESCAPE; break;
				default:
					break;
			}
			if (retAction != null){
				break;
			} else {
				System.out.println("INVALID INPUT");
			}
		}
		System.out.println(" ");
		return retAction;
	}	
}