package com.expensetracker.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.expensetracker.db.Expense;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
@Transactional
public class ExpenseServiceImpl {
	@Inject
	Provider<EntityManager> em;
	
	@Inject
	private Logger logger;
	
	public void add(Expense expense) {
		try{	
			em.get().persist(expense);
		}catch(Exception e) {
			logger.info("Unable to add. An exception has occured: "+e.getMessage()+e.getCause()+e.getStackTrace());
			throw e;
		}
	}
	
	public List<Expense> viewAll(){
		List<Expense> expenseList = em.get().createQuery("from Expense").getResultList();
		return expenseList;
	}
	
	public Expense view(Integer id) {
		Expense expense = em.get().find(Expense.class, id);
		return expense;
	}
	
	public void delete(Integer id) {
		try {
			Expense expense = em.get().find(Expense.class, id);
			em.get().remove(expense);
		}catch(Exception e) {
			logger.info("Unable to delete. AN exception has occured: "+e.getMessage()+e.getCause()+e.getStackTrace());
		}
	}
	
	public void update(@Context HttpServletRequest req) {
		Expense expense = em.get().find(Expense.class, Integer.parseInt(req.getParameter("id")));
		expense.setCategory(req.getParameter("category"));
		expense.setPrice(Integer.parseInt(req.getParameter("price")));
		expense.setName(req.getParameter("name"));
	}
}
