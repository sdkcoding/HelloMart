package com.hellomart.service;

import java.util.List;

import com.hellomart.dto.Cart;

public interface CartService {

   // 1. 장바구니 추가
   void insert(Cart cart);
   
   // 2. 장바구니 목록
   List<Cart> listCart(String id);
   
   // 3. 장바구니 삭제
   void deleteCart(String id, int no);
   
   // 4. 장바구니 수정
   void modifyCart(Cart cart);
   
   // 5. 장바구니 금액 합계
   int sumMoney(String id);
   
   // 6. 장바구니 동일한 상품 레코드 확인
   int countCart(int no, String id);
   
   // 7. 장바구니 상품수량 변경
   void updateCart(Cart cart);
   
   // 8. 장바구니 물품 구매시, 장바구니 물품제거
   void deleteCartList(String id);
}