package com.cui.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.junit.Test;

import com.cui.dao.EmpDAO;
import com.cui.pojo.Emp;

public class TestEMP {
	EmpDAO dao = new EmpDAO();
	@Test
	public void testLogin() {
		
		Emp emp = dao.login("7788", "scott11");
		if(emp!=null){
			System.out.println("µÇÂ¼³É¹¦");
		}else{
			System.out.println("µÇÂ¼Ê§°Ü");
		}
	}
	@Test
	public void testAll(){
		List<Emp> list = dao.findAll();
		
		JSONArray array = new JSONArray(list);
		
		System.out.println(array);
	}
	@Test
	public void testAvg(){
		List<Map<String, Object>> list = dao.avgSal();
		JSONArray array = new JSONArray(list);
		
		System.out.println(array);
	}

}
