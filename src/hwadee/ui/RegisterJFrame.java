package hwadee.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hwadee.entity.User;
import hwadee.service.ISignUpService;
import hwadee.service.impl.SignUpServiceImpl;
import hwadee.util.UIUtil;

public class RegisterJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 650;
	
	private JLabel nickNameLabel, realNameLabel, genderLabel, passwordLabel, repasswordLabel;
	private JLabel emailLabel, birthdayLabel, numberLabel, addressLabel, hintLabel;
	private JTextField nickName,realName,email,number;
	private JRadioButton male,female;
	private ButtonGroup genderGroup;
	private JPasswordField password,repassword;
	private DateObserver birthday;
	private JTextArea address;
	private JButton signUp,reset;
	
	private JLabel nickNameHint,realNameHint,passwordHint,emailHint,numberHint,addressHint;
	
	private String command;
	@SuppressWarnings("unused")
	private User editedUser;
	
	public RegisterJFrame(String command,User editedUser) {
		this.command = command;
		this.editedUser = editedUser;
		
		this.setLayout(null);
		this.setBounds(100, 100, FRAME_WIDTH, FRAME_WIDTH);
		this.setTitle(this.command+"�û�");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		hintLabel = new JLabel("��\" * \"����Ϊ������");
		hintLabel.setBounds(150, 10, 200, 25);
		hintLabel.setForeground(Color.RED);
		this.add(hintLabel);
		
		nickNameHint = new JLabel("�������֡���Сд��ĸ���»��ߣ�������50λ");
		nickNameHint.setBounds(100, 30, 300, 25);
		nickNameHint.setForeground(Color.BLUE);
		this.add(nickNameHint);

		nickNameLabel = new JLabel("*�û���");
		nickNameLabel.setBounds(50, 50, 80, 25);
		this.add(nickNameLabel);
		
		nickName = new JTextField();
		nickName.setBounds(130, 50, 220, 25);
		nickName.setName("nickName");
		this.add(nickName);
		
		realNameHint = new JLabel("������������10���֣�Ӣ����������50����ĸ");
		realNameHint.setBounds(100, 80, 300, 25);
		realNameHint.setForeground(Color.BLUE);
		this.add(realNameHint);
		
		realNameLabel = new JLabel("��ʵ����");
		realNameLabel.setBounds(50,100,80,25);
		this.add(realNameLabel);
		
		realName = new JTextField();
		realName.setBounds(130,100,220,25);
		realName.setName("realName");
		this.add(realName);
		
		genderLabel = new JLabel("*�Ա�");
		genderLabel.setBounds(50, 150, 80, 25);
		this.add(genderLabel);
		
		male = new JRadioButton("��",true);
		male.setBounds(130, 150, 110, 25);
		this.add(male);
		
		female = new JRadioButton("Ů");
		female.setBounds(240, 150, 110, 25);
		this.add(female);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(this.male);
		genderGroup.add(this.female);
		
		passwordHint = new JLabel("6-18λ�������֡���Сд��ĸ�������ַ��Ĵ�");
		passwordHint.setBounds(100, 180, 300, 25);
		passwordHint.setForeground(Color.BLUE);
		this.add(passwordHint);
		
		passwordLabel = new JLabel("*����");
		passwordLabel.setBounds(50, 200, 80, 25);
		this.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(130, 200, 220, 25);
		password.setName("password");
		this.add(password);
		
		repasswordLabel = new JLabel("*�ظ�����");
		repasswordLabel.setBounds(50, 250, 80, 25);
		this.add(repasswordLabel);
		
		repassword = new JPasswordField();
		repassword.setBounds(130, 250, 220, 25);
		repassword.setName("repassword");
		this.add(repassword);
		
		emailHint = new JLabel("abc@ab.cd��ʽ��������50���ַ�");
		emailHint.setBounds(100, 280, 300, 25);
		emailHint.setForeground(Color.BLUE);
		this.add(emailHint);
		
		emailLabel = new JLabel("*����");
		emailLabel.setBounds(50, 300, 80, 25);
		this.add(emailLabel);
		
		email = new JTextField();
		email.setBounds(130, 300, 220, 25);
		email.setName("email");
		this.add(email);
		
		birthdayLabel = new JLabel("����");
		birthdayLabel.setBounds(50, 350, 80, 25);
		this.add(birthdayLabel);
		
		birthday = new DateObserver();
		birthday.setText("��ѡ���������");
		birthday.setBounds(130, 350, 220, 25);
		birthday.setName("birthday");
		this.add(birthday);
		
		numberHint = new JLabel("11�����ֵ绰���ֻ�����");
		numberHint.setBounds(100, 380, 300, 25);
		numberHint.setForeground(Color.BLUE);
		this.add(numberHint);
		
		numberLabel = new JLabel("*�绰");
		numberLabel.setBounds(50, 400, 80, 25);
		this.add(numberLabel);
		
		number = new JTextField();
		number.setBounds(130, 400, 220, 25);
		number.setName("number");
		this.add(number);
		
		addressHint = new JLabel("��ַ��250������");
		addressHint.setBounds(100, 430, 300, 25);
		addressHint.setForeground(Color.BLUE);
		this.add(addressHint);
		
		addressLabel = new JLabel("��ַ");
		addressLabel.setBounds(50, 450, 80, 25);
		this.add(addressLabel);
		
		address = new JTextArea();
		address.setBounds(130, 450, 220, 50);
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		address.setName("address");
		this.add(address);
		
		signUp = new JButton(this.command);
		signUp.setBounds(50, 550, 120, 25);
		signUp.setName("signUp");
		signUp.addActionListener(this);
		this.add(signUp);
		
		reset = new JButton("����");
		reset.setBounds(230, 550, 120, 25);
		reset.setName("reset");
		reset.addActionListener(this);
		this.add(reset);
		
		if(this.command.equals("�༭")) {
			nickName.setText(editedUser.getUserNickName());
			realName.setText(editedUser.getUserRealName());
			if(editedUser.getUserGender().equals("��"))
				male.setSelected(true);
			else
				female.setSelected(true);
			email.setText(editedUser.getUserEmail());
			if(editedUser.getUserBirthday()!=null && !editedUser.getUserBirthday().equals(""))
				birthday.setText(editedUser.getUserBirthday().toString());
			number.setText(editedUser.getUserNumber());
			address.setText(editedUser.getUserAddress());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ע��" || e.getActionCommand() == "���" || e.getActionCommand() == "�༭") {
			if(nickName.getText().length() != 0 && password.getPassword().length != 0 && repassword.getPassword().length != 0 && email.getText().length() != 0 && number.getText().length() != 0) {
				ISignUpService signUpService = new SignUpServiceImpl();
				if(signUpService.isValidNickName(nickName.getText()) 
						&& !signUpService.isRepeatedNickName(nickName.getText()) 
						&& signUpService.isValidRealName(realName.getText()) 
						&& signUpService.isCorrectPassword(password.getPassword()) 
						&& signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())
						&& signUpService.isValidEmail(email.getText()) 
						&& !signUpService.isRepeatedEmail(email.getText()) 
						&& signUpService.isValidBirthday(birthday.getText()) 
						&& signUpService.isValidNumber(number.getText()) 
						&& !signUpService.isRepeatedNumber(number.getText())
						&& signUpService.isValidAddress(address.getText())
						&& (this.command.equals("���") || this.command.equals("ע��"))
						|| this.command.equals("�༭")
						&& signUpService.isValidNickName(nickName.getText()) 
						&& !signUpService.isRepeatedNickName(nickName.getText(),editedUser.getUserId()) 
						&& signUpService.isValidRealName(realName.getText()) 
						&& signUpService.isCorrectPassword(password.getPassword()) 
						&& signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())
						&& signUpService.isValidEmail(email.getText()) 
						&& !signUpService.isRepeatedEmail(email.getText(),editedUser.getUserId()) 
						&& signUpService.isValidBirthday(birthday.getText()) 
						&& signUpService.isValidNumber(number.getText()) 
						&& !signUpService.isRepeatedNumber(number.getText(),editedUser.getUserId())
						&& signUpService.isValidAddress(address.getText())) {
					if(this.command.equals("���") || this.command.equals("ע��")) {
						if(!signUpService.insertUser(nickName.getText(), 
								realName.getText(), 
								male.isSelected()?"��":"Ů", 
								password.getPassword(), 
								email.getText(), 
								birthday.getText(), 
								number.getText(), 
								address.getText()))
							JOptionPane.showMessageDialog(null, this.command+"ʧ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
						else {
							this.dispose();
							JOptionPane.showMessageDialog(null, this.command+"�ɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
						}
						if(this.command.equals("ע��"))
							UIUtil.run(new LoginJFrame(), LoginJFrame.FRAME_WIDTH, LoginJFrame.FRAME_HEIGHT);
						else
							UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
					}else {
						if(!signUpService.editUser(this.editedUser,
								nickName.getText(), 
								realName.getText(), 
								male.isSelected()?"��":"Ů", 
								password.getPassword(), 
								email.getText(), 
								birthday.getText(), 
								number.getText(), 
								address.getText(),
								this.editedUser.getRegisterDate()))
							JOptionPane.showMessageDialog(null, "�༭ʧ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
						else {
							this.dispose();
							JOptionPane.showMessageDialog(null, "�༭�ɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
						}
						UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
					}
				}else if(!signUpService.isValidNickName(nickName.getText())){
					JOptionPane.showMessageDialog(null, "�û�����Ч��ע���û����ַ��볤������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedNickName(nickName.getText())) {
					JOptionPane.showMessageDialog(null, "�û����Ѵ���", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidRealName(realName.getText())) {
					JOptionPane.showMessageDialog(null, "������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isCorrectPassword(password.getPassword())) {
					JOptionPane.showMessageDialog(null, "������Ч��ע��������ַ������볤������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())) {
					JOptionPane.showMessageDialog(null, "������������벻һ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidEmail(email.getText())) {
					JOptionPane.showMessageDialog(null, "��Ч�������ַ", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedEmail(email.getText())) {
					JOptionPane.showMessageDialog(null, "�����ַ�ѱ�ע��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidBirthday(birthday.getText())) {
					JOptionPane.showMessageDialog(null, "������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidNumber(number.getText())) {
					JOptionPane.showMessageDialog(null, "������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedNumber(number.getText())) {
					JOptionPane.showMessageDialog(null, "�����ѱ�ע��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "��Ч�ĵ�ַ", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
				
			}else if(nickName.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�������û���", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(password.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "����������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(repassword.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "��ȷ������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(email.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "����������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(number.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "���������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			if(this.command.equals("�༭")) {
				nickName.setText(editedUser.getUserNickName());
				realName.setText(editedUser.getUserRealName());
				if(editedUser.getUserGender().equals("��"))
					male.setSelected(true);
				else
					female.setSelected(true);
				email.setText(editedUser.getUserEmail());
				if(editedUser.getUserBirthday()!=null && !editedUser.getUserBirthday().equals(""))
					birthday.setText(editedUser.getUserBirthday().toString());
				number.setText(editedUser.getUserNumber());
				address.setText(editedUser.getUserAddress());
			}else {
				nickName.setText("");
				realName.setText("");
				male.setSelected(true);
				password.setText("");
				repassword.setText("");
				email.setText("");
				birthday.setText("��ѡ���������");
				number.setText("");
				address.setText("");
			}
		}
	}

}
