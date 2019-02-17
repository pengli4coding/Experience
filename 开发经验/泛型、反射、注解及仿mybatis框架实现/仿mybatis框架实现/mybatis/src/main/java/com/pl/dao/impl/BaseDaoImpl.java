package com.pl.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.pl.annotation.Column;
import com.pl.annotation.Id;
import com.pl.annotation.Table;
import com.pl.dao.BaseDao;
import com.pl.util.JDBCUtil;

public class BaseDaoImpl implements BaseDao {

	@Override
	public void delete(Integer id, Class<?> clazz) {
		// id参数校验
		if (null == id || id < 0) {
			throw new IllegalArgumentException("id非法，不允许为空或者小于0");
		}
		// 拼装sql语句
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ");
		sql.append(this.getTableName(clazz) + " where id = ?");
		String sqlStr = sql.toString();
		System.out.println("sql语句为" + sqlStr);
		// 执行sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = JDBCUtil.getPstmt(sqlStr);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeResUpdate(pstmt);
		}

	}

	@Override
	public <T> void delete(T t) {
		// 实体对象非空校验
		if (null == t) {
			throw new IllegalArgumentException("传入实体对象非法，不允许为空");
		}
		// 拼装sql语句
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ");
		Class<?> clazz = t.getClass();
		sql.append(this.getTableName(clazz) + " where id = ?");
		String sqlStr = sql.toString();
		System.out.println("sql语句为" + sqlStr);
		// 判断传入的实体对象中是否有id成员变量或者有某一字段上加了@Column("id")注解
		Object id = null;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 如果有某一字段上加了@Column("id")注解
			Column column = field.getDeclaredAnnotation(Column.class);
			if (null != column && column.value().equalsIgnoreCase("id")) {
				field.setAccessible(true);
				try {
					id = field.get(t);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			// 如果有id成员变量
			String fieldName = field.getName();
			if (fieldName.equalsIgnoreCase("id")) {
				field.setAccessible(true);
				try {
					id = field.get(t);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		if (null == id) {
			throw new IllegalArgumentException("id非法，实体对象必须是有id成员变量或者有某一字段上加了@Column(\"id\")注解");
		}
		// 执行sql语句
		PreparedStatement pstmt = null;
		try {
			pstmt = JDBCUtil.getPstmt(sqlStr);
			pstmt.setObject(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeResUpdate(pstmt);
		}

	}

	@Override
	public <T> T get(Integer id, Class<?> clazz) {
		Object result = null;
		// id参数校验
		if (null == id || id < 0) {
			throw new IllegalArgumentException("id非法，不允许为空或者小于0");
		}
		// 拼装sql语句
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		String idColumn = null;// 主键字段名
		Field[] declaredFields = clazz.getDeclaredFields();
		for(Field field : declaredFields) {
			Id idAnno = field.getDeclaredAnnotation(Id.class);
			if(null != idAnno) {
				idColumn = idAnno.value();
			}
		}
		sql.append(this.getTableName(clazz) + " where " + idColumn + " = ?");
		String sqlStr = sql.toString();
		System.out.println("sql语句为" + sqlStr);
		// 执行sql语句并给实体对象赋值
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = JDBCUtil.getPstmt(sqlStr);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				try {
					result = clazz.newInstance();
				} catch (Exception e) {
					throw new RuntimeException("实例化对象失败");
				}
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					// 获取setter方法
					String fieldName = field.getName();
					String upFieldName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Column column = field.getAnnotation(Column.class);
					if (null != column) {// 如果有指定字段名
						Object columnValue = rs.getObject(column.value());
						try {
							Method setMethod = clazz.getDeclaredMethod(upFieldName, field.getType());
							setMethod.invoke(result, columnValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {// 如果没有指定字段名
						Object columnValue = rs.getObject(fieldName);
						try {
							Method setMethod = clazz.getDeclaredMethod(upFieldName, field.getType());
							setMethod.invoke(result, columnValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeResQuery(rs);
		}

		return (T) result;
	}

	@Override
	public <T> List<T> findAll(Class<?> clazz) {
		List<T> resultList = new ArrayList<T>();
		// 拼装sql语句
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append(this.getTableName(clazz));
		String sqlStr = sql.toString();
		System.out.println("sql语句为" + sqlStr);
		// 执行sql语句并给实体对象赋值
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = JDBCUtil.getPstmt(sqlStr);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Object result = null;
				try {
					result = clazz.newInstance();
				} catch (Exception e) {
					throw new RuntimeException("实例化对象失败");
				}
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					// 获取setter方法
					String fieldName = field.getName();
					String upFieldName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Column column = field.getAnnotation(Column.class);
					if (null != column) {// 如果有指定字段名
						Object columnValue = rs.getObject(column.value());
						try {
							Method setMethod = clazz.getDeclaredMethod(upFieldName, field.getType());
							setMethod.invoke(result, columnValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {// 如果没有指定字段名
						Object columnValue = rs.getObject(fieldName);
						try {
							Method setMethod = clazz.getDeclaredMethod(upFieldName, field.getType());
							setMethod.invoke(result, columnValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				resultList.add((T) result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeResQuery(rs);
		}

		return resultList;
	}

	@Override
	public <T> List<T> query(Class<?> clazz, String sql, Object... objects) {
		List<T> resultList = new ArrayList<T>();
		// 执行sql语句并给实体对象赋值
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = JDBCUtil.getPstmt(sql);
			if(null != objects && objects.length > 0) {
				int count = 1;
				for(Object param : objects) {
					pstmt.setObject(count++, param);
				}
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Object result = null;
				try {
					result = clazz.newInstance();
				} catch (Exception e) {
					throw new RuntimeException("实例化对象失败");
				}
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					// 获取setter方法
					String fieldName = field.getName();
					String upFieldName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Column column = field.getAnnotation(Column.class);
					if (null != column) {// 如果有指定字段名
						Object columnValue = rs.getObject(column.value());
						try {
							Method setMethod = clazz.getDeclaredMethod(upFieldName, field.getType());
							setMethod.invoke(result, columnValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {// 如果没有指定字段名
						Object columnValue = rs.getObject(fieldName);
						try {
							Method setMethod = clazz.getDeclaredMethod(upFieldName, field.getType());
							setMethod.invoke(result, columnValue);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				resultList.add((T) result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeResQuery(rs);
		}

		return resultList;
	}
	
	@Override
	public <T> Serializable save(T t) {
		// 实体对象非空校验
		if (null == t) {
			throw new IllegalArgumentException("传入实体对象非法，不允许为空");
		}
		// 拼装sql语句
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		Class<?> clazz = t.getClass();
		sql.append(this.getTableName(clazz) + " (");
		Field[] fields = clazz.getDeclaredFields();
		int columnCount = 0;
		for (Field field : fields) {
			String columnName = null;
			Column column = field.getDeclaredAnnotation(Column.class);
			Id idAnno = field.getDeclaredAnnotation(Id.class);
			if (null != column) {
				columnName = column.value();
			} else {
				columnName = field.getName();
			}
			
			if(null == idAnno) {// id字段不需要插入值
				sql.append(columnName + ",");
				columnCount ++;
			}
		}
		sql.deleteCharAt(sql.length() - 1);// 删除最后一个逗号
		sql.append(") values (");
		for(int i=0;i<columnCount;i++) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);// 删除最后一个逗号
		sql.append(")");
		System.out.println("sql语句为:" + sql.toString());
		// 执行sql语句
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String id = null;
			for (Field field : fields) {
				Id idAnno = field.getDeclaredAnnotation(Id.class);
				if(null != idAnno) {
					id = idAnno.value();
				}
			}
			pstmt = JDBCUtil.getPstmt(sql.toString(),new String[] {id});
			int paramCount = 1;
			for (Field field : fields) {
				Id idAnno = field.getDeclaredAnnotation(Id.class);
				// 获取getter方法
				String fieldName = field.getName();
				String upFieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Method getMethod = clazz.getDeclaredMethod(upFieldName);
					Object fieldValue = getMethod.invoke(t);
					if(null == idAnno) {// id字段不需要插入值
						pstmt.setObject(paramCount++, fieldValue);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			int num = pstmt.executeUpdate();
			if(num > 0) {// 如果影响行数大于0
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					return (Serializable) rs.getObject(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeResQuery(rs);
		}
		return null;
	}

	/**
	 * 获取表名
	 * 
	 * @param clazz
	 * @return
	 */
	private String getTableName(Class<?> clazz) {
		String tableName = "";
		Table table = clazz.getAnnotation(Table.class);
		if (null != table) {// 如果实体bean上有注解
			String tableNameTemp = table.value();
			if (StringUtils.isNotBlank(tableNameTemp)) {
				tableName = tableNameTemp;
			} else {
				tableName = clazz.getSimpleName();
			}
		} else {// 如果实体bean上没有注解，则直接用类名作为表名
			tableName = clazz.getSimpleName();
		}
		return tableName;
	}

}
