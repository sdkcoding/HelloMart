package com.hellomart.util;

import java.util.Vector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	private HttpServletRequest request;
	private HttpServletResponse response;

	public CookieUtils(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public void creatCookie(String key, String value, int period){
		Cookie c = new Cookie(key, value);
		c.setMaxAge(period);
		response.addCookie(c);
	}

	public String[] getAllValue() {
		Cookie[] cookies = request.getCookies();
		int length = cookies.length;
		String[] values = new String[length];
		
		for(int i = 0; i < length; i++) {
			values[i] = cookies[i].getValue();
		}
		
		return values;
	}

	public Vector<String> getAllValueWithKeyWord(String keyword) {
		Cookie[] cookies = request.getCookies();
		Vector<String> values = new Vector<>();
		int length = cookies.length;
		
		for(int i = 0; i < length; i++) {
			String name = cookies[i].getName();
			String value = cookies[i].getValue();
			if(name.indexOf(keyword) != -1) {
				values.add(value);
			}
		}
		
		return values;
	}
	
	public String getValue(String name){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
	    	for(int i=0; i < cookies.length; i++){
	    		if(name.equals(cookies[i].getName())){
	    			return cookies[i].getValue();
	    		}
	        }
	    }
		return null;
	}

	public void removeAll(){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
	    	for(int i=0; i < cookies.length; i++){
	    		cookies[i].setMaxAge(0) ;
	    		response.addCookie(cookies[i]) ;
	        }
	    }
	}

	public void remove(int size, String keyword) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			int count = 0;
			for(int i = 0; i < cookies.length || count < size; i++) {
				Cookie cookie = cookies[i];
				String name = cookie.getName();
				if(name.indexOf(keyword) == -1) {
					continue;
				}
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				count++;
			}
		}
	}
	
	public void remove(String name){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
	    	for(int i=0; i < cookies.length; i++){
	    		if(name.equals(cookies[i].getName())){
	    			cookies[i].setMaxAge(0) ;
		    		response.addCookie(cookies[i]) ;
		    		break;
	    		}
	        }
	    }
	}
	
	public int length(String keyword) {
		Cookie[] cookies = request.getCookies();
		int length = 0;
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String name = cookie.getName();
				if(name.indexOf(keyword) != -1) {
					length++;
				}
			}
		}
		return length;
	}

}