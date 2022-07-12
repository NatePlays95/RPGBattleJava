package src.ui;

import java.util.LinkedList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.objects.EBattlerAction;
import src.objects.BattleAction;
import src.managers.BMInput; 
import src.managers.BMOutput; 
import src.managers.BattleManager;
import src.managers.GameManager;
import src.util.Sleep;

public class PanelBattle extends PanelGame implements BMInput, BMOutput{
	
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextPane1;
	private javax.swing.JLabel lblEnemyStatus;
	private javax.swing.JLabel lblPlayerStatus;
	private PanelBattleActions panelActions;
	private javax.swing.JLabel imgEnemy;
	private javax.swing.JLabel imgBg;

	//salvar as prints
	LinkedList<String> battleLog = new LinkedList<String>();
	
	EBattlerAction actionChoice = EBattlerAction.NONE;
	
	public PanelBattle(GameManager gameIn) {
    super(gameIn);
		initComponents();
  }
	
  private void initComponents() {
    panelActions = new PanelBattleActions();
    lblPlayerStatus = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextPane1 = new javax.swing.JTextArea();
    lblEnemyStatus = new javax.swing.JLabel();
    imgEnemy = new javax.swing.JLabel();
    imgBg = new javax.swing.JLabel();
		Font fontStatus = new Font("SansSerif", Font.BOLD, 14);

    setMaximumSize(new java.awt.Dimension(400, 300));
    setMinimumSize(new java.awt.Dimension(400, 300));
    setPreferredSize(new java.awt.Dimension(400, 300));
    setLayout(null);

    panelActions.setMaximumSize(new java.awt.Dimension(320, 100));
    panelActions.setMinimumSize(new java.awt.Dimension(320, 100));
    panelActions.setOpaque(false);
    panelActions.setPreferredSize(new java.awt.Dimension(320, 100));
		panelActions.hideButtons();
    add(panelActions);
    panelActions.setBounds(6, 176, 388, 94);

    lblPlayerStatus.setFont(fontStatus); // NOI18N
    lblPlayerStatus.setForeground(new java.awt.Color(255, 255, 255));
    lblPlayerStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblPlayerStatus.setText("Player Character - 10/10 HP");
    add(lblPlayerStatus);
    lblPlayerStatus.setBounds(6, 276, 388, 19);

		jTextPane1.setText("Player Character attacks!");
		jTextPane1.setOpaque(false);
		jTextPane1.setEditable(false);
		jScrollPane1.setViewportView(jTextPane1);
    add(jScrollPane1);
    jScrollPane1.setBounds(0, 0, 400, 36);

    lblEnemyStatus.setFont(fontStatus); // NOI18N
    lblEnemyStatus.setForeground(new java.awt.Color(255, 255, 255));
    lblEnemyStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblEnemyStatus.setText("Enemy Character - 0/0 HP");
    add(lblEnemyStatus);
    lblEnemyStatus.setBounds(6, 49, 388, 19);

    imgEnemy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    imgEnemy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/battlers/_empty.png"))); // NOI18N
    add(imgEnemy);
    imgEnemy.setBounds(6, 74, 388, 96);

    imgBg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ui/arena2.png"))); // NOI18N
    add(imgBg);
    imgBg.setBounds(0, 0, 400, 300);
  }
	
	//INPUT
	public EBattlerAction enterChooseAction(){
		EBattlerAction retAction = null;
		
		panelActions.canChooseAction = true; //agora os botoes funcionam
		panelActions.showButtons();

		//esperar entrada
		while (panelActions.canChooseAction){
			Sleep.sleep(1); //loops precisam de sleep pra funcionar
			//detectar entrada
			if (actionChoice != EBattlerAction.NONE){
				retAction = actionChoice; actionChoice = EBattlerAction.NONE;
				panelActions.canChooseAction = false; //agora os botoes nao funcionam
				panelActions.hideButtons();
				break;
			}
			
		}
		return retAction;
	}	
	
	void updateStatus(BattleManager bm){
		lblEnemyStatus.setText(String.format("%s - %s", bm.enemy.name, bm.enemy.hpText()));
		lblPlayerStatus.setText(String.format("%s - %s", bm.actor.name, bm.actor.hpText()));
	}
	
	void updateBattleLog(String str){
		String lastNewStr = battleLog.peekLast();
		if (lastNewStr == null) lastNewStr = "";
		String newStr = str;
		battleLog.add(newStr);
		jTextPane1.setText(lastNewStr+"\n"+newStr);
	}


	//OUTPUT
	public void displayStart(BattleManager bm){
		updateStatus(bm);
		
		//load enemy image
		java.net.URL imgURL = getClass().getResource("/img/battlers/"+bm.enemy.name+".png");
		if (imgURL != null) imgEnemy.setIcon(new ImageIcon(imgURL));
		
		updateBattleLog("BATTLE START");
		Sleep.sleep(1000);
	}
	public void displayEnd(BattleManager bm){
		updateBattleLog("BATTLE END");
		Sleep.sleep(1000);
	}
	public void displayTurn(BattleManager bm){
		updateStatus(bm);
		updateBattleLog(String.format("Turn %d", bm.turnCount));
		Sleep.sleep(500);
	}

	public void displayEscape(BattleManager bm){
		updateBattleLog(bm.actor.name + " escaped successfully!");
	}
	public void displayDefeat(BattleManager bm){
		updateBattleLog(bm.actor.name + " was defeated. You lost.");
	}
	public void displayVictory(BattleManager bm, int goldYield, int expYield){
		Sleep.sleep(500);
		updateBattleLog(bm.enemy.name + " was defeated! You won!");

		//hide enemy image
		//imgEnemy.setIcon(new ImageIcon(getClass().getResource("/img/battlers/_empty.png")));
		imgEnemy.setVisible(false);
		
		Sleep.sleep(1000);
		String str = bm.actor.name + " got " + goldYield + " gold and " + expYield + " exp!"; 
		updateBattleLog(str);
	}

	public void displayActionAttack(BattleManager bm, BattleAction action){
		String name = action.user.name;
		updateBattleLog(name + " attacks!");
		Sleep.sleep(500);
		updateStatus(bm);
		if (!action.wasSuccess){
				updateBattleLog("but it missed!");
		} else {
			if (action.value == 0){
				updateBattleLog("Hits, but deals no damage.");
			} else {
				String str = String.format("Hits for %d damage!", action.value);
				updateBattleLog(str);
			}
		}
		Sleep.sleep(1000);
	}
	public void displayActionMagic(BattleManager bm, BattleAction action){
		String name = action.user.name;
		int mag = action.user.getParam(3);
		//nome da magia muda com Inteligencia
		if (mag >= 12){
			updateBattleLog(name + " casts Fireball!");
		} else if (mag >= 6){
			updateBattleLog(name + " casts Blizzard!");
		} else {
			updateBattleLog(name + " casts Thunder!");
		}
		Sleep.sleep(500);
		updateStatus(bm);
		if (!action.wasSuccess || action.value == 0){
				updateBattleLog("but it fizzled out!");
		} else {
				String str = String.format("Hits for %d damage!", action.value);
				updateBattleLog(str);
		}
		Sleep.sleep(1000);
	}
	public void displayActionHeal(BattleManager bm, BattleAction action){
		String name = action.user.name;
		updateBattleLog(String.format(name+" heals themself for %d HP.", action.value));
		updateStatus(bm);
		Sleep.sleep(1000);
	}
	public void displayActionOther(BattleManager bm, BattleAction action){
		String name = action.user.name;
		switch (action.type){	
			case CHARGE:
				updateBattleLog(name+" charges a strong attack...");
				break;
			case GUARD:
				updateBattleLog(name+" raises their guard against incoming attacks.");
				break;
			case ESCAPE:
				updateBattleLog(name+" tries to escape...");
				Sleep.sleep(2000);
				break;
			default: break;
		}
		updateStatus(bm);
		Sleep.sleep(1000);
	}


	
	
}


class PanelBattleActions extends JPanel{
	public boolean canChooseAction = false;
	
	private JButton btnAttack;
	private JButton btnCharge;
	private JButton btnEscape;
	private JButton btnGuard;
	private JButton btnHeal;
	private JButton btnMagic;

	public PanelBattleActions() {
    initComponents();
		//par = (PanelBattle)getParent();
  }  
	
  private void initComponents() {
		btnAttack = new javax.swing.JButton();
		btnCharge = new javax.swing.JButton();
		btnGuard = new javax.swing.JButton();
		btnMagic = new javax.swing.JButton();
		btnHeal = new javax.swing.JButton();
		btnEscape = new javax.swing.JButton();

		setMaximumSize(new java.awt.Dimension(320, 90));
		setMinimumSize(new java.awt.Dimension(320, 90));
		setOpaque(false);
		setPreferredSize(new java.awt.Dimension(320, 90));

		btnAttack.setText("Attack");
		btnAttack.setPreferredSize(new java.awt.Dimension(100, 36));
		btnAttack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAttackActionPerformed(evt);
			}
		});
		add(btnAttack);
		
		btnCharge.setText("Charge");
		btnCharge.setPreferredSize(new java.awt.Dimension(100, 36));
		btnCharge.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnChargeActionPerformed(evt);
			}
		});
		add(btnCharge);
		
		btnGuard.setText("Guard");
		btnGuard.setPreferredSize(new java.awt.Dimension(100, 36));
		btnGuard.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnGuardActionPerformed(evt);
			}
		});
		add(btnGuard);
		
		btnMagic.setText("Magic");
		btnMagic.setPreferredSize(new java.awt.Dimension(100, 36));
		btnMagic.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMagicActionPerformed(evt);
			}
		});
		add(btnMagic);
		
		btnHeal.setText("Heal");
		btnHeal.setPreferredSize(new java.awt.Dimension(100, 36));
		btnHeal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnHealActionPerformed(evt);
			}
		});
		add(btnHeal);
		
		btnEscape.setText("Escape");
		btnEscape.setPreferredSize(new java.awt.Dimension(100, 36));
		btnEscape.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnEscapeActionPerformed(evt);
			}
		});
		add(btnEscape);
  } 

	public void hideButtons(){
		btnAttack.setVisible(false);
		btnCharge.setVisible(false);
		btnGuard.setVisible(false);
		btnMagic.setVisible(false);
		btnHeal.setVisible(false);
		btnEscape.setVisible(false);
	}
	public void showButtons(){
		btnAttack.setVisible(true);
		btnCharge.setVisible(true);
		btnGuard.setVisible(true);
		btnMagic.setVisible(true);
		btnHeal.setVisible(true);
		btnEscape.setVisible(true);
	}

	private void btnAttackActionPerformed(java.awt.event.ActionEvent evt) {
		if (canChooseAction) ((PanelBattle)getParent()).actionChoice = EBattlerAction.ATTACK;
	}
	private void btnChargeActionPerformed(java.awt.event.ActionEvent evt) {
		if (canChooseAction) ((PanelBattle)getParent()).actionChoice = EBattlerAction.CHARGE;
	}                                         
	private void btnGuardActionPerformed(java.awt.event.ActionEvent evt) {
		if (canChooseAction) ((PanelBattle)getParent()).actionChoice = EBattlerAction.GUARD;
	}                                        
	private void btnMagicActionPerformed(java.awt.event.ActionEvent evt) {
		if (canChooseAction) ((PanelBattle)getParent()).actionChoice = EBattlerAction.MAGIC;
	}                                        
	private void btnHealActionPerformed(java.awt.event.ActionEvent evt) {
		if (canChooseAction) ((PanelBattle)getParent()).actionChoice = EBattlerAction.HEAL;
	}                                       
	private void btnEscapeActionPerformed(java.awt.event.ActionEvent evt) {
		if (canChooseAction) ((PanelBattle)getParent()).actionChoice = EBattlerAction.ESCAPE;
	} 
}