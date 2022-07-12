package src.ui;

import java.io.File;
import java.util.Vector;
import src.managers.GameManager;

public class PanelTitle extends PanelGame{
	
	private javax.swing.JButton btnAbout;
	private javax.swing.JButton btnLoad;
	private javax.swing.JButton btnNew;
	private javax.swing.JLabel imgTitle;
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel lblTitle;

	public PanelTitle(GameManager gameIn) {
		super(gameIn);
		initComponents();

	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		btnLoad = new javax.swing.JButton();
		btnNew = new javax.swing.JButton();
		btnAbout = new javax.swing.JButton();
		jComboBox1 = new javax.swing.JComboBox<>();
		lblTitle = new javax.swing.JLabel();
		imgTitle = new javax.swing.JLabel();

		setMaximumSize(new java.awt.Dimension(400, 300));
		setMinimumSize(new java.awt.Dimension(400, 300));
		setPreferredSize(new java.awt.Dimension(400, 300));
		setLayout(null);

		btnLoad.setFont(new java.awt.Font("Segoe UI", 0, 12));
		btnNew.setFont(new java.awt.Font("Segoe UI", 0, 12));
		btnAbout.setFont(new java.awt.Font("Segoe UI", 0, 12));
		
		jPanel1.setMaximumSize(new java.awt.Dimension(400, 120));
    jPanel1.setOpaque(false);

    btnLoad.setText("Load Character");
		btnLoad.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnLoadActionPerformed(evt);
      }
    });

    btnNew.setText("New Character");
    btnNew.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNewActionPerformed(evt);
      }
    });

    btnAbout.setText("About");
    //btnAbout.addActionListener(new java.awt.event.ActionListener() {
    //  public void actionPerformed(java.awt.event.ActionEvent evt) {
    //    btnAboutActionPerformed(evt);
    //  }
    //});

		//preencher a jcombobox1
		loadList();
		
    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(btnLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(26, 26, 26)
        .addComponent(btnAbout)
        .addGap(114, 114, 114))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btnLoad)
          .addComponent(btnNew)
          .addComponent(btnAbout))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(64, Short.MAX_VALUE))
    );

    add(jPanel1);
    jPanel1.setBounds(0, 170, 400, 120);

    lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
    lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblTitle.setText("RPG Battle Simulator");
    add(lblTitle);
    lblTitle.setBounds(0, 10, 400, 32);

		imgTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ui/title.png"))); // NOI18N
		add(imgTitle);
		imgTitle.setBounds(0, 0, 400, 300);
	}


	void loadList(){
		File path = new File("./saves/");
		Vector<String> saves = new Vector<String>();
		for (final File fileEntry : path.listFiles()) {
			if (!fileEntry.isDirectory()){
				String saveName = fileEntry.getName();
				//System.out.println(saveName);
				if (saveName.endsWith(".dat")) saves.add(saveName);
			}
		}
		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(saves));
		if (saves.isEmpty()) {
			jComboBox1.setVisible(false);
			btnLoad.setEnabled(false);
		}
	}


	
	void btnNewActionPerformed(java.awt.event.ActionEvent evt){
		game.doNewCharacter();
	}
	void btnLoadActionPerformed(java.awt.event.ActionEvent evt){
		String filename = jComboBox1.getSelectedItem().toString();
		//System.out.println(jComboBox1.getSelectedItem());
		game.doLoadCharacter(filename);
	}
	
}