package com.hellomart.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.hellomart.dto.Account;

public interface AccountService {
	
	String getApplyStatus(String id);
	
	/**
	 * 아이디 존재 여부를 확인하기 위해 id 컬럼을 조건으로 카운트한다. 
	 * 
	 * @param id 유저 아이디
	 * @return 아이디가 이미 존재하면 1, 아니면 0
	 */
	int countId(String id);
	
	/**
	 * 이메일 존재 여부를 확인하기 위해 email 컬럼을 조건으로 카운트한다. 
	 * 
	 * @param email 유저 이메일
	 * @return 이메일이 이미 존재하면 1, 아니면 0
	 */
	int countEmail(String email);
	
	String getPasswd(String id);
	
	void modifyPw(String id,String new_pw);
	
	void updateAccount(Account account);
	
	/**
	 * <p>로그인 정보를 가져온다.
	 * 
	 * <ul>로그인 정보
	 * 	<li>PASSWORD
	 * 	<li>Authority
	 * </ul>
	 * 
	 * @return 로그인 정보를 담은 Account 객체
	 */
	Account findAccount(String id);
	
	/**
	 * 새로운 유저를 등록시킨다.
	 * 
	 * @param account 계정 정보를 담고있는 객체
	 */
	void insertAccount(Account account);
	
	Account getInfo(String id);
	
	/**
	 * id와 일치하는 계정들을 삭제한다.
	 * 
	 * @param id 삭제할 계정의 아이디
	 */
	void deleteAccount(String id);
	
	/**
	 * id와 일치하는 계정들을 삭제한다.
	 * 
	 * @param id 삭제할 계정의 아이디
	 */
	void deleteAccountList(List<String> idList);

	/**
	 * 계정 테이블의 총 행의 수를 반환한다.
	 * 
	 * @return 계정 테이블의 총 행의 수
	 */
	int count();
	
	/**
	 * 계정 테이블의 모든 계정 리스트를 반환한다.
	 * 
	 * @return 계정 테이블의 모든 계정 리스트
	 */
	void accountList(int pageNum, Model model, 
				Map<String, Object> searchData, String servletPath);
	
	/**
	 * SELLER_READY 권한을 가지고 있는 계정들을 모두 SELLER 권한으로 바꾼다.
	 * 
	 * @param sellerAc 권한을 바꿀 id 리스트
	 */
	void sellerApproval(List<String> idList);

	void searchIDPW(String email);
	
	int sellerRegist(String id);
}