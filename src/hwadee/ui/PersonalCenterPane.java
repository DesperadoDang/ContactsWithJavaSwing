package hwadee.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hwadee.service.ISignUpService;
import hwadee.service.impl.SignUpServiceImpl;

public class PersonalCenterPane extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel nickNameLabel, realNameLabel, genderLabel, passwordLabel, repasswordLabel, oldPasswordLabel;
	private JLabel emailLabel, birthdayLabel, numberLabel, addressLabel, hintLabel;
	private JTextField nickName,realName,email,number;
	private JRadioButton male,female;
	private ButtonGroup genderGroup;
	private JPasswordField password,repassword,oldPassword;
	private DateObserver birthday;
	private JTextArea address;
	private JButton edit,confirm,reset;
	
	private JLabel nickNameHint,realNameHint,passwordHint,emailHint,numberHint,addressHint;
	
	public PersonalCenterPane() {
		this.setLayout(null);
		
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
		this.add(nickName);
		
		realNameHint = new JLabel("������������10���֣�Ӣ����������50����ĸ");
		realNameHint.setBounds(550, 30, 300, 25);
		realNameHint.setForeground(Color.BLUE);
		this.add(realNameHint);
		
		realNameLabel = new JLabel("��ʵ����");
		realNameLabel.setBounds(500,50,80,25);
		this.add(realNameLabel);
		
		realName = new JTextField();
		realName.setBounds(580,50,220,25);
		this.add(realName);
		
		genderLabel = new JLabel("*�Ա�");
		genderLabel.setBounds(50, 100, 80, 25);
		this.add(genderLabel);
		
		male = new JRadioButton("��");
		male.setBounds(130, 100, 110, 25);
		this.add(male);
		
		female = new JRadioButton("Ů");
		female.setBounds(240, 100, 110, 25);
		this.add(female);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(this.male);
		genderGroup.add(this.female);
		
		oldPasswordLabel = new JLabel("*�����룺");
		oldPasswordLabel.setBounds(500, 100, 80, 25);
		this.add(oldPasswordLabel);
		
		oldPassword = new JPasswordField();
		oldPassword.setBounds(580, 100, 220, 25);
		this.add(oldPassword);
		
		passwordHint = new JLabel("6-18λ�������֡���Сд��ĸ�������ַ��Ĵ�");
		passwordHint.setBounds(100, 130, 300, 25);
		passwordHint.setForeground(Color.BLUE);
		this.add(passwordHint);
		
		passwordLabel = new JLabel("*������");
		passwordLabel.setBounds(50, 150, 80, 25);
		this.add(passwordLabel);
		
		password = new JPasswordField();
		password.setBounds(130, 150, 220, 25);
		this.add(password);
		
		repasswordLabel = new JLabel("*�ظ�����");
		repasswordLabel.setBounds(500, 150, 80, 25);
		this.add(repasswordLabel);
		
		repassword = new JPasswordField();
		repassword.setBounds(580, 150, 220, 25);
		this.add(repassword);
		
		emailHint = new JLabel("abc@ab.cd��ʽ��������50���ַ�");
		emailHint.setBounds(100, 180, 300, 25);
		emailHint.setForeground(Color.BLUE);
		this.add(emailHint);
		
		emailLabel = new JLabel("*����");
		emailLabel.setBounds(50, 200, 80, 25);
		this.add(emailLabel);
		
		email = new JTextField();
		email.setBounds(130, 200, 220, 25);
		this.add(email);
		
		birthdayLabel = new JLabel("����");
		birthdayLabel.setBounds(500, 200, 80, 25);
		this.add(birthdayLabel);
		
		birthday = new DateObserver();
		birthday.setText("��ѡ���������");
		birthday.setBounds(580, 200, 220, 25);
		this.add(birthday);
		
		numberHint = new JLabel("11�����ֵ绰���ֻ�����");
		numberHint.setBounds(100, 230, 300, 25);
		numberHint.setForeground(Color.BLUE);
		this.add(numberHint);
		
		numberLabel = new JLabel("*�绰");
		numberLabel.setBounds(50, 250, 80, 25);
		this.add(numberLabel);
		
		number = new JTextField();
		number.setBounds(130, 250, 220, 25);
		this.add(number);
		
		addressHint = new JLabel("��ַ��250������");
		addressHint.setBounds(100, 280, 300, 25);
		addressHint.setForeground(Color.BLUE);
		this.add(addressHint);
		
		addressLabel = new JLabel("��ַ");
		addressLabel.setBounds(50, 300, 80, 25);
		this.add(addressLabel);
		
		address = new JTextArea();
		address.setBounds(130, 300, 500, 50);
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		this.add(address);
		
		edit = new JButton("�༭");
		edit.setBounds(50, 370, 120, 25);
		edit.addActionListener(this);
		this.add(edit);
		
		confirm = new JButton("ȷ��");
		confirm.setBounds(300, 370, 120, 25);
		confirm.addActionListener(this);
		this.add(confirm);
		
		reset = new JButton("����");
		reset.setBounds(530, 370, 120, 25);
		reset.addActionListener(this);
		this.add(reset);
		
		setAllNotEnable();
		getDefaultValue();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="�༭") {
			setAllEnable();
		}else if(e.getActionCommand()=="ȷ��") {
			if(nickName.getText().length() != 0 && oldPassword.getPassword().length != 0 && password.getPassword().length != 0 && repassword.getPassword().length != 0 && email.getText().length() != 0 && number.getText().length() != 0) {
				ISignUpService signUpService = new SignUpServiceImpl();
				if(signUpService.isValidNickName(nickName.getText()) 
						&& !signUpService.isRepeatedNickName(nickName.getText(),UIStatus.current_user.getUserId()) 
						&& signUpService.isValidRealName(realName.getText()) 
						&& signUpService.isCorrectOldPassword(oldPassword.getPassword(), UIStatus.current_user)
						&& signUpService.isCorrectPassword(password.getPassword()) 
						&& signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())
						&& signUpService.isValidEmail(email.getText()) 
						&& !signUpService.isRepeatedEmail(email.getText(),UIStatus.current_user.getUserId()) 
						&& signUpService.isValidBirthday(birthday.getText()) 
						&& signUpService.isValidNumber(number.getText()) 
						&& !signUpService.isRepeatedNumber(number.getText(),UIStatus.current_user.getUserId())
						&& signUpService.isValidAddress(address.getText())) {
					if(!signUpService.editUser(UIStatus.current_user,
							nickName.getText(), 
							realName.getText(), 
							male.isSelected()?"��":"Ů", 
							password.getPassword(), 
							email.getText(), 
							birthday.getText(), 
							number.getText(), 
							address.getText(),
							UIStatus.current_user.getRegisterDate())) {
						JOptionPane.showMessageDialog(null, "����ʧ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
						setAllNotEnable();
						getDefaultValue();
					}
					else {
						JOptionPane.showMessageDialog(null, "���ĳɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
						UIStatus.current_user = signUpService.getUser(UIStatus.current_user.getUserId());
						setAllNotEnable();
						getDefaultValue();
					}
				}else if(!signUpService.isValidNickName(nickName.getText())){
					JOptionPane.showMessageDialog(null, "�û�����Ч��ע���û����ַ��볤������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedNickName(nickName.getText(),UIStatus.current_user.getUserId())) {
					JOptionPane.showMessageDialog(null, "�û����Ѵ���", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidRealName(realName.getText())) {
					JOptionPane.showMessageDialog(null, "������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isCorrectOldPassword(oldPassword.getPassword(), UIStatus.current_user)) {
					JOptionPane.showMessageDialog(null, "���������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isCorrectPassword(password.getPassword())) {
					JOptionPane.showMessageDialog(null, "������Ч��ע��������ַ������볤������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isSameRepassword(password.getPassword(), repassword.getPassword())) {
					JOptionPane.showMessageDialog(null, "������������벻һ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidEmail(email.getText())) {
					JOptionPane.showMessageDialog(null, "��Ч�������ַ", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedEmail(email.getText(),UIStatus.current_user.getUserId())) {
					JOptionPane.showMessageDialog(null, "�����ַ�ѱ�ע��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidBirthday(birthday.getText())) {
					JOptionPane.showMessageDialog(null, "������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidNumber(number.getText())) {
					JOptionPane.showMessageDialog(null, "������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(signUpService.isRepeatedNumber(number.getText(),UIStatus.current_user.getUserId())) {
					JOptionPane.showMessageDialog(null, "�����ѱ�ע��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!signUpService.isValidAddress(address.getText())){
					JOptionPane.showMessageDialog(null, "��Ч�ĵ�ַ", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}else if(nickName.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�������û���", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(oldPassword.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "�����������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(password.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "������������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(repassword.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "��ȷ��������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else if(email.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "����������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "���������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			getDefaultValue();
		}
	}
	
	public void setAllEnable() {
		nickName.setEditable(true);
		realName.setEditable(true);
		male.setEnabled(true);
		female.setEnabled(true);
		oldPassword.setEditable(true);
		password.setEditable(true);
		repassword.setEditable(true);
		email.setEditable(true);
		birthday.setEnabled(true);
		number.setEditable(true);
		address.setEditable(true);
		edit.setEnabled(false);
		confirm.setEnabled(true);
		reset.setEnabled(true);
	}

	public void setAllNotEnable() {
		nickName.setEditable(false);
		realName.setEditable(false);
		male.setEnabled(false);
		female.setEnabled(false);
		oldPassword.setEditable(false);
		password.setEditable(false);
		repassword.setEditable(false);
		email.setEditable(false);
		birthday.setEnabled(false);
		number.setEditable(false);
		address.setEditable(false);
		edit.setEnabled(true);
		confirm.setEnabled(false);
		reset.setEnabled(false);
	}
	
	public void getDefaultValue() {
		nickName.setText(UIStatus.current_user.getUserNickName());
		realName.setText(UIStatus.current_user.getUserRealName());
		if(UIStatus.current_user.getUserGender().equals("��"))
			male.setSelected(true);
		else
			female.setSelected(true);
		email.setText(UIStatus.current_user.getUserEmail());
		if(UIStatus.current_user.getUserBirthday() != null)
			birthday.setText(UIStatus.current_user.getUserBirthday().toString());
		number.setText(UIStatus.current_user.getUserNumber());
		address.setText(UIStatus.current_user.getUserAddress());
		oldPassword.setText("");
		password.setText("");
		repassword.setText("");
	}
}
