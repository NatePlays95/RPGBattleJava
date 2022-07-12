package src.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import src.managers.GameManager;

public class PanelNewCharacter extends PanelGame{
  
	private javax.swing.JButton btnCreate;
	private javax.swing.JButton btnReturn;
  private javax.swing.JLabel imgTitle;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JList<String> jList1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JTextField jTextField1;
  private javax.swing.JLabel lblTitle;
	
	// JTextField textField;
	// JButton btnConfirm;
	// private final static String newline = "\n";
	
	public PanelNewCharacter(GameManager gameIn){
		super(gameIn);
		initComponents();
		updateStatsDisplay();
	}
                      
private void initComponents() {

    lblTitle = new javax.swing.JLabel();
    btnCreate = new javax.swing.JButton();
		btnReturn = new javax.swing.JButton();
    jTextField1 = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList<>();
    jLabel3 = new javax.swing.JLabel();
    jTextArea1 = new javax.swing.JTextArea();
    imgTitle = new javax.swing.JLabel();

    setMaximumSize(new java.awt.Dimension(400, 300));
    setMinimumSize(new java.awt.Dimension(400, 300));
    setPreferredSize(new java.awt.Dimension(400, 300));
    setLayout(null);

    lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblTitle.setText("Create a new Hero");
    add(lblTitle);
    lblTitle.setBounds(30, 10, 340, 30);

    btnCreate.setText("Create!");
    add(btnCreate);
    btnCreate.setBounds(260, 270, 100, 22);
		btnCreate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        onConfirmCreate(evt);
      }
    });
		btnReturn.setText("Return to Title");
    add(btnReturn);
    btnReturn.setBounds(40, 270, 140, 22);
		btnReturn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        onReturnTitle(evt);
      }
    });

    jTextField1.setText("");
    add(jTextField1);
    jTextField1.setBounds(110, 70, 180, 22);

    jLabel1.setText("Name:");
    add(jLabel1);
    jLabel1.setBounds(110, 50, 80, 16);

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
    jLabel2.setText("Class Preset");
    add(jLabel2);
    jLabel2.setBounds(220, 100, 90, 16);

		//jList1.setFont(new java.awt.Font("Segoe UI", 0, 11));
    jList1.setBackground(new java.awt.Color(242, 222, 190));
    jList1.setModel(new javax.swing.AbstractListModel<String>() {
      String[] strings = { "Default", "Fighter", "Wizard", "Rogue", "Berserker", "Priest", "Warlock" };
      public int getSize() { return strings.length; }
      public String getElementAt(int i) { return strings[i]; }
    });
		jList1.setSelectedIndex(0);
		jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        onClassSelected(evt);
      }
    });
    jScrollPane1.setViewportView(jList1);

    add(jScrollPane1);
    jScrollPane1.setBounds(230, 120, 80, 130);
	
    jLabel3.setText("Statistics");
    add(jLabel3);
    jLabel3.setBounds(90, 100, 70, 16);

    jTextArea1.setColumns(20);
    jTextArea1.setRows(6);
    jTextArea1.setText("Strength - 1\nDexterity - 2\nConstitution - 1\nIntelligence - 0\nCharisma - 0\nLuck - 3");
    jTextArea1.setToolTipText("");
    jTextArea1.setAlignmentX(1.0F);
    jTextArea1.setOpaque(false);
		jTextArea1.setEditable(false);
    add(jTextArea1);
    jTextArea1.setBounds(90, 120, 100, 120);

    imgTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ui/charcreation.png"))); // NOI18N
    add(imgTitle);
    imgTitle.setBounds(0, 0, 400, 300);
  }

	int[] getPresetStats(){
		int[] params;
		String preset = jList1.getSelectedValue();
		if (preset == null) preset = "";
		switch(preset){
			case "Fighter":
				params = new int[]{2,2,1,0,1,0}; break;
			case "Wizard":
				params = new int[]{1,2,0,2,0,1}; break;
			case "Rogue":
				params = new int[]{0,2,0,1,2,0}; break;
			case "Berserker":
				params = new int[]{2,1,2,0,0,1}; break;
			case "Priest":
				params = new int[]{1,0,0,2,1,2}; break;
			case "Warlock":
				params = new int[]{1,0,0,1,2,2}; break;
			default:
				params = new int[]{1,1,1,1,1,1}; break;
		}
		return params;
	}

	void updateStatsDisplay(){
		int[] params = getPresetStats();
		String text = "Strength - "+params[0]+"\n";
		text += "Dexterity - "+params[1]+"\n";
		text += "Constitution - "+params[2]+"\n";
		text += "Intelligence - "+params[3]+"\n";
		text += "Charisma - "+params[4]+"\n";
		text += "Blessing - "+params[5];
		jTextArea1.setText(text);
	}

	public void onClassSelected(javax.swing.event.ListSelectionEvent evt){
		updateStatsDisplay();
	}
	
	public void onConfirmCreate(ActionEvent evt){
		String name = jTextField1.getText();
		String tempName = name.replaceAll("[^a-zA-Z0-9]", " ").replaceAll(" ", "");
		if (tempName.isEmpty()) return;
		
		int[] preset = getPresetStats();
		game.createPlayer(name, preset);
	}

	public void onReturnTitle(ActionEvent evt){
		game.doTitleScene();
	}


	
}