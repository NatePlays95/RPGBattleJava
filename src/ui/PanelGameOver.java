package src.ui;

//feito com NetBeans
public class PanelGameOver extends javax.swing.JPanel{
	private javax.swing.JLabel jLabel1;
  
	public PanelGameOver() {
        initComponents();
    }
  
	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		setBackground(new java.awt.Color(0, 0, 0));
		setMaximumSize(new java.awt.Dimension(400, 300));
		setMinimumSize(new java.awt.Dimension(400, 300));
		setPreferredSize(new java.awt.Dimension(400, 300));
		jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(255, 0, 0));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("GAME OVER");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
						.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(125, 125, 125)
						.addComponent(jLabel1)
						.addContainerGap(127, Short.MAX_VALUE))
		);
	}
}