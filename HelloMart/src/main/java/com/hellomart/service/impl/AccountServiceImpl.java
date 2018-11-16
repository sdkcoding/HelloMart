package com.hellomart.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hellomart.dao.AccountDAO;
import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;
import com.hellomart.util.EmailUtils;
import com.hellomart.util.Page;
import com.hellomart.util.TokenGenerator;
import com.hellomart.util.mail.SMTPAuthenticator;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountDAO dao;
	
	@Resource(name = "bbsPage")
	private Page page;
	
	public AccountServiceImpl() {
	}
	
	@Override
	public String getApplyStatus(String id) {
		return dao.getApplyStatus(id);
	}
	
	@Override
	public int countId(String id) {
		return dao.countId(id);
	}
	
	@Override
	public int countEmail(String email) {
		return dao.countEmail(email);
	}
	
	@Override
	public Account findAccount(String id) {
		return dao.findAccount(id);
	}
	
	@Override
	public void insertAccount(Account account) {
		dao.insertAccount(account);
	}
	
	@Override
	public Account getInfo(String id) {
		return dao.getInfo(id);
	}

	@Override
	public void deleteAccount(String id) {
		dao.deleteAccount(id);
	}
	
	@Override
	public void deleteAccountList(List<String> idList) {
		for(String id : idList){
			dao.deleteAccount(id);
		}
	}

	@Override
	public int count() {
		return dao.count();
	}
	
	@Override
	public void accountList(int pageNum, Model model, 
				Map<String, Object> searchData, String servletPath) {
		int totalCount = 0;
		int pageSize = 5;// 한페이지에 보여줄 글의 갯수
		int pageBlock = 10; //한 블럭당 보여줄 페이지 갯수
		ArrayList<Account> accountList;
		HashMap<String, Object> paramMap;
		paramMap = new HashMap<>();
		paramMap.put("flag", 0);
		if(searchData != null){
			paramMap.put("flag", 1);
			String id = (String)searchData.get("id");
			String accountRole = (String)searchData.get("accountRole");
			String sellerApply = (String)searchData.get("sellerApply");
			if(!id.equals("")){
				paramMap.put("id", id);
			}
			if(!accountRole.equals("")){
				paramMap.put("accountRole", accountRole);
			}
			if(!sellerApply.equals("")){
				paramMap.put("sellerApply", sellerApply);
			}
		}
		
		totalCount = dao.accountCount(paramMap);
		page.paging(pageNum, totalCount, pageSize, pageBlock, servletPath);
		paramMap.put("startRow", page.getStartRow());
		paramMap.put("endRow", page.getEndRow());
		accountList = dao.accountList(paramMap);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCode", page.getSb().toString());
		model.addAttribute("accountList", accountList);
	}

	@Override
	public void sellerApproval(List<String> idList) {
		for(String id : idList){
			dao.sellerApproval(id);
			dao.sellerProgressDelete(id);
		}
	}

	@Override
	public void updateAccount(Account account) {
		dao.updateAccount(account);
	}

	@Override
	public String getPasswd(String id) {
		return dao.getPasswd(id);
	}

	@Override
	public void modifyPw(String id, String new_pw) {
		dao.modifyPw(id,new_pw);
	}
	
	@Override
	public void searchIDPW(String email) {
		String id = dao.findIdByEmail(email);
		String password = TokenGenerator.getTokenString(5);
		
		Authenticator auth = new SMTPAuthenticator("papayaza999", "practice123");
		new Thread(() -> {
			EmailUtils eu = new EmailUtils();
			eu.setAuth(auth);
			String from = "papayaza999@gmail.com";
			String subject = "HelloMart 아이디/비밀번호 찾기";
			String content = "ID : " + id + "<br>" + "새 비밀번호 : " + password + "<br>";
			try {
				eu.sendEmail(from, email, subject, content);
			} catch (MessagingException e) {
				logger.info("Fail to send email to " + email);
				return;
			}
			dao.modifyPw(id, password);
		}).start();
	}

	@Override
	public int sellerRegist(String id){
	    return dao.sellerRegist(id);
	}

}
