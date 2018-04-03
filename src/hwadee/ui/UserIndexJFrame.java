package hwadee.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UserIndexJFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 900;
	public static final int FRAME_HEIGHT = 500;
	
	private JTabbedPane userTabbedPane;
	private JPanel allContactPane,searchPane,personalInfoPane,birthdayHintPane;
	
	public UserIndexJFrame() {
		this.setTitle("�绰��");
		this.setLayout(null);
		this.setBounds(500,300,UserIndexJFrame.FRAME_WIDTH,UserIndexJFrame.FRAME_HEIGHT);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		userTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		userTabbedPane.setBounds(0, 0, UserIndexJFrame.FRAME_WIDTH,UserIndexJFrame.FRAME_HEIGHT);
		this.add(userTabbedPane);
		
		allContactPane = new AllContactPane(this);
		userTabbedPane.addTab("������ϵ��", allContactPane);
		
		searchPane = new SearchPane(this);
		userTabbedPane.addTab("������ϵ��", searchPane);
		
		personalInfoPane = new PersonalCenterPane();
		userTabbedPane.addTab("��������", personalInfoPane);
		
		birthdayHintPane = new BirthDayHintPane();
		userTabbedPane.addTab("��ϵ����������", birthdayHintPane);
	}	
}
