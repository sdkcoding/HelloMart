package com.helloart.test.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hellomart.dao.AccountDAO;
import com.hellomart.dto.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class AccountDaoTest {
	 
	@Autowired
	private AccountDAO dao;
	
	private String wrongId = "papayaza222";
	
	private String id = "papayaza111";
	private String password = "practice123";
	private String role = "MEMBER";
	private int points = 0;
	private int grade = 0;
	private String email = "papayaza111@gmail.com";
	private String postCode = "12345";
	private String roadAddress = "Road Address";
	private String detailAddress = "Detail Address";
	private String phone = "010-1234-5678";
	private String name = "홍길동";
	private String birthYear = "2000";
	private String birthMonth = "03";
	private String birthDay = "01";
	private char gender = 'F';
	private int reliability = 0;
	
	@Before
	public void before() {
		dao.truncate();
	}
	
	@After
	public  void after() {
		dao.truncate();
	}
	
	/*
	 * DB에 저장할 Account 객체를 생성하여 값을 저장한 다음 반환한다. 
	 */
	private Account getNewAccount() {
		Account account = new Account();
		
		account.setId(id);
		account.setPassword(password);
		account.setEmail(email);
		account.setPostCode(postCode);
		account.setRoadAddress(roadAddress);
		account.setDetailAddress(detailAddress);
		account.setPhone(phone);
		account.setName(name);
		account.setBirthYear(birthYear);
		account.setBirthMonth(birthMonth);
		account.setBirthDay(birthDay);
		account.setGender(gender);
		
		return account;
	}
	
	@Test
	public void methodTest() {
		Account a1 = getNewAccount();
		
		/* 계정을 등록시킨다. */
		dao.insertAccount(a1);
		
		/* 한 개의 행이 존재하는지 확인한다. */
		int count = dao.count();
		assertEquals(count, 1);
		
		/* 일치하는 아이디가 없는 계정 정보를 불러온다. */
		Account a2 = dao.getInfo(wrongId);
		
		/* null 값이라면 정상! */
		assertThat(a2, is(nullValue()));
		
		/* 일치하는 계정 정보를 불러온다. */
		Account a3 = dao.getInfo(id);
		
		/* null 값이 아니라면 정상! */
		assertThat(a3, is(not(nullValue())));
		
		/* DB에서 가져온 JoinDate값이 null이 아닌지 확인한다. */
		assertThat(a3.getJoinDate(), is(not(nullValue())));
		
		/* a1과 a3를 비교하기 위해 a1에 DB에서 기본으로 설정되는 값들을 저장한다. */
		a1.setRole(role);
		a1.setPoints(points);
		a1.setGrade(grade);
		a1.setReliability(reliability);
		a1.setJoinDate(a3.getJoinDate());
		
		/* 자바에서 생성한 a1객체와 DB에서 가져온 a3객체가 같은 값들을 가지고 있는지 확인한다. */
		assertThat(a1.equals(a3), is(true));
		
		/* 계정을 삭제한다. */
		dao.deleteAccount(id);
		
		/* 계정 삭제 후 테이블의 행의 수가 0인지 확인한다. */
		count = dao.count();
		assertEquals(count, 0);
	}
	
}