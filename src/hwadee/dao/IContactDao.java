package hwadee.dao;

import java.util.List;

import hwadee.entity.Contact;

public interface IContactDao {
	//������ϵ��
	public boolean insert(Contact contact);
	//ɾ����ϵ��
	public boolean delete(int contactId);
	//�޸���ϵ��
	public boolean update(int contactId,Contact contact);
	//ͨ����ϵ��id������ϵ��
	public Contact findContactById(int contactId);
	//ͨ���û�id������ϵ��
	public List<Contact> findContactByUserId(int userId);
	//ͨ����ϵ������������ϵ��
	public List<Contact> findContactByContactName(String contactName);
	//ͨ����ϵ�˺��������ϵ��
	public List<Contact> findContactByContactNumber(String contactNumber);
	//ͨ����ϵ��qq������ϵ��
	public List<Contact> findContactByContactQQ(String contactQQ);
	//ͨ����ϵ���������û�Id������ϵ��
	public List<Contact> findContactByContactName(String contactName,int userId);
	//ͨ����ϵ�˺�����û�Id������ϵ��
	public List<Contact> findContactByContactNumber(String contactNumber,int userId);
	//ͨ����ϵ��qq���û�Id������ϵ��
	public List<Contact> findContactByContactQQ(String contactQQ,int userId);
	//����������ϵ��
	public List<Contact> findAll();
}
