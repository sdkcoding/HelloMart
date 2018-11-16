package com.hellomart.dao;

import java.util.List;

import com.hellomart.dto.Point;

public interface PointDAO {
	public void insertPoint(Point point);
	public List<Point> getAllPointLog(String id);
	public List<Point> getPeriodPointLog(String id, String startDate, String endDate);
}