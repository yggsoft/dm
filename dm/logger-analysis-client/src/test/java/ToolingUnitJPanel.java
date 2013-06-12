
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import mainframe.MainForm;
import sunsdk.sql.ConnectDb;
import sunsdk.sql.Journal;
import sunsdk.sql.SqlTools;
import sunsdk.swing.customComponent.IPanelFunction;
import sunsdk.swing.customComponent.JStarLabel;
import sunsdk.system.GetLangue.GetWord;
import sunsdk.system.Table.AndUpDelTable.ShowTableWithSQLJPanel;
import sunsdk.system.Tool.KeyOpt;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * @author 杨国钢
 * 
 * @function 零件单位维护
 * 
 */
@SuppressWarnings("serial")
public class ToolingUnitJPanel extends javax.swing.JPanel implements IPanelFunction {
	private JPanel formPanel;

	private JPanel outsidePanel;

	private ShowTable tablePanel;

	private JTextField descriptionTextField;

	private JTextField unitTextField;

	private JLabel descriptionLabel;

	private JLabel unitLabel;

	private JTextField descriptionSearchTextField;

	private JLabel descriptionSearchLabel;

	private JTextField unitSearchTextField;

	private JLabel unitSearchLabel;

	private JPanel searchPanel;

	private JLabel jLabel3;

	private JButton searchButton;

	private JButton addButton;

	private JButton editButton;

	private JButton deleteButton;

	private JButton saveButton;

	private int editFlag = 0;

	private String unit_id;

	private String unit;

	private String description;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new ToolingUnitJPanel( ));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public ToolingUnitJPanel( ) {
		super();
		initGUI();
		initFormValue();
		setFormEditable(false);
		setButtonEnabled(true, false, false, false);
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(780, 500));
			{
				formPanel = new JPanel();
				this.add(formPanel, BorderLayout.SOUTH);
				formPanel.setLayout(null);
				formPanel.setBorder(BorderFactory.createTitledBorder(""));
				formPanel.setPreferredSize(new java.awt.Dimension(780, 63));
				{
					jLabel3 = new JStarLabel();
					formPanel.add(jLabel3);
					jLabel3.setBounds(358, 7, 10, 22);

				}
				{
					unitLabel = new JLabel("unit:");
					formPanel.add(unitLabel);
					unitLabel.setText(GetWord.GetLangue("unit", MainForm.language) + ":");
					unitLabel.setBounds(10, 7, 108, 22);
				}
				{
					descriptionLabel = new JLabel("description:");
					formPanel.add(descriptionLabel);
					descriptionLabel.setText(GetWord.GetLangue("description", MainForm.language) + ":");
					descriptionLabel.setBounds(419, 7, 108, 22);
				}
				{
					unitTextField = new JTextField();
					formPanel.add(unitTextField);
					unitTextField.setText("");
					unitTextField.setBounds(120, 7, 237, 22);
					unitTextField.addKeyListener(this);
				}
				{
					descriptionTextField = new JTextField();
					formPanel.add(descriptionTextField);
					descriptionTextField.setText("");
					descriptionTextField.setBounds(527, 7, 237, 22);
					descriptionTextField.addKeyListener(this);
				}
				{
					addButton = new JButton("Add", new ImageIcon("images/iButton/icon_add.png"));
					formPanel.add(addButton);
					addButton.setText(GetWord.GetLangue("Add", MainForm.language));
					addButton.setEnabled(false);
					addButton.setBounds(395, 35, 88, 21);
					addButton.addActionListener(this);
				}
				{
					editButton = new JButton("Edit", new ImageIcon("images/iButton/icon_edit.png"));
					formPanel.add(editButton);
					editButton.setText(GetWord.GetLangue("Edit", MainForm.language));
					editButton.setEnabled(false);
					editButton.setBounds(490, 35, 89, 21);
					editButton.addActionListener(this);
				}
				{
					deleteButton = new JButton("Delete", new ImageIcon("images/iButton/icon_del.png"));
					formPanel.add(deleteButton);
					deleteButton.setText(GetWord.GetLangue("Delete", MainForm.language));
					deleteButton.setEnabled(false);
					deleteButton.setBounds(585, 35, 90, 21);
					deleteButton.addActionListener(this);
				}
				{
					saveButton = new JButton("Save", new ImageIcon("images/iButton/icon_save.png"));
					formPanel.add(saveButton);
					saveButton.setText(GetWord.GetLangue("Save", MainForm.language));
					saveButton.setPreferredSize(new java.awt.Dimension(91, 21));
					saveButton.setBounds(680, 35, 91, 21);
					saveButton.addActionListener(this);
				}
			}
			{
				outsidePanel = new JPanel();
				BorderLayout jPanel2Layout = new BorderLayout();
				this.add(outsidePanel, BorderLayout.CENTER);
				outsidePanel.setLayout(jPanel2Layout);
				outsidePanel.setPreferredSize(new java.awt.Dimension(780, 396));
				outsidePanel.setBorder(BorderFactory.createTitledBorder(""));
				{
					String selectSql = "select unit_id,unit, description from p_tooling_unit order by unit_id desc";
					String tableHeader[] = { GetWord.GetLangue("unit_id", MainForm.language), 
							GetWord.GetLangue("unit", MainForm.language),
							GetWord.GetLangue("description", MainForm.language) };
					tablePanel = new ShowTable(selectSql, tableHeader, 30, null);
					outsidePanel.add(tablePanel, BorderLayout.CENTER);
				}
			}
			{
				searchPanel = new JPanel();
				this.add(searchPanel, BorderLayout.NORTH);
				searchPanel.setPreferredSize(new java.awt.Dimension(770, 38));
				searchPanel.setLayout(null);
				{
					unitSearchLabel = new JLabel("unit");
					searchPanel.add(unitSearchLabel);
					unitSearchLabel.setText(GetWord.GetLangue("unit", MainForm.language) + ":");
					unitSearchLabel.setBounds(10, 10, 100, 21);
				}
				{
					unitSearchTextField = new JTextField();
					searchPanel.add(unitSearchTextField);
					unitSearchTextField.setText("");
					unitSearchTextField.setBounds(110, 10, 161, 21);
					unitSearchTextField.addKeyListener(this);
				}
				{
					descriptionSearchLabel = new JLabel("description");
					searchPanel.add(descriptionSearchLabel);
					descriptionSearchLabel.setText(GetWord.GetLangue("description", MainForm.language) + ":");
					descriptionSearchLabel.setBounds(327, 10, 100, 21);
				}
				{
					descriptionSearchTextField = new JTextField();
					searchPanel.add(descriptionSearchTextField);
					descriptionSearchTextField.setText("");
					descriptionSearchTextField.setBounds(427, 10, 161, 21);
					descriptionSearchTextField.addKeyListener(this);
				}
				{
					searchButton = new JButton("Search", new ImageIcon("images/iButton/icon_search.png"));
					searchPanel.add(searchButton);
					searchButton.setText(GetWord.GetLangue("Search", MainForm.language));
					searchButton.setBounds(677, 10, 91, 21);
					searchButton.addActionListener(this);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class ShowTable extends ShowTableWithSQLJPanel {
		public ShowTable(String Sql, String[] tableHeader, int everyLine, ConnectDb MyConnect) {
			super(Sql, tableHeader, everyLine, MyConnect);
		}

		public void doElsething() {
			if (this.getIfHave() == 1) {
				setFormEditable(false);
				setButtonEnabled(true, true, true, false);
				int Row = this.table.getSelectedRow();
				unit_id = this.table.getValueAt(Row, 0).toString();
				unitTextField.setText(this.table.getValueAt(Row, 1).toString().trim());
				descriptionTextField.setText(this.table.getValueAt(Row, 2).toString().trim());
			} else {
				setFormEditable(false);
				setButtonEnabled(true, false, false, false);
			}
		}

		public void setTableAttribute() { // 加滚动条
			// this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			this.table.getColumnModel().getColumn(0).setMinWidth(0);
			this.table.getColumnModel().getColumn(0).setMaxWidth(0);
		}
	}

	public void doSearch() {
		getSearchFormValue();
		String Sql = "select unit_id,unit, description from p_tooling_unit where 1=1";
		if (!unit.equals("")) {
			Sql += " and UPPER(unit) like '%'||UPPER('" + unit + "')||'%'";
			unitSearchTextField.requestFocus();
		}
		if (!description.equals("")) {
			Sql += " and UPPER(description) like '%'||UPPER('" + description + "')||'%'";
			descriptionSearchTextField.requestFocus();
		}
		Sql = Sql + "  order by unit_id desc";
		tablePanel.SetSql(Sql);
		tablePanel.Reload();
		setFormEditable(false);
		setButtonEnabled(true, false, false, false);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == addButton) {// Add
			editFlag = 0;
			initFormValue();
			setFormEditable(true);
			setButtonEnabled(false, false, false, true);
			unitTextField.requestFocus();
		}
		if (arg0.getSource() == editButton) {// update
			editFlag = 1;
			setFormEditable(true);
			setButtonEnabled(false, false, false, true);
			unitTextField.requestFocus();
		}
		if (arg0.getSource() == deleteButton) {
			doDelete();
		}

		if (arg0.getSource() == saveButton) {
			doSave();
		}
		if (arg0.getSource() == searchButton) {
			doSearch();
		}
	}

	public void doDelete() {
		int result = JOptionPane.showConfirmDialog(this, GetWord.GetLangue("ConfirmDelete", MainForm.language), GetWord.GetLangue("SystemTitle", MainForm.language), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (result == 0) {
			if(isUsed()){
				JOptionPane.showMessageDialog(this, GetWord.GetLangue("CannotDelete", MainForm.language), GetWord.GetLangue("SystemTitle", MainForm.language), JOptionPane.INFORMATION_MESSAGE);
			}else{
				Vector<String> allSql = new Vector<String>();
				String delSql = "delete p_tooling_unit where unit_id='" + unit_id + "'";
				String logSql = Journal.writeJournalInfo(GetWord.GetLangue("MaintenancePartUnit", MainForm.language), MainForm.user_name, GetWord.GetLangue("DeleteRecord", MainForm.language) + "[" + unit + "]");
				allSql.add(delSql);
				allSql.add(logSql);
				if (SqlTools.executeBatch(allSql)) {
					JOptionPane.showMessageDialog(this, GetWord.GetLangue("OperationSuccess", MainForm.language), GetWord.GetLangue("SystemTitle", MainForm.language), JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, GetWord.GetLangue("OperationFailure", MainForm.language), GetWord.GetLangue("SystemTitle", MainForm.language), JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		}
		setFormEditable(false);
		setButtonEnabled(true, false, false, false);
		tablePanel.Reload();
	}

	public boolean isStateAllowed() {
		return false;
	}

	public boolean checkFormValue() {
		getFormValue();
		if (unit.equals("")) {
			JOptionPane.showMessageDialog(this, GetWord.GetLangue("PleaseInput", MainForm.language) + GetWord.GetLangue("unit", MainForm.language), GetWord.GetLangue("SystemTitle", MainForm.language), JOptionPane.INFORMATION_MESSAGE);
			unitTextField.requestFocus();
			return true;
		}
		if (isOnly()) {
			JOptionPane.showMessageDialog(this, GetWord.GetLangue("unit", MainForm.language)+GetWord.GetLangue("Existed", MainForm.language), GetWord.GetLangue("SystemTitle", MainForm.language), JOptionPane.INFORMATION_MESSAGE);
			unitTextField.requestFocus();
			return true;
		}
		return false;
	}

	public void getFormValue() {
		unit = unitTextField.getText().toString().replace("'", "''");
		description = descriptionTextField.getText().toString().replace("'", "''");
	}

	public void getSearchFormValue() {
		unit = unitSearchTextField.getText().toString().replace("'", "''");
		description = descriptionSearchTextField.getText().toString().replace("'", "''");
	}

	public void doSave() {
		if (checkFormValue()) {
			return;
		}
		Vector<String> allSql = new Vector<String>();
		if (editFlag == 0) {
			unit_id = SqlTools.getPrimaryKey("L_STOCK_UNIT_ID", "DW");
			String insertSql ="insert into p_tooling_unit  (unit_id,unit, description)values ('"+unit_id+"','"+unit+"','"+description+"')";
			String logSql = Journal.writeJournalInfo(GetWord.GetLangue("MaintenancePartUnit", MainForm.language), MainForm.user_name, GetWord.GetLangue("AddRecord", MainForm.language) + "[" + unit + "]");
			allSql.add(insertSql);
			allSql.add(logSql);

		}
		if (editFlag == 1) {
			String updateSql = "update p_tooling_unit   set unit='"+unit+"', description='"+description+"' where unit_id='"+unit_id+"'";
			String logSql = Journal.writeJournalInfo(GetWord.GetLangue("MaintenancePartUnit", MainForm.language), MainForm.user_name, GetWord.GetLangue("AlterRecord", MainForm.language) + "[" + unit + "]");
			allSql.add(updateSql);
			allSql.add(logSql);
		}
		if (SqlTools.executeBatch(allSql)) {
			JOptionPane.showMessageDialog(this, GetWord.GetLangue("OperationSuccess", MainForm.language), GetWord.GetLangue("SystemTitle", MainForm.language), JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, GetWord.GetLangue("OperationFailure", MainForm.language), GetWord.GetLangue("SystemTitle", MainForm.language), JOptionPane.INFORMATION_MESSAGE);
		}
		setFormEditable(false);
		setButtonEnabled(true, false, false, false);
		tablePanel.Reload();
	}

	public void initFormValue() {
		unitTextField.setText("");
		descriptionTextField.setText("");
		unitSearchTextField.setText("");
		descriptionSearchTextField.setText("");
		tablePanel.Reload();
	}

	public void setButtonEnabled(boolean Add, boolean Update, boolean Delete, boolean Save) {
		addButton.setEnabled(Add);
		editButton.setEnabled(Update);
		deleteButton.setEnabled(Delete);
		saveButton.setEnabled(Save);
	}

	public void setFormEditable(boolean b) {
		unitTextField.setEditable(b);
		descriptionTextField.setEditable(b);
	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void keyPressed(KeyEvent arg0) {
		JTextField[] TextField = { unitTextField, descriptionTextField };
		KeyOpt.SendKey(arg0, TextField);

		if (arg0.getSource() == unitSearchTextField || arg0.getSource() == descriptionSearchTextField) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				doSearch();
			}
		}

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void doSubmit() {
		// TODO 自动生成方法存根
		
	}

	public boolean isOnly() {
		String uniqueSql = "select *  from p_tooling_unit  where unit='"+unit+"'";
		if (editFlag == 1) {
			uniqueSql += " and unit_id!='" + unit_id + "'";
		}
		if (SqlTools.IfExist(uniqueSql)) {
			return true;
		}
		return false;
	}

	public boolean isUsed() {
		//数据库中使用的不是单位的引用，此表只是方便的进行数据录入，所以删除验证可以取消，但是不删除数据有利于操作地方便性。
		//单位换算需要，所以要验证单位换算。
		String useSql = "select list_unit from l_stock_part_unit_conver where list_unit='"+unit+"' union select true_unit from l_stock_part_unit_conver where true_unit='"+unit+"' ";
		if (SqlTools.IfExist(useSql)) {
			return true;
		}
		return false;
	}

}
