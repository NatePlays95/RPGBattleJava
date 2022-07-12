package src.util;

import java.util.Random;

public class Roll{
	public static Random rand;
	static{
		rand = new Random(); //inicializar variavel estatica
	}
	
	public static int d100(){
		int randomNum = rand.nextInt(100) + 1;
    return randomNum;
	}

}