package com.barber.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.barber.model.User;





public class UserDao {
	public User login(Connection con, User user) throws Exception{
		User resultUser = null;
		String sql = "select * from t_user where userName=? and password=? and privilege=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getPrivilege());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			resultUser = new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setPassword(rs.getString("privilege"));
		}
		return resultUser;
	}
	
	
	public int add(Connection con, User user)throws Exception{
		String sql="insert into t_user values(null,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getPrivilege());
		pstmt.setString(4, user.getMail());
		
		return pstmt.executeUpdate();						  // return affected rows
	}
	
	
	
}
