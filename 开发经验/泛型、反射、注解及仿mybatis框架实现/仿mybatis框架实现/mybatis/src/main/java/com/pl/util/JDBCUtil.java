package com.pl.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class JDBCUtil {

	private static String driverClassName = "";
	private static String url = "";
	private static String username = "";
	private static String password = "";

	/**
	 * 获取数据库连接信息
	 */
	static {
		InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("properties/jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			String driverClassNameTemp = prop.getProperty("jdbc.driverClassName");
			String urlTemp = prop.getProperty("jdbc.url");
			String usernameTemp = prop.getProperty("jdbc.username");
			String passwordTemp = prop.getProperty("jdbc.password");
			if (StringUtils.isNotBlank(driverClassNameTemp)) {
				driverClassName = driverClassNameTemp;
			}
			if (StringUtils.isNotBlank(urlTemp)) {
				url = urlTemp;
			}
			if (StringUtils.isNotBlank(usernameTemp)) {
				username = usernameTemp;
			}
			if (StringUtils.isNotBlank(passwordTemp)) {
				password = passwordTemp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			// 注册数据库驱动
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static PreparedStatement getPstmt(String sql) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		if (null == conn) {
			return pstmt;
		}
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	/**
	 * 执行sql语句并返回指定字段值，一般用于返回主键字段
	 * @param sql sql语句
	 * @param id 要返回的字段名的数组
	 * @return
	 */
	public static PreparedStatement getPstmt(String sql,String[] columnArray) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		if (null == conn) {
			return pstmt;
		}
		try {
			pstmt = conn.prepareStatement(sql,columnArray);// 指定要返回的字段
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	/**
	 * 关闭增删改操作后的资源
	 * 
	 * @param pstmt
	 */
	public static void closeResUpdate(PreparedStatement pstmt) {
		try {
			if (null != pstmt) {
				Connection conn = pstmt.getConnection();
				pstmt.close();
				if (null != conn) {
					conn.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭查询操作后的资源
	 * 
	 * @param rs
	 */
	public static void closeResQuery(ResultSet rs) {
		try {
			if (null != rs) {
				Statement pstmt = rs.getStatement();
				// 关闭ResultSet一定要在获得Statement之后
				rs.close();
				if (pstmt != null) {
					Connection conn = pstmt.getConnection();
					pstmt.close();
					if (conn != null) {
						conn.close();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
