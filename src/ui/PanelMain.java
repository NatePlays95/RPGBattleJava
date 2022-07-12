package src.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.managers.GameManager;

public class PanelMain extends PanelGame{

	private javax.swing.JButton btnArmory;
  private javax.swing.JButton btnBattle;
  private javax.swing.JButton btnInventory;
  private javax.swing.JButton btnSave;
  private javax.swing.JLabel imgBg;
  private javax.swing.JLabel lblStatusArmor;
  private javax.swing.JLabel lblStatusLevel;
  private javax.swing.JLabel lblStatusName;
  private javax.swing.JLabel lblStatusParams;
  private javax.swing.JLabel lblStatusParams2;
  private javax.swing.JLabel lblStatusWeapon;
  private javax.swing.JLabel lblTitle;
	
	public PanelMain(GameManager gameIn) {
		super(gameIn);
		initComponents();
		updateStatus();
	}

	void initComponents(){
		btnBattle = new javax.swing.JButton();
    btnArmory = new javax.swing.JButton();
    btnInventory = new javax.swing.JButton();
    btnSave = new javax.swing.JButton();
    lblTitle = new javax.swing.JLabel();
    lblStatusName = new javax.swing.JLabel();
    lblStatusLevel = new javax.swing.JLabel();
    lblStatusParams = new javax.swing.JLabel();
    lblStatusParams2 = new javax.swing.JLabel();
    lblStatusWeapon = new javax.swing.JLabel();
    lblStatusArmor = new javax.swing.JLabel();
    imgBg = new javax.swing.JLabel();
		Font fontStatus = new Font("Segoe UI", 0, 24);
		Color colorStatus = new Color(255,255,255);

		setMaximumSize(new java.awt.Dimension(400, 300));
    setMinimumSize(new java.awt.Dimension(400, 300));
    setPreferredSize(new java.awt.Dimension(400, 300));
    setLayout(null);

    btnBattle.setText("Battle");
    btnBattle.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnBattleActionPerformed(evt);
      }
    });
    add(btnBattle);
    btnBattle.setBounds(290, 150, 100, 22);

    btnArmory.setEnabled(false);
    btnArmory.setText("Armory");
    add(btnArmory);
    btnArmory.setBounds(290, 180, 100, 22);

    btnInventory.setText("Inventory");
    btnInventory.setEnabled(false);
    add(btnInventory);
    btnInventory.setBounds(290, 210, 100, 22);

    btnSave.setText("Save");
    btnSave.setEnabled(false);
    add(btnSave);
    btnSave.setBounds(290, 240, 100, 22);

    lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    lblTitle.setForeground(colorStatus);
    lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblTitle.setText("RPG Battle Simulator");
    add(lblTitle);
    lblTitle.setBounds(6, 6, 388, 32);

    lblStatusName.setForeground(colorStatus);
    lblStatusName.setText("Player Character - 20/20 HP");
    add(lblStatusName);
    lblStatusName.setBounds(10, 130, 200, 16);

    lblStatusLevel.setForeground(colorStatus);
    lblStatusLevel.setText("Level 3 - 1000 exp");
    add(lblStatusLevel);
    lblStatusLevel.setBounds(10, 150, 200, 16);

    lblStatusParams.setForeground(colorStatus);
    lblStatusParams.setText("Int 1, Cha 1, Bls 1");
    add(lblStatusParams);
    lblStatusParams.setBounds(10, 200, 200, 16);

    lblStatusParams2.setForeground(colorStatus);
    lblStatusParams2.setText("Str 1, Dex 1, Con 1");
    add(lblStatusParams2);
    lblStatusParams2.setBounds(10, 180, 200, 16);

    lblStatusWeapon.setForeground(colorStatus);
    lblStatusWeapon.setText("Weapon - []");
    add(lblStatusWeapon);
    lblStatusWeapon.setBounds(10, 240, 200, 16);

    lblStatusArmor.setForeground(colorStatus);
    lblStatusArmor.setText("Armor - []");
    add(lblStatusArmor);
    lblStatusArmor.setBounds(10, 260, 200, 16);

    imgBg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ui/dungeon.png"))); // NOI18N
    add(imgBg);
    imgBg.setBounds(0, 0, 400, 300);
	}

	void updateStatus(){
		src.objects.Actor player = (src.objects.Actor)game.player;
		
		lblStatusName.setText(player.name + " - " + player.hpText()+" HP");
		
		lblStatusLevel.setText("Level "+player.getLevel()+" - "+player.getExp()+"/"+player.expToLevel()+" EXP");
		
		String paramStr = "Str " + player.getParam(0) + ", ";
		paramStr += "Dex " + player.getParam(1) + ", ";
		paramStr += "Con " + player.getParam(2);
		lblStatusParams2.setText(paramStr);
		
		paramStr = "Int " + player.getParam(3) + ", ";
		paramStr += "Cha " + player.getParam(4) + ", ";
		paramStr += "Ble " + player.getParam(5);
		lblStatusParams.setText(paramStr);
	}

	void btnBattleActionPerformed(ActionEvent evt){
		game.doChooseBattle();
	}

	


	
}