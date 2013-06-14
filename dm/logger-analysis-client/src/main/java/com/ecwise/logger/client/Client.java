package com.ecwise.logger.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.angelo.logging.db.DataCentre;
import com.angelo.logging.templete.StringRule;
import com.angelo.logging.templete.Templete;

public class Client extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton addBtn;
	private DataCentre dataCentre;
	private JTextField urlTextField;
	private JTextField userNameTextField;
	private JTextField passwordTextField;
	private JTextField titleTextField;
	private JTextField categoryTextField;
	private JTextArea rcaTextArea;
	private JTextArea reproduceStepsTextArea;
	private JTextArea rulesTextArea;
	private JLabel infoLabel;

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setSize(1024, 768);
		jf.setTitle("Logger Analysis Terminal");
		jf.getContentPane().add(new Client());
		jf.setResizable(false);
		jf.setVisible(true);
		
		jf.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public Client() {
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(1024, 768));
		{
			// left panel
			JPanel leftPanel = new JPanel();
			leftPanel.setLayout(null);
			leftPanel.setPreferredSize(new Dimension(200, 600));
			leftPanel.setBorder(new TitledBorder("Database Info"));
			this.add(leftPanel, BorderLayout.WEST);
			{
				{
					JLabel urlLabel = new JLabel("JDBC URL");
					urlLabel.setBounds(10, 25, 150, 20);
					leftPanel.add(urlLabel);
				}

				{
					urlTextField = new JTextField();
					urlTextField.setBounds(10, 55, 150, 20);
					urlTextField.setText("jdbc:h2:tcp://192.168.3.225/../h2/data");
					leftPanel.add(urlTextField);
				}

				{
					JLabel userNameLabel = new JLabel("UserName");
					userNameLabel.setBounds(10, 85, 150, 20);
					leftPanel.add(userNameLabel);
				}

				{
					userNameTextField = new JTextField();
					userNameTextField.setBounds(10, 115, 150, 20);
					userNameTextField.setText("sa");
					leftPanel.add(userNameTextField);
				}

				{
					JLabel passwordLabel = new JLabel("Password");
					passwordLabel.setBounds(10, 145, 150, 20);
					leftPanel.add(passwordLabel);
				}

				{
					passwordTextField = new JTextField();
					passwordTextField.setBounds(10, 175, 150, 20);
					leftPanel.add(passwordTextField);
				}
			}
			// center panel
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(null);
			centerPanel.setPreferredSize(new Dimension(600, 600));
			this.add(centerPanel, BorderLayout.CENTER);
			{

				{
					JLabel titleLabel = new JLabel("Title");
					titleLabel.setBounds(10, 25, 150, 20);
					centerPanel.add(titleLabel);
				}

				{
					titleTextField = new JTextField();
					titleTextField.setBounds(10, 55, 300, 20);
					centerPanel.add(titleTextField);
				}
				
				{
					JLabel categoryLabel = new JLabel("Category");
					categoryLabel.setBounds(10, 85, 150, 20);
					centerPanel.add(categoryLabel);
				}
				
				{
					categoryTextField = new JTextField();
					categoryTextField.setBounds(10, 115, 300, 20);
					centerPanel.add(categoryTextField);
				}
				
				{
					JLabel rcaLabel = new JLabel("RCA");
					rcaLabel.setBounds(10, 145, 150, 20);
					centerPanel.add(rcaLabel);
				}
				
				{
					rcaTextArea = new JTextArea();
					rcaTextArea.setBounds(10, 175, 350, 150);
					centerPanel.add(rcaTextArea);
				}
				
				{
					JLabel reproduceStepsLabel = new JLabel("Reproduce Steps");
					reproduceStepsLabel.setBounds(390, 145, 150, 20);
					centerPanel.add(reproduceStepsLabel);
				}
				
				{
					reproduceStepsTextArea = new JTextArea();
					reproduceStepsTextArea.setBounds(390, 175, 350, 150);
					centerPanel.add(reproduceStepsTextArea);
				}
				
				
				{
					reproduceStepsTextArea = new JTextArea();
					reproduceStepsTextArea.setBounds(390, 175, 350, 150);
					centerPanel.add(reproduceStepsTextArea);
				}

				{
					JLabel rulesLabel = new JLabel("Rules");
					rulesLabel.setBounds(10, 350, 150, 20);
					centerPanel.add(rulesLabel);
				}
				
				{
					rulesTextArea = new JTextArea();
					rulesTextArea.setBounds(10, 380, 730, 150);
					centerPanel.add(rulesTextArea);
				}
				
				{
					addBtn = new JButton("Add");
					addBtn.setBounds(400, 550, 80, 50);
					addBtn.addActionListener(new AddTempleteListener());
					centerPanel.add(addBtn);
				}
				
				{
					infoLabel = new JLabel("");
					infoLabel.setBounds(10, 650, 730, 50);
					centerPanel.add(infoLabel);
				}
			}
		}

	}
	
	private void clean() {
		titleTextField.setText("");
		categoryTextField.setText("");
		rcaTextArea.setText("");
		reproduceStepsTextArea.setText("");
		rulesTextArea.setText("");
	}

	private class AddTempleteListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String url = urlTextField.getText().trim();
			String userName = userNameTextField.getText().trim();
			String password = passwordTextField.getText().trim();
			
			if(url.isEmpty() || userName.isEmpty()){
				infoLabel.setText("Please input JDBC info.");
				return ;
			}else{
				infoLabel.setText("");
			}
			
			String title = titleTextField.getText().trim();
			String category = categoryTextField.getText().trim();
			String rCA = rcaTextArea.getText().trim();
			String reProduceSteps = reproduceStepsTextArea.getText().trim();
			String rules = rulesTextArea.getText().trim();
			
			if(title.length() == 0 && rules.length() == 0){
				infoLabel.setText("Please input title and Rules.");
				return ;
			}else{
				infoLabel.setText("");
			}
			
			if (e.getSource() == addBtn) {
				dataCentre = new DataCentre(url, userName, password);

				Templete templete = new Templete();
				templete.setTitle(title);
				templete.setCategory(category);
				// templete.setName(name);
				templete.setRCA(rCA);
				templete.setReProduceSteps(reProduceSteps);
				templete.setTimestamp(new Date());

				StringRule rule = new StringRule();
				rule.setFeature(rules);

				try {
					dataCentre.addTempleteRule(templete, rule);
				} catch (Exception e1) {
					infoLabel.setText("Please confirm JDBC info.");
					return ;
				}
				infoLabel.setText("Add successfully.");
				clean();
			}
		}
	}
}
