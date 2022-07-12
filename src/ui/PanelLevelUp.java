package src.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import src.objects.Battler;
import src.objects.Actor;
import src.managers.GameManager;

public class PanelLevelUp extends PanelGame{

	// Variables declaration - do not modify                     
  private javax.swing.JButton btnConfirm;
  private javax.swing.JLabel imgBg;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel lblParam0;
  private javax.swing.JLabel lblParam1;
  private javax.swing.JLabel lblParam2;
  private javax.swing.JLabel lblParam3;
  private javax.swing.JLabel lblParam4;
  private javax.swing.JLabel lblParam5;
  private javax.swing.JLabel lblStatusName;
  private javax.swing.JLabel lblTitle;
  private javax.swing.JSpinner spn0;
  private javax.swing.JSpinner spn1;
  private javax.swing.JSpinner spn2;
  private javax.swing.JSpinner spn3;
  private javax.swing.JSpinner spn4;
  private javax.swing.JSpinner spn5;
	private Battler playerRef;
	
	public PanelLevelUp(GameManager gameIn) {
		super(gameIn);
		initComponents();
	}

	 private void initComponents() {

		playerRef = game.player;
		 
    lblStatusName = new javax.swing.JLabel();
    lblTitle = new javax.swing.JLabel();
    spn0 = new StatSpinner(playerRef.getParam(0));
    spn1 = new StatSpinner(playerRef.getParam(1));
    spn2 = new StatSpinner(playerRef.getParam(2));
    spn3 = new StatSpinner(playerRef.getParam(3));
    spn4 = new StatSpinner(playerRef.getParam(4));
    spn5 = new StatSpinner(playerRef.getParam(5));
    lblParam0 = new javax.swing.JLabel();
    lblParam1 = new javax.swing.JLabel();
    lblParam2 = new javax.swing.JLabel();
    lblParam3 = new javax.swing.JLabel();
    lblParam4 = new javax.swing.JLabel();
    lblParam5 = new javax.swing.JLabel();
    jLabel1 = new javax.swing.JLabel();
    btnConfirm = new javax.swing.JButton();
    imgBg = new javax.swing.JLabel();

    lblStatusName.setForeground(new java.awt.Color(255, 255, 255));
    lblStatusName.setText("Player Character - 20/20 HP");

    setBackground(new java.awt.Color(102, 204, 0));
    setMaximumSize(new java.awt.Dimension(400, 300));
    setMinimumSize(new java.awt.Dimension(400, 300));
    setPreferredSize(new java.awt.Dimension(400, 300));
    setLayout(null);

    lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    lblTitle.setForeground(new java.awt.Color(255, 255, 255));
    lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblTitle.setText("Level Up!");
    add(lblTitle);
    lblTitle.setBounds(-2, 50, 400, 32);

    spn0.addChangeListener(new SpinnerListener(this));
    add(spn0);
    spn0.setBounds(120, 110, 64, 22);

    spn1.addChangeListener(new SpinnerListener(this));
    add(spn1);
    spn1.setBounds(120, 140, 64, 22);

    spn2.addChangeListener(new SpinnerListener(this));
		add(spn2);
		spn2.setBounds(120, 170, 64, 22);

    spn3.addChangeListener(new SpinnerListener(this));
		add(spn3);
    spn3.setBounds(280, 110, 64, 22);

    spn4.addChangeListener(new SpinnerListener(this));
    add(spn4);
    spn4.setBounds(280, 140, 64, 22);

    spn5.addChangeListener(new SpinnerListener(this));
    add(spn5);
    spn5.setBounds(280, 170, 64, 22);

    lblParam0.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblParam0.setForeground(new java.awt.Color(255, 255, 255));
    lblParam0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    lblParam0.setText("Dexterity");
    add(lblParam0);
    lblParam0.setBounds(10, 140, 100, 20);

    lblParam1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblParam1.setForeground(new java.awt.Color(255, 255, 255));
    lblParam1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    lblParam1.setText("Strength");
    add(lblParam1);
    lblParam1.setBounds(10, 110, 100, 20);

    lblParam2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblParam2.setForeground(new java.awt.Color(255, 255, 255));
    lblParam2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    lblParam2.setText("Constitution");
    add(lblParam2);
    lblParam2.setBounds(10, 170, 100, 20);

    lblParam3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblParam3.setForeground(new java.awt.Color(255, 255, 255));
    lblParam3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    lblParam3.setText("Intelligence");
    add(lblParam3);
    lblParam3.setBounds(170, 110, 100, 20);

    lblParam4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblParam4.setForeground(new java.awt.Color(255, 255, 255));
    lblParam4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    lblParam4.setText("Charisma");
    add(lblParam4);
    lblParam4.setBounds(170, 140, 100, 20);

    lblParam5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lblParam5.setForeground(new java.awt.Color(255, 255, 255));
    lblParam5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    lblParam5.setText("Blessing");
    add(lblParam5);
    lblParam5.setBounds(170, 170, 100, 20);

    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Remaining Points: 4");
    add(jLabel1);
    jLabel1.setBounds(110, 190, 180, 40);

    btnConfirm.setText("Confirm");
	  btnConfirm.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnConfirmActionPerformed(evt);
      }
    });
    add(btnConfirm);
    btnConfirm.setBounds(270, 260, 120, 22);

    imgBg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ui/dungeon.png"))); // NOI18N
    add(imgBg);
    imgBg.setBounds(0, 0, 400, 300);

		 updateStatus(); 
  }// </editor-fold>                        

	void updateStatus(){
		updateRemainingPoints();
	}

	void updateRemainingPoints(){
		jLabel1.setText("Remaining Points: "+((Actor)playerRef).statPoints);
	}
	
  void spnStateChanged(javax.swing.event.ChangeEvent evt) {             
    StatSpinner spinner = (StatSpinner)evt.getSource();
    int value = (int)spinner.getValue();
		int change = spinner.valueChanged;

		int statPoints = ((Actor)playerRef).statPoints;
		
		if (change == -1){
			spinner.lastValue = -1; //don't update again
			if (value < spinner.getParam()){
				spinner.setValue(value+1); return;
			} else {
				((Actor)playerRef).statPoints += 1;
			}
		}
		if (change == 1){
			spinner.lastValue = -1; //don't update again
			if (statPoints <= 0){
				spinner.setValue(value+-1); return;
			} else {
				((Actor)playerRef).statPoints += -1;
			}
		}
		
		change = 0;
		updateRemainingPoints();
  }

	void btnConfirmActionPerformed(java.awt.event.ActionEvent evt){
		int[] newParams = {0,0,0,0,0,0};
		newParams[0] = (int)spn0.getValue();
		newParams[1] = (int)spn1.getValue();
		newParams[2] = (int)spn2.getValue();
		newParams[3] = (int)spn3.getValue();
		newParams[4] = (int)spn4.getValue();
		newParams[5] = (int)spn5.getValue();
		((Actor) game.player).levelUpParams(newParams);
		
		game.doMainScene();
	}

}

class StatSpinner extends javax.swing.JSpinner{
	int param;
	public int lastValue = -1;
	public int valueChanged = 0; //-1 or 1 for changes.
	
	public StatSpinner(int paramIn){
		this.param = paramIn;
		this.lastValue = this.param;
		this.setValue(this.param);
	}
	
	public void setValue(Object valueIn){
		super.setValue(valueIn);
		this.lastValue = (int)getValue();
	}
	
	public int getParam() { return this.param; }
}

class SpinnerListener implements ChangeListener {
	PanelLevelUp panel;
	
	public SpinnerListener(PanelLevelUp panelIn){
		this.panel = panelIn;
	}
	
	public void stateChanged(ChangeEvent evt) {
		StatSpinner spn = (StatSpinner)evt.getSource();
		
		//lastValue = -1 if shouldn't actually update.
		if ((spn.lastValue != -1) && ((int)spn.getValue()-spn.lastValue) != 0){
			spn.valueChanged = (int)spn.getValue() - spn.lastValue;
			//System.out.println(spn.valueChanged);
			panel.spnStateChanged(evt);
		}
    spn.lastValue = (int)spn.getValue();
  }
	
}