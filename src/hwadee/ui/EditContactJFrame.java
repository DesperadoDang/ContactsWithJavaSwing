package hwadee.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hwadee.entity.Contact;
import hwadee.service.IEditContactService;
import hwadee.service.impl.EditContactServiceImpl;
import hwadee.util.UIUtil;

public class EditContactJFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 500;
	
	private String command;
	private Contact editedContact = null;
	
	private JLabel hintLabel;
	private JLabel nameHint,numberHint,qqHint,addressHint;
	private JLabel nameLabel,genderLabel,birthdayLabel,numberLabel,qqLabel,addressLabel;
	private JTextField name,number,qq;
	private JRadioButton male,female;
	private ButtonGroup genderGroup;
	private DateObserver birthday;
	private JTextArea address;
	private JButton add,reset;
	
	
	public EditContactJFrame(String command,Contact editedContact) {
		this.command = command;
		this.editedContact = editedContact;
		
		this.setLayout(null);
		this.setBounds(500, 300, FRAME_WIDTH, FRAME_WIDTH);
		this.setTitle(command+"��ϵ��");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		hintLabel = new JLabel("��\" * \"����Ϊ������");
		hintLabel.setBounds(150, 10, 200, 25);
		hintLabel.setForeground(Color.RED);
		this.add(hintLabel);
		
		nameHint = new JLabel("������������10���֣�Ӣ����������50����ĸ");
		nameHint.setBounds(100, 30, 300, 25);
		nameHint.setForeground(Color.BLUE);
		this.add(nameHint);
		
		nameLabel = new JLabel("*����");
		nameLabel.setBounds(50,50,80,25);
		this.add(nameLabel);
		
		name = new JTextField();
		name.setBounds(130,50,220,25);
		this.add(name);
		
		genderLabel = new JLabel("*�Ա�");
		genderLabel.setBounds(50, 100, 80, 25);
		this.add(genderLabel);
		
		male = new JRadioButton("��",true);
		male.setBounds(130, 100, 110, 25);
		this.add(male);
		
		female = new JRadioButton("Ů");
		female.setBounds(240, 100, 110, 25);
		this.add(female);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(this.male);
		genderGroup.add(this.female);
		
		birthdayLabel = new JLabel("����");
		birthdayLabel.setBounds(50, 150, 80, 25);
		this.add(birthdayLabel);
		
		birthday = new DateObserver();
		birthday.setText("��ѡ���������");
		birthday.setBounds(130, 150, 220, 25);
		this.add(birthday);
		
		numberHint = new JLabel("11�����ֵ绰���ֻ�����");
		numberHint.setBounds(100, 180, 300, 25);
		numberHint.setForeground(Color.BLUE);
		this.add(numberHint);
		
		numberLabel = new JLabel("*�绰");
		numberLabel.setBounds(50, 200, 80, 25);
		this.add(numberLabel);
		
		number = new JTextField();
		number.setBounds(130, 200, 220, 25);
		this.add(number);
		
		qqHint = new JLabel("5-12λ������");
		qqHint.setBounds(100, 230, 300, 25);
		qqHint.setForeground(Color.BLUE);
		this.add(qqHint);
		
		qqLabel = new JLabel("QQ");
		qqLabel.setBounds(50, 250, 80, 25);
		this.add(qqLabel);
		
		qq = new JTextField();
		qq.setBounds(130, 250, 220, 25);
		this.add(qq);
		
		addressHint = new JLabel("��ַ��250������");
		addressHint.setBounds(100, 280, 300, 25);
		addressHint.setForeground(Color.BLUE);
		this.add(addressHint);
		
		addressLabel = new JLabel("��ַ");
		addressLabel.setBounds(50, 300, 80, 25);
		this.add(addressLabel);
		
		address = new JTextArea();
		address.setBounds(130, 300, 220, 50);
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		this.add(address);
		
		add = new JButton(this.command);
		add.setBounds(50, 400, 120, 25);
		add.addActionListener(this);
		this.add(add);
		
		reset = new JButton("����");
		reset.setBounds(230, 400, 120, 25);
		reset.addActionListener(this);
		this.add(reset);
		
		if(this.command.equals("�༭")) {
			name.setText(this.editedContact.getContactName());
			if(this.editedContact.getContactGender().equals("Ů"))
				female.setSelected(true);
			if(this.editedContact.getContactBirthday()!=null)
				birthday.setText(this.editedContact.getContactBirthday().toString());
			number.setText(this.editedContact.getContactNumber());
			qq.setText(this.editedContact.getContactQQ());
			address.setText(this.editedContact.getContactAddress());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "���" || e.getActionCommand() == "�༭") {
			if(name.getText().length() != 0 && number.getText().length() != 0) {
				IEditContactService editContactService = new EditContactServiceImpl();
				if(editContactService.isValidContactName(name.getText()) 
						&& editContactService.isValidBirthday(birthday.getText()) 
						&& editContactService.isValidNumber(number.getText()) 
						&& editContactService.isValidQQ(qq.getText()) 
						&& editContactService.isValidAddress(address.getText())) {
					if(this.command.equals("�༭")) {
						if(editContactService.updateContact(this.editedContact.getContactId(),this.editedContact.getUserId(),name.getText(), male.isSelected()?"��":"Ů", birthday.getText(), number.getText(), qq.getText(), address.getText(),this.editedContact.getContactAddDate())) {
							JOptionPane.showMessageDialog(null, "�༭�ɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							if(UIStatus.current_user.isAdmin()) {
								UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
							}else {
								UIUtil.run(new UserIndexJFrame(), UserIndexJFrame.FRAME_WIDTH, UserIndexJFrame.FRAME_HEIGHT);
							}
						}else {
							JOptionPane.showMessageDialog(null, "�༭ʧ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
						}
					}else {
						if(editContactService.insertContact(UIStatus.current_user.getUserId(),name.getText(), male.isSelected()?"��":"Ů", birthday.getText(), number.getText(), qq.getText(), address.getText())) {
							JOptionPane.showMessageDialog(null, "��ӳɹ�", "��Ϣ��ʾ", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							if(UIStatus.current_user.isAdmin()) {
								UIUtil.run(new AdminIndexJFrame(), AdminIndexJFrame.FRAME_WIDTH, AdminIndexJFrame.FRAME_HEIGHT);
							}else {
								UIUtil.run(new UserIndexJFrame(), UserIndexJFrame.FRAME_WIDTH, UserIndexJFrame.FRAME_HEIGHT);
							}
						}else {
							JOptionPane.showMessageDialog(null, "���ʧ��", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
						}
					}
				}else if(!editContactService.isValidContactName(name.getText())){
					JOptionPane.showMessageDialog(null, "��ϵ��������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!editContactService.isValidBirthday(birthday.getText())) {
					JOptionPane.showMessageDialog(null, "������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!editContactService.isValidNumber(number.getText())) {
					JOptionPane.showMessageDialog(null, "�绰������Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else if(!editContactService.isValidQQ(qq.getText())) {
					JOptionPane.showMessageDialog(null, "qq��Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "��ַ��Ч", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}else if(name.getText().length()==0){
				JOptionPane.showMessageDialog(null, "��������ϵ������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "���������", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			}
		}else {
			if(this.command.equals("�༭")) {
				name.setText(this.editedContact.getContactName());
				if(this.editedContact.getContactGender().equals("Ů"))
					female.setSelected(true);
				if(this.editedContact.getContactBirthday()!=null)
					birthday.setText(this.editedContact.getContactBirthday().toString());
				else
					birthday.setText("��ѡ���������");
				number.setText(this.editedContact.getContactNumber());
				qq.setText(this.editedContact.getContactQQ());
				address.setText(this.editedContact.getContactAddress());
			}else {
				name.setText("");
				male.setSelected(true);
				birthday.setText("��ѡ���������");
				number.setText("");
				qq.setText("");
				address.setText("");
			}
		}
	}

}
