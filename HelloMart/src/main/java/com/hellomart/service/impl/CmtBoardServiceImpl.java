package com.hellomart.service.impl;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellomart.dao.CmtBoardDAO;
import com.hellomart.dto.CmtBoard;
import com.hellomart.service.CmtBoardService;

@Service
public class CmtBoardServiceImpl implements CmtBoardService{
   
   @SuppressWarnings("unused")
private static final Logger logger = LoggerFactory.getLogger(CmtBoardService.class);
   
   @Autowired
   private CmtBoardDAO dao;
   
   public CmtBoardServiceImpl() {
      
   }



   @Override
   public Vector<CmtBoard> cmtlist(int idx, int startRow, int pageSize) {
      return dao.cmtlist(idx, startRow, pageSize);
   }


   @Override
   public void cmtinsert(CmtBoard cmtboard) {
      dao.cmtinsert(cmtboard);
   }


   @Override
   public int cmtCount(int idx) {
      return dao.cmtCount(idx);
   }

   @Override
   public void cmtdelete(int cmtidx) {
      dao.cmtdelete(cmtidx);      
   }

}