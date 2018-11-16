package com.hellomart.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hellomart.dto.Cart;
import com.hellomart.dto.ProductList;
import com.hellomart.service.CartService;
import com.hellomart.service.ProductService;

@Controller
public class CartController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	CartService service;
	
	@Autowired
	ProductService service2;
	
	// 1-1. 장바구니 추가(페이지 이동)
	@RequestMapping(value = "/addCart", method=RequestMethod.GET)
	public String addCart(@ModelAttribute Cart cart, Principal principal){
		String id = principal.getName(); 
			
		cart.setId(id);
		// 장바구니에 기존 상품이 있는지 검사
		int count = service.countCart(cart.getNo(), id);
		if(count == 0){
			// 없으면 insert
			service.insert(cart);
		} else{
			// 있으면 update
			service.updateCart(cart);
		}
		return "redirect:/mypage/cartlist?id="+id;	
	}
	
	// 1-2. 장바구니 추가 - 페이지유지
	@RequestMapping(value = "/addCartNo", method=RequestMethod.GET)
	public void addCartNo(@ModelAttribute Cart cart, Principal principal){
		String id = principal.getName();
		cart.setId(id);
		// 장바구니에 기존 상품이 있는지 검사
		int count = service.countCart(cart.getNo(), id);
		if(count == 0){
			// 없으면 insert
			service.insert(cart);
		} else{
			// 있으면 update
			service.updateCart(cart);
		}
	}
	

	
	// 2. 장바구니 목록
    @RequestMapping(value = "mypage/cartlist", method=RequestMethod.GET)
    public ModelAndView list(ModelAndView mav, Principal principal){
    	String id = principal.getName();
    	/* 쿼리 조인 결과를 받을 커스텀 맵 */
    	ProductList productList = new ProductList();
    	Cart cart = new Cart();
    	Map<String, Object> dtomap = new HashMap<String, Object>();
    	dtomap.put("cart", cart);
    	dtomap.put("productlList", productList);
    	
        Map<String, Object> map = new HashMap<String, Object>();
        List<Cart> list = service.listCart(id); // 장바구니 정보 
        int sumMoney = service.sumMoney(id); // 장바구니 전체 금액 호출
        // 장바구니 전체 긍액에 따라 배송비 구분
        // 배송료(10만원이상 => 무료, 미만 => 2500원)
        int fee = sumMoney >= 100000 ? 0 : 2500;
        map.put("list", list);                // 장바구니 정보를 map에 저장
        map.put("count", list.size());        // 장바구니 상품의 유무
        map.put("sumMoney", sumMoney);        // 장바구니 전체 금액
        map.put("fee", fee);                 // 배송금액
        map.put("allSum", sumMoney+fee);    // 주문 상품 전체 금액
        mav.setViewName("mypage/cartList");    // view(jsp)의 이름 저장
        mav.addObject("map", map);            // map 변수 저장
        
        return mav;
    }
    
    // 3. 장바구니 삭제
    @RequestMapping(value="mypage/cartdelete", method=RequestMethod.GET)
    public String delete(@RequestParam int no, Principal principal){
    	String id = principal.getName();
        service.deleteCart(id, no);
        return "redirect:/mypage/cartlist?id="+id;
    }
    
    // 4. 장바구니 수정
    @RequestMapping(value="mypage/cartmodify", method=RequestMethod.POST)
    public String update(@RequestParam int[] orderCount, @RequestParam int[] no, Principal principal) {
        // session의 id
        String id = principal.getName();
        // 레코드의 갯수 만큼 반복문 실행
        for(int i=0; i<no.length; i++){
            Cart cart = new Cart();
            cart.setId(id);
            cart.setOrderCount(orderCount[i]);
            cart.setNo(no[i]);
            service.modifyCart(cart);
        }

        return "redirect:/mypage/cartlist?id="+id;
    }
    	
}