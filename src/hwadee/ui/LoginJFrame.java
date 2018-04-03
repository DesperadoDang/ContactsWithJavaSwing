package hwadee.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hwadee.entity.User;
import hwadee.service.ILoginService;
import hwadee.service.impl.LoginServiceImpl;
import hwadee.util.UIUtil;

public class LoginJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 250;
	
	private JLabel userNameLabel;
	private JTextField userName;
	private JLabel passwordLabel;
	private JPasswordField password;
	private JButton login;
	private JButton reset;
	private JButton signUp;
	
	public LoginJFrame() {
		this.setLayout(null);
		this.setBounds(100, 100, FRAME_WIDTH, FRAME_WIDTH);
		this.setTitle("��¼�绰��");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		userNameLabel = new JLabel("�û���");
		userNameLabel.setBounds(50, 50, 50, 20);
		this.add(userNameLabel);
		
		userName = new JTextField();
		userName.setBounds(130, 50, 220, 25);
		this.add(userName);
		userName.setName("userName");
		
		passwordLabel = new JLabel("����");
		passwordLabel.setBounds(50, 100, 50, 25);
		this.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(130, 100, 220, 25);
		this.add(password);
		password.setName("password");
		
		login = new JButton("��¼");
		login.setBounds(50, 150, 70, 25);
		login.addActionListener(this);
		this.add(login);
		login.setName("login");
		
		reset = new JButton("����");
		reset.setBounds(165, 150, 70, 25);
		reset.addActionListener(this);
		this.add(reset);
		reset.setName("reset");
		
		signUp = new JButton("ע��");
		signUp.setBounds(280, 150, 70, 25);
		signUp.addActionListener(this);
		this.add(signUp);
		signUp.setName("signUp");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "��¼") {
			if(userName.getText().length() != 0 && password.getPassword().length != 0) {
				ILoginService userService = new LoginServiceImpl();
				User user = null;
				if((user = userService.verifyUser(userName.getText(),password.getPassword())) != null) {
					UIStatus.current_user = user;
					if(user.isAdmin()) {
						//ת������Ա����
						this.dispose();
						JOptionPane.showMessageDialog(null, "����Ա��¼�ɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
						UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
					}else {
						//ת����ͨ�û�����
						this.dispose();
						JOptionPane.showMessageDialog(null, "��ͨ�û���¼�ɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
						UIUtil.run(new UserIndexJFrame(), UserIndexJFrame.FRAME_WIDTH, UserIndexJFrame.FRAME_HEIGHT);
					}
				}else {
					JOptionPane.showMessageDialog(null, "�û������������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}else if(userName.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�������û���", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(password.getPassword().length == 0)
				JOptionPane.showMessageDialog(null, "����������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			
		}else if(e.getActionCommand() == "����") {
			userName.setText("");
			password.setText("");
		}else {
			//ת��ע�����
			this.dispose();
			UIUtil.run(new RegisterJFrame("ע��",null), RegisterJFrame.FRAME_WIDTH, RegisterJFrame.FRAME_HEIGHT);
		}
	}
	
}
