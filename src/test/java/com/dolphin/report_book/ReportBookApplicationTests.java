package com.dolphin.report_book;

import com.auth0.jwt.JWT;
import com.dolphin.report_book.entity.Admin;
import com.dolphin.report_book.entity.User;
import com.dolphin.report_book.mapper.TeacherMapper;
import com.dolphin.report_book.service.AdminService;
import com.dolphin.report_book.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class ReportBookApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private AdminService adminService;
	@Test
	void Test(){
		System.out.println(teacherMapper.listTeachers());
	}
	@Test
	void Token(){
		String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsibm8iOiJhZG1pbiIsImlkIjoxfSwiZXhwIjoxNzMyNjE1OTc2fQ.9GjesdDHc2vxAPywnNrsq5vzTTMDffqgYYkUnXXD1VA";
		Map<String,Object> map= JwtUtil.parseToken(token);
		String username=(String)map.get("no");
		Admin admin=adminService.getByNo(username);
		System.out.println("admin------>"+admin);
	}

}
