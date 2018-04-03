package hwadee.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class DBUtil {
	static private String dbDriverName = null;
	static private String dbUrl = null;
	static private String userName = null;
	static private String password = null;
	static {
		Properties configs = new Properties();
		InputStream configIn = DBUtil.class.getResourceAsStream("../../db.properties");
		if(configIn == null) {
			System.err.println("�����ļ�ȱʧ");
			System.exit(-1);
		}
		//��ȡ�����ļ�
		try {
			configs.load(configIn);
			
			dbDriverName = configs.getProperty("db.driver");
			dbUrl = configs.getProperty("db.url");
			userName = configs.getProperty("db.user");
			password = configs.getProperty("db.password");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(configIn != null) {
				try {
					configIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//�������ݿ�����
		try {
			Class.forName(dbDriverName);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "�ⲿ��û�ҵ�", "��Ϣ��ʾ", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
	//��ȡ����
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbUrl, userName, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn = null;
		}
		
		return conn;
	}
	//ִ�и���
	public static boolean executeUpdate(String sql,Object...objs) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			for (int i=0;i<objs.length;i++) {
				stmt.setObject(i+1, objs[i]);
			}
			if(stmt.executeUpdate()==0)
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBUtil.closeConnection(conn);
			DBUtil.closeStatement(stmt);
		}
		
		return true;
	}
	//ִ�в�ѯ
	public static Object[] executeQuery(String sql,Object...objs) {
		Object[] resources = new Object[3];
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			for (int i=0;i<objs.length;i++) {
				stmt.setObject(i+1, objs[i]);
			}
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resources[0] = rs;
		resources[1] = stmt;
		resources[2] = conn;
		return resources;
	}
	//ִ�в�ѯ����
	public static Object[] executeQueryAll(String sql) {
		Object[] resources = new Object[3];
		Connection conn = DBUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resources[0] = rs;
		resources[1] = stmt;
		resources[2] = conn;
		return resources;
	}
	//�رս����
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//�ر�PreparedStatement
	public static void closeStatement(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//�ر�����
	public static void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//�رս������PreparedStatement������
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn) {
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(conn);
	}
}
