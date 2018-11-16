package com.hellomart.util;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class XMLPaserTest {
	
	private static final Logger logger = LoggerFactory.getLogger(XMLPaserTest.class);
	
	private XMLParser parser = new XMLParser("category.xml"); 
	
	
	@Before
	public void before() {
		
	}
	
	@Test
	public void getChildrenTest() throws Exception {
		Vector<String> columns = parser.getChildren("냉장고");
		
		logger.debug(columns.toString());
	}
	
	@Test
	public void getAttributeValueTest() {
		String table = parser.getAttributeValue("가전제품", "냉장고", "table");
		String column = parser.getAttributeValue("냉장고", "소비전력", "column");
		logger.debug("table : " + table);
		logger.debug("column : " + column);
	}
	
	@Test
	public void getValueTest() {
		String value = parser.getValue("냉장고", "소비전력");
		
		logger.debug(value);
	}
	
}
