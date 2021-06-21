package com.expensetracker.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.expensetracker.db.Expense;
import com.google.inject.ImplementedBy;

@ImplementedBy(ExpenseServiceImpl.class)
public interface ExpenseService {
	void add(String name,Integer price,String date,String category) throws ParseException;
	List<Expense> viewAll(String searchName);
	Expense view(Integer id);
	void delete(Integer id);
	void update(Integer id,String name,Integer price,String date) throws ParseException;	
}
