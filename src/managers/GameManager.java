package src.managers;

import java.io.*;
import src.objects.*;
import src.ui.*;
import src.util.Sleep;

public class GameManager{

	public Battler player;
	public Battler enemy;
	public BattleManager bm;
	GameWindow window;
	//String windowInput = "";

	public GameManager(){
		window = new GameWindow();
	}

	public void run(){
		doTitleScene();
	}
	
	public void doChooseBattle(){
		window.gotoPanel(new PanelChooseBattle(this));
	}
	
	public void doTitleScene(){
		window.gotoPanel(new PanelTitle(this));
	}
	
	public void doLevelUpScene(){
		window.gotoPanel(new PanelLevelUp(this));
	}
	
	public void doMainScene(){
		boolean didLevelUp = ((Actor)player).attemptLevelUp();
		if (didLevelUp) {
			doLevelUpScene(); 
			return;
		}
		
		((Actor)player).healToFull();
		savePlayer();
		window.gotoPanel(new PanelMain(this));
	}
	
	public void doBattle(String enemyName){
		enemy = new Enemy(enemyName); //player.isAI = true; 
		
		bm = new BattleManager(player, enemy); 
		PanelBattle bpanel = new PanelBattle(this);
		//Thread t1 = new Thread(bpanel); t1.start();
		bm.attachInput(bpanel);
		bm.attachOutput(bpanel);
		window.gotoPanel(bpanel);
		boolean result = false;
		result = bm.run(); //falso quando derrota
		enemy = null;
		if (result){
			doMainScene();
		} else doGameOver();
	}
	
	public void doGameOver(){
		savePlayer(); //save dead character
		window.gotoPanel(new PanelGameOver());
	}
	
	public void doNewCharacter(){
		window.gotoPanel(new PanelNewCharacter(this));
	}
	public void doLoadCharacter(String filename){
		if (loadPlayer(filename)){
			doMainScene();
		} else doTitleScene();
	}

	public void createPlayer(String name){
		this.player = new Actor(name);
		doMainScene();
	}
	public void createPlayer(String name, int[] paramsIn){
		this.player = new Actor(name);
		this.player.params = paramsIn;
		doMainScene();
	}
	public boolean loadPlayer(String filename){
		//name includes .dat end
		try {
			FileInputStream fis = new FileInputStream("./saves/"+filename);
    	ObjectInputStream ois = new ObjectInputStream(fis);
    	// write object to file
			//System.out.println(ois.readObject());
    	//System.out.println(filename);
			Battler loadedPlayer = (Battler)ois.readObject();
			//System.out.println(loadedPlayer);
			player = loadedPlayer;
			ois.close();
		} catch (Exception ex) {
		  ex.printStackTrace();	
			System.out.println("Error\nCouldn't open saved file.");
			return false;
		}
		return true;	
	}
	
	public void savePlayer(){
		try {
			FileOutputStream fos = new FileOutputStream("./saves/"+player.name+".dat");
    	ObjectOutputStream oos = new ObjectOutputStream(fos);
    	// write object to file
    	oos.writeObject(player);
			oos.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
	
}