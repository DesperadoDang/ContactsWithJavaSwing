package hwadee.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hwadee.entity.User;
import hwadee.service.IUserIndexService;
import hwadee.service.IUserManageService;
import hwadee.service.impl.UserIndexServiceImpl;
import hwadee.service.impl.UserManageServiceImpl;
import hwadee.util.UIUtil;

public class ManageUserPane extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TABLE_ROW_NUM = 12;
	
	
	private JTextField searchContent;
	private JButton search;
	private JRadioButton byNickName,byRealName,byEmail,byNumber;
	private ButtonGroup methodGroup;
	private JLabel[] tableColName;
	private JButton[][] deleteButtons=null,editbuttons=null;
	private JButton addButton,exportButton,importButton,clearAll;
	private JTable userTable;
	private JLabel currentPageLabel;
	private JTextField pageJump;
	private JButton prePage,nextPage,jumpTo;
	private JFileChooser importChooser;
	
	private int currentPage = 1;
	private Object[][] objects;
	private int maxPage;
	private String[] cols = {"�û���","����","�Ա�","����","����Ա","����","����"};
	
	private JFrame frame;
	
	public ManageUserPane(JFrame frame) {
		this();
		this.frame = frame;
	}
	
	public ManageUserPane() {
		getInitUsers();
		getUsers();
			
		this.setLayout(null);
		
		searchContent = new JTextField();
		searchContent.setBounds(0, 0, 100, 25);
		this.add(searchContent);
		
		search = new JButton("����");
		search.setBounds(120, 0, 75, 25);
		search.addActionListener(this);
		this.add(search);
		
		byNickName = new JRadioButton("�û���",true);
		byNickName.setBounds(200, 0, 75, 25);
		this.add(byNickName);
		
		byRealName = new JRadioButton("����",true);
		byRealName.setBounds(270, 0, 75, 25);
		this.add(byRealName);
		
		byEmail = new JRadioButton("����");
		byEmail.setBounds(350, 0, 60, 25);
		this.add(byEmail);
		
		byNumber = new JRadioButton("����");
		byNumber.setBounds(410, 0, 75, 25);
		this.add(byNumber);
		
		methodGroup = new ButtonGroup();
		methodGroup.add(byNickName);
		methodGroup.add(byRealName);
		methodGroup.add(byEmail);
		methodGroup.add(byNumber);
		
		clearAll = new JButton("���");
		clearAll.setBounds(825, 0, 75, 25);
		clearAll.addActionListener(this);
		this.add(clearAll);
		
		addButton = new JButton("���");
		addButton.setBounds(500, 0, 75, 25);
		addButton.addActionListener(this);
		this.add(addButton);
		
		exportButton = new JButton("�����û�");
		exportButton.setBounds(600, 0, 100, 25);
		exportButton.addActionListener(this);
		this.add(exportButton);
		
		importButton = new JButton("�����û�");
		importButton.setBounds(700, 0, 100, 25);
		importButton.addActionListener(this);
		this.add(importButton);
		
		tableColName = new JLabel[7];
		for(int i=0;i<7;i++) {
			tableColName[i] = new JLabel(cols[i]);
			tableColName[i].setBounds(0+i*100, 30, 100, 25);
			this.add(tableColName[i]);
		}
		
		Object[][] showObjs;
		if(objects.length-(currentPage-1)*TABLE_ROW_NUM >= TABLE_ROW_NUM)
			showObjs = new Object[TABLE_ROW_NUM][9];
		else
			showObjs = new Object[objects.length-(currentPage-1)*TABLE_ROW_NUM][9];
		
		for(int i=0;i<showObjs.length;i++) {
			showObjs[i] = objects[(currentPage-1)*TABLE_ROW_NUM+i];
		}
			
		userTable = new JTable();
		userTable.setModel(new DefaultTableModel(showObjs,cols){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 7 || column == 8)
					return true;
				return false;
			}
			
		});
		userTable.setRowHeight(30);
		userTable.setBounds(0, 50, 700, 360);
		this.add(userTable);
		if(objects.length!=0) {
			for(int i=0;i<deleteButtons[currentPage-1].length;i++) {
				deleteButtons[currentPage-1][i].setBounds(720, 50+i*30, 60, 30);
				editbuttons[currentPage-1][i].setBounds(820, 50+i*30, 60, 30);
			}
		}
		
		currentPageLabel = new JLabel("��ǰλ�ڵ�"+currentPage+"/"+maxPage+"ҳ");
		currentPageLabel.setBounds(0, 410, 150, 25);
		this.add(currentPageLabel);
		
		prePage = new JButton("��һҳ");
		prePage.setBounds(150, 410, 100, 25);
		prePage.addActionListener(this);
		this.add(prePage);
		
		nextPage = new JButton("��һҳ");
		nextPage.setBounds(350, 410, 100, 25);
		nextPage.addActionListener(this);
		this.add(nextPage);
		
		pageJump = new JTextField();
		pageJump.setBounds(500, 410, 50, 25);
		this.add(pageJump);
		
		jumpTo = new JButton("��ת");
		jumpTo.setBounds(600, 410, 100, 25);
		jumpTo.addActionListener(this);
		this.add(jumpTo);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "����") {
			if(byNickName.isSelected())
				searchUser("byNickName");
			else if(byRealName.isSelected())
				searchUser("byRealName");
			else if(byEmail.isSelected())
				searchUser("byEmail");
			else 
				searchUser("byNumber");
			getUsers();
			getTable();
		}else if(e.getActionCommand() == "���") {
				frame.dispose();
				UIUtil.run(new RegisterJFrame("���",null), RegisterJFrame.FRAME_WIDTH, RegisterJFrame.FRAME_HEIGHT);
		}else if(e.getActionCommand() == "�����û�") {
			IUserManageService userManageService = new UserManageServiceImpl();
			if(!userManageService.exportUser())
				JOptionPane.showMessageDialog(null, "����ʧ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "�����ɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand() == "�����û�") {
			importChooser = new JFileChooser();
			importChooser.setDialogTitle("ѡ�񱸷��ļ����е���");
			importChooser.setApproveButtonText("ȷ��");
			importChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			this.add(importChooser);
			if(importChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				IUserManageService userManageService = new UserManageServiceImpl();
				if(userManageService.importUser(importChooser.getSelectedFile().getPath())) {
					JOptionPane.showMessageDialog(null, "����ɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
					getInitUsers();
					getUsers();
					currentPage = 1;
					getTable();
				}else {
					JOptionPane.showMessageDialog(null, "����ʧ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				importChooser.setVisible(false);
			}
		}else if(e.getActionCommand() == "ɾ��") {
			IUserManageService userManageService = new UserManageServiceImpl();
			userManageService.deleteUser(e.getSource().toString());
			getInitUsers();
			getUsers();
			currentPage = 1;
			getTable();
			JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand() == "�༭") {
			frame.dispose();
			IUserManageService userManageService = new UserManageServiceImpl();
			User user = userManageService.getUser(e.getSource().toString());
			UIUtil.run(new RegisterJFrame("�༭",user), RegisterJFrame.FRAME_WIDTH, RegisterJFrame.FRAME_HEIGHT);
		}else if(e.getActionCommand() == "��һҳ") {
			if(currentPage<=1) {
				currentPage = 1;
			}else {
				currentPage--;
			}
			getTable();
		}else if(e.getActionCommand() == "��һҳ") {
			if(currentPage>=maxPage) {
				currentPage = maxPage;
			}else {
				currentPage++;
			}
			getTable();
		}else if(e.getActionCommand() == "��ת"){
			if(pageJump.getText().length()==0) {
				JOptionPane.showMessageDialog(null, "��ָ����תҳ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else {
				IUserIndexService userIndexService = new UserIndexServiceImpl();
				if(userIndexService.isValidPageNum(pageJump.getText(), maxPage)) {
					currentPage = Integer.parseInt(pageJump.getText());
					getTable();
				}else {
					JOptionPane.showMessageDialog(null, "ҳ�벻��ȷ", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
		}else if(e.getActionCommand() == "���") {
			IUserManageService userManageService = new UserManageServiceImpl();
			userManageService.deleteAll(UIStatus.current_user);
			getInitUsers();
			getUsers();
			currentPage = 1;
			getTable();
			JOptionPane.showMessageDialog(null, "��ճɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void getTable() {
		Object[][] showObjs;
		if(objects.length-(currentPage-1)*TABLE_ROW_NUM >= TABLE_ROW_NUM)
			showObjs = new Object[TABLE_ROW_NUM][9];
		else {
			try {
				showObjs = new Object[objects.length-(currentPage-1)*TABLE_ROW_NUM][9];
			}catch (Exception e) {
				showObjs = new Object[0][];
			}
		}
			
		
		for(int i=0;i<showObjs.length;i++) {
			showObjs[i] = objects[(currentPage-1)*TABLE_ROW_NUM+i];
		}
		
		userTable.setModel(new DefaultTableModel(showObjs,cols){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 7 || column == 8)
					return true;
				return false;
			}
			
		});
		userTable.setRowHeight(30);
		userTable.setBounds(0, 50, 700, 360);
		this.add(userTable);
		if(objects.length!=0) {
			for(int i=0;i<maxPage;i++) {
				for(int j=0;j<deleteButtons[i].length;j++) {
					deleteButtons[i][j].setBounds(1000, 1000, 60, 30);
					editbuttons[i][j].setBounds(1000, 1000, 60, 30);
				}
			}
		
			for(int i=0;i<deleteButtons[currentPage-1].length;i++) {
				deleteButtons[currentPage-1][i].setBounds(720, 50+i*30, 60, 30);
				editbuttons[currentPage-1][i].setBounds(820, 50+i*30, 60, 30);
			}
		}
		
		currentPageLabel.setText("��ǰλ�ڵ�"+currentPage+"/"+maxPage+"ҳ");
	}
	
	public void getInitUsers() {
		IUserManageService userManageService = new UserManageServiceImpl();
		objects = userManageService.getAllUser(UIStatus.current_user);
	}
	
	public void getUsers() {
		if(deleteButtons!=null) {
			for(int i=0;i<maxPage;i++) {
				for(int j=0;j<deleteButtons[i].length;j++) {
					deleteButtons[i][j].setVisible(false);
					editbuttons[i][j].setVisible(false);
				}
			}
		}
		
		if(objects.length%TABLE_ROW_NUM==0 && objects.length/TABLE_ROW_NUM!=0)
			maxPage = objects.length/TABLE_ROW_NUM;
		else
			maxPage = objects.length/TABLE_ROW_NUM+1;
		
		if(objects.length!=0){
			deleteButtons = new JButton[maxPage][];
			editbuttons = new JButton[maxPage][];
			for(int i=0;i<maxPage;i++) {
				if(objects.length%TABLE_ROW_NUM != 0 && i==maxPage-1) {
					deleteButtons[i] = new JButton[objects.length%TABLE_ROW_NUM];
					editbuttons[i] =  new JButton[objects.length%TABLE_ROW_NUM];
				}else {
					deleteButtons[i] = new JButton[TABLE_ROW_NUM];
					editbuttons[i] =  new JButton[TABLE_ROW_NUM];
				}
				for(int j=0;j<deleteButtons[i].length;j++) {
					deleteButtons[i][j] = (JButton)objects[i*TABLE_ROW_NUM+j][7];
					deleteButtons[i][j].setBounds(1000, 1000, 60, 30);
					deleteButtons[i][j].addActionListener(this);
					this.add(deleteButtons[i][j]);
				
					editbuttons[i][j] = (JButton)objects[i*TABLE_ROW_NUM+j][8];
					editbuttons[i][j].setBounds(1000, 1000, 60, 30);
					editbuttons[i][j].addActionListener(this);
					this.add(editbuttons[i][j]);
				}
			}
		}
	}
	
	public void searchUser(String method) {
		IUserManageService userManageService = new UserManageServiceImpl();
		objects = userManageService.searchUser(UIStatus.current_user,this.searchContent.getText(),method);
	}
}
