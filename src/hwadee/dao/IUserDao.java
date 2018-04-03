package hwadee.dao;

import java.util.List;

import hwadee.entity.User;

public interface IUserDao {
	//�����û�
	public boolean insert(User user);
	//ɾ���û�
	public boolean delete(int userId);
	//�޸��û�
	public boolean update(int userId,User user);
	//ͨ���û�Id�����û�
	public User findUserById(int userId);
	//ͨ���û��������û�
	public User findUserByUserNickName(String userNickName);
	//ͨ���û���ʵ���������û�
	public List<User> findUserByUserRealName(String userRealName);
	//ͨ���û���������û�
	public User findUserByUserEmail(String userEmail);
	//ͨ���û��ֻ��Ų����û�
	public User findUserByUserNumber(String userNumber);
	//���������û�
	public List<User> findAll();
}
