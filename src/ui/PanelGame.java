package src.ui;

import src.managers.GameManager;

//qualquer painel que precisa comunicar com o Model: GameManager
public class PanelGame extends javax.swing.JPanel{
	GameManager game;
	PanelGame(GameManager gameIn){
		this.game = gameIn;
	}
}