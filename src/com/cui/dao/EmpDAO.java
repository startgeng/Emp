package com.cui.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cui.pojo.Emp;
//导包：ctrl+shift+O
import com.cui.utils.DBTools;

public class EmpDAO {

	// 登录
	public Emp login(String empno, String ename) {
		Connection conn = DBTools.getConn();
		QueryRunner runner = new QueryRunner();
		try {
			Emp emp = runner.query(conn, "select * from emp where empno=? and ename=?", new BeanHandler<Emp>(Emp.class),
					empno, ename.toUpperCase());
			DbUtils.close(conn);
			return emp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 查询所有员工
	public List<Emp> findAll() {
		Connection conn = DBTools.getConn();
		QueryRunner runner = new QueryRunner();
		try {
			List<Emp> list = runner.query(conn, "select * from emp", new BeanListHandler<Emp>(Emp.class));
			DbUtils.closeQuietly(conn);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 根据姓名模糊查询
	public List<Emp> findLikeName(String name) {
		Connection conn = DBTools.getConn();
		QueryRunner runner = new QueryRunner();
		try {
			List<Emp> list = runner.query(conn, "select * from emp where ename like ?",
					new BeanListHandler<Emp>(Emp.class), "%" + name.toUpperCase() + "%");
			DbUtils.closeQuietly(conn);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 求各个岗位的平均工资
	// aaa:3000
	// bb:4000
	// job:aaa sal:4000

	public List<Map<String, Object>> avgSal() {

		ResultSetHandler<List<Map<String, Object>>> myHandler = new ResultSetHandler<List<Map<String, Object>>>() {

			@Override
			public List<Map<String, Object>> handle(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("job", rs.getString(1));
					map.put("avg", rs.getDouble(2));
					list.add(map);
				}

				return list;
			}
		};

		Connection conn = DBTools.getConn();
		QueryRunner runner = new QueryRunner();
		try {
			List<Map<String, Object>> list = runner.query(conn, "select job,avg(sal) from emp group by job", myHandler);
			DbUtils.closeQuietly(conn);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
