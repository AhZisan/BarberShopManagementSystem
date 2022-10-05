package com.barber.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.barber.model.Employee;
import com.barber.util.StringUtil;

public class EmployeeDao {

	public ResultSet list(Connection con, Employee employee) throws SQLException {
		StringBuffer sb = new StringBuffer("select * from t_employee");
		
		if(employee.getId() > 0) {
			sb.append("and id like '%" + employee.getId() + "%'");
			
		}		
		if (!StringUtil.isEmpty(employee.getEmpName())) {
			sb.append("and empName like '%" + employee.getEmpName() + "%'");
		}
		if(employee.getAge() >= 10) {
			sb.append("and age like '%" + employee.getAge() + "%'");
		}
		if (!StringUtil.isEmpty(employee.getEmpSex())) {
			sb.append("and empSex like '%" + employee.getEmpSex() + "%'");
		}
		if (employee.getPrice() > 0) {
			sb.append("and price like '%" + employee.getPrice() + "%'");
		}
		if (!StringUtil.isEmpty(employee.getWorkDay())) {
			sb.append("and workDay like '%" + employee.getWorkDay() + "%'");
		}
		
		String sql = sb.toString().replaceFirst("and", "where");
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeQuery();
	}

}
