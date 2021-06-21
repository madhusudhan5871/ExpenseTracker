package com.expensetracker.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.expensetracker.db.Expense;
import com.google.inject.ImplementedBy;

@ImplementedBy(ExpenseServiceImpl.class)
public interface ExpenseService {
	void add(HttpServletRequest req) throws ParseException;
	List<Expense> viewAll(HttpServletRequest req);
	Expense view(Integer id);
	void delete(Integer id);
	void update(@Context HttpServletRequest req) throws ParseException;	
}
