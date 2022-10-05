package com.barber.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.barber.model.Appoint;
import com.barber.model.Employee;
import com.barber.util.StringUtil;

public class AppointDao {
	public int addApnt(Connection con, Appoint appoint) throws Exception {
		String sql = "insert into t_appointment values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, appoint.getCustName());
		pstmt.setString(2, appoint.getTelNum());
		pstmt.setString(3, appoint.getDate());
		pstmt.setString(4, appoint.getDay());
		pstmt.setString(5, appoint.getTime());
		pstmt.setLong(6, appoint.getEmpId());

		return pstmt.executeUpdate(); // return affected rows
	}

	public ResultSet apList(Connection con, Appoint appoint) throws SQLException {
		StringBuffer sb = new StringBuffer("select * from t_appointment");

		if (appoint.getId() > 0) {
			sb.append("and id like '%" + appoint.getId() + "%'");
		}
		if (!StringUtil.isEmpty(appoint.getCustName())) {
			sb.append("and empName like '%" + appoint.getCustName() + "%'");
		}
		if (!StringUtil.isEmpty(appoint.getTelNum())) {
			sb.append("and empName like '%" + appoint.getTelNum() + "%'");
		}
		if (!StringUtil.isEmpty(appoint.getDate())) {
			sb.append("and empSex like '%" + appoint.getDate() + "%'");
		}

		if (!StringUtil.isEmpty(appoint.getDay())) {
			sb.append("and workDay like '%" + appoint.getDay() + "%'");
		}
		if (!StringUtil.isEmpty(appoint.getTime())) {
			sb.append("and workDay like '%" + appoint.getTime() + "%'");
		}
		if (appoint.getEmpId() > 0) {
			sb.append("and id like '%" + appoint.getEmpId() + "%'");
		}

		String sql = sb.toString().replaceFirst("and", "where");
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
