package com.expensetracker.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.expensetracker.db.Category;
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
	
	public void add(@Context HttpServletRequest req) throws ParseException {
		try{	
			Expense expense = new Expense();
			expense.setName(req.getParameter("name"));
			expense.setPrice(Integer.parseInt(req.getParameter("price")));
			//expense.setCategory(req.getParameter("category"));
			Category category;
			if(req.getParameter("newCategory").isEmpty()) {
				category = em.get().find(Category.class, req.getParameter("category"));
			}else {
				category = new Category();
				category.setCategoryName(req.getParameter("newCategory"));
			}expense.setCategory(category);
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
			expense.setDate(date1);
			em.get().persist(expense);
		}catch(Exception e) {
			logger.info("Unable to add. An exception has occured: "+e.getMessage()+e.getCause()+e.getStackTrace());
			throw e;
		}
	}
	
	public List<Expense> viewAll(@Context HttpServletRequest req){
		String searchName = req.getParameterMap().containsKey("searchname")?"%"+req.getParameter("searchname")+"%":"%";
		List<Expense> expenseList = em.get().createQuery("select e from Expense e where e.name like ?1 order by e.name").setParameter(1, searchName).getResultList();
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
			throw e;
		}
	}
	
	public void update(@Context HttpServletRequest req) throws ParseException{
		try {
		Expense expense = em.get().find(Expense.class, Integer.parseInt(req.getParameter("id")));
		expense.setPrice(Integer.parseInt(req.getParameter("price")));
		expense.setName(req.getParameter("name"));
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
		expense.setDate(date1);
		}catch(Exception e) {
			logger.info("Unable to update. An exception has occured "+e.getMessage()+e.getCause()+e.getStackTrace());
			throw e;
		}
	}
}
