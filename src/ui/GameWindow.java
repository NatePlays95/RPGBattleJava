package src.ui;

import javax.swing.*;

public class GameWindow extends JFrame{

	public JPanel panel;
	
	public GameWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("RPG BATTLE SIMULATOR");
		this.setSize(400, 300); //pack(); 
		setVisible(true);
	}


	
	synchronized public void gotoPanel(JPanel panel){
		this.panel = panel; //referencia externa
		getContentPane().removeAll();
		getContentPane().add(panel);
		getContentPane().revalidate(); //IMPORTANT
		getContentPane().repaint();    //IMPORTANT
		pack();
		panel.setVisible(true);
	}

	
}