package com.hellomart.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.Account;
import com.hellomart.dto.OrderList;
import com.hellomart.dto.ReView;
import com.hellomart.service.AccountService;
import com.hellomart.service.HistoryService;
import com.hellomart.service.PointService;
import com.hellomart.validator.DeleteAccountValidator;
import com.hellomart.validator.ModifyAccountInfoValidator;
import com.hellomart.validator.ModifyPasswordValidator;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired 
	private AccountService service;
	
	@Autowired
	private PointService pointService;
	
	@Autowired
	private HistoryService historyservice;

	@Autowired
	private DeleteAccountValidator deleteAccountValidator;
	
	@Autowired
	private ModifyPasswordValidator modifyPasswordValidator;
	
	@RequestMapping("/menu")
	public void main(Model model, Principal principal) {
		String username = principal.getName();
		String status = service.getApplyStatus(username);
		model.addAttribute("status", status);
	}
	
	@RequestMapping("/info")
	public ModelAndView info(Principal principal) {
		ModelAndView mav = new ModelAndView();
		
		String id = principal.getName();
		Account account = service.getInfo(id);
		
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "info");
		return mav;
	}
	
	
	@RequestMapping(value="/info/modify",method=RequestMethod.GET)
	public ModelAndView infoModify(Principal principal) {
		
		ModelAndView mav = new ModelAndView();
		
		String id = principal.getName();
		Account account = service.getInfo(id);
		account.setId(id);
		
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "modify");
		return mav;
	}
	
	@RequestMapping(value="/info/modify",method=RequestMethod.POST)
	public ModelAndView Modify(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		new ModifyAccountInfoValidator().validate(account, bindingResult);
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("mypage/info/page");
			mav.addObject("viewPage", "modify");
			return mav;
		}
		service.updateAccount(account);
		mav.setViewName("redirect:/mypage/info");
		return mav;
	}
	
	@RequestMapping(value="/info/modifyPwd",method=RequestMethod.GET)
	public ModelAndView infoModifyPwd() {
		ModelAndView mav = new ModelAndView();
		Account account = new Account();
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "modifyPwd");
		return mav;
	}
	
	@RequestMapping(value="/info/modifyPwd",method=RequestMethod.POST)
	public ModelAndView ModifyPwd(@ModelAttribute("account") Account account,
						  			Principal principal, 
						  			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		String id = principal.getName();
		account.setId(id);
		String new_pw = account.getNew_password();
		
		modifyPasswordValidator.validate(account, bindingResult);
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("mypage/info/page");
			mav.addObject("viewPage", "modifyPwd");
			
			return mav;
		}
		
		service.modifyPw(id, new_pw);
		
		mav.setViewName("redirect:/mypage/info");
		return mav;
		
	}
	
	@RequestMapping(value="/info/delete",method=RequestMethod.GET)
	public ModelAndView infoDelete() {
		ModelAndView mav = new ModelAndView();
		Account account = new Account();
		mav.addObject("account", account);
		mav.setViewName("mypage/info/page");
		mav.addObject("viewPage", "delete");
		return mav;
	}
	
	@RequestMapping(value="/info/delete", method=RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute("account") Account account, Principal principal, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		String id = principal.getName();
		account.setId(id);
		
		deleteAccountValidator.validate(account, bindingResult);
		
		if(bindingResult.hasErrors()) {
			mav.setViewName("mypage/info/page");
			mav.addObject("viewPage", "delete");
			return mav;
		}
		
		service.deleteAccount(id);
		
		mav.setViewName("redirect:/logout");
		return mav;
	}
	
	@RequestMapping("/shoppingcart")
	public void shoppingcart() {
	}

	@RequestMapping("/point")
	public String point(Model model, Principal principal) {
		String id = principal.getName();
		
		model.addAttribute("pointList", pointService.getAllPointLog(id));
		
		return "mypage/pointList"; 
	}
	
	@RequestMapping("/point/period")
	public String pointPeriod(Model model, Principal principal, String startDate, String endDate) {
		String id = principal.getName();
		
		model.addAttribute("pointList", pointService.getPeriodPointLog(id, startDate, endDate + " 24:00:00"));
		model.addAttribute("viewPage", "pointList");
		
		return "mypage/pointList"; 
	}
	
	@RequestMapping(value = "/history", method=RequestMethod.GET)
	public ModelAndView History(ModelAndView mav, Principal principal){
		String id = principal.getName();
    	
        Map<String, Object> map = new HashMap<String, Object>();
		List<OrderList> list = historyservice.historylist(id);
        map.put("list", list);               
        map.put("count", list.size());        
        mav.setViewName("mypage/history");    
        mav.addObject("map", map);            
        
        return mav;
	}
	
	@RequestMapping("/history/period")
	public String historyPeriod(Model model, String id, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrderList> list = historyservice.historyDatelist(id, startDate, endDate + " 24:00:00");
        map.put("list", list);               
        map.put("count", list.size());        
        model.addAttribute("map", map);
        
		return "mypage/history"; 
	}
	
	@RequestMapping("/historyButton")
	public ModelAndView historyButton(ModelAndView mav,Principal principal, HttpServletRequest request ) {
		String id = principal.getName();
		String no = request.getParameter("no");
		ReView review = historyservice.reviewCheck(no, id);
		boolean check = false;
		if(review != null){
			check = true;
			mav.addObject("idx", review.getIdx());
			
		}
		mav.addObject("no", no);
		mav.addObject("check", check);
		mav.setViewName("mypage/historyButton");
		
		return mav;
	}	
	
	@PreAuthorize(value = "hasRole('ROLE_MEMBER')")
	@RequestMapping("/sellerRegist")
    public String sellerRegist(Principal principal){
		String id = principal.getName();
		int result = service.sellerRegist(id);
		
		return "redirect:/mypage/menu?success="+result;
   }
	
}
