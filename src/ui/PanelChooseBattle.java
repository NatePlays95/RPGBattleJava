package src.ui;

import java.io.File;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import src.managers.GameManager;
import src.managers.DataManager;
import src.objects.Enemy;

public class PanelChooseBattle extends PanelGame{

  private javax.swing.JButton btnBattle;
  private javax.swing.JButton btnReturn;
	private javax.swing.JLabel imgEnemy;
  private javax.swing.JLabel imgBg;
  private javax.swing.JComboBox<String> jComboBox1;
  private javax.swing.JLabel lblStatus1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JLabel lblTitle;

	public PanelChooseBattle(GameManager gameIn) {
		super(gameIn);
		initComponents();
		updateEnemy();
	}

	private void initComponents() {

    lblTitle = new javax.swing.JLabel();
    jComboBox1 = new javax.swing.JComboBox<>();
    btnReturn = new javax.swing.JButton();
    btnBattle = new javax.swing.JButton();
    jPanel1 = new javax.swing.JPanel();
    lblStatus1 = new javax.swing.JLabel();
		imgEnemy = new javax.swing.JLabel();
    imgBg = new javax.swing.JLabel();

		setMaximumSize(new java.awt.Dimension(400, 300));
    setMinimumSize(new java.awt.Dimension(400, 300));
    setPreferredSize(new java.awt.Dimension(400, 300));
    setLayout(null);

    lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblTitle.setForeground(new java.awt.Color(255, 255, 255));
    lblTitle.setText("Choose your Adversary");
    add(lblTitle);
    lblTitle.setBounds(22, 15, 200, 20);

    //jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Slime", "Boar", "Zombie", "Wasp", "Skeleton", "Fighter", "Rogue", "Wizard", "Shaman", "Beholder" }));
    add(jComboBox1);
    jComboBox1.setBounds(240, 15, 120, 22);
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1ActionPerformed(evt);
      }
    });

		loadList();

    btnReturn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    btnReturn.setText("Return");
		btnReturn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnReturnActionPerformed(evt);
      }
    });
    add(btnReturn);
    btnReturn.setBounds(43, 233, 120, 50);

    btnBattle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    btnBattle.setText("Battle!");
		btnBattle.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnBattleActionPerformed(evt);
      }
    });
    add(btnBattle);
    btnBattle.setBounds(240, 233, 120, 50);

		imgEnemy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    imgEnemy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/battlers/_empty.png"))); // NOI18N
    add(imgEnemy);
    imgEnemy.setBounds(240, 43, 120, 96);

    lblStatus1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblStatus1.setForeground(new java.awt.Color(255, 255, 255));
    lblStatus1.setText("HP - 13/13");
    add(lblStatus1);
    lblStatus1.setBounds(150, 140, 220, 40);

    imgBg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ui/entrance.png"))); // NOI18N
    add(imgBg);
    imgBg.setBounds(0, 0, 400, 300);
  }

	
	void loadList(){
		Vector<String> names = DataManager.getCsvEnemies();
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(names));
	}
	
	void updateEnemy(){
		Enemy enemy = new Enemy(jComboBox1.getSelectedItem().toString());

		lblStatus1.setText(enemy.name + " " + "Lvl " + enemy.getLevel());
		java.net.URL imgURL = getClass().getResource("/img/battlers/"+enemy.name+".png");
		if (imgURL != null) imgEnemy.setIcon(new ImageIcon(imgURL));
	}

	void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt){
		updateEnemy();
	}
	
	void btnReturnActionPerformed(java.awt.event.ActionEvent evt){
		game.doMainScene();
	}
	void btnBattleActionPerformed(java.awt.event.ActionEvent evt){
		final String enemyName = jComboBox1.getSelectedItem().toString();
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	    @Override
	    protected Void doInBackground() throws Exception {
	    // Simulate doing something useful.
		    game.doBattle(enemyName);
	    	return null;
	    }
	  };
	  worker.execute();
	}
	
}
	