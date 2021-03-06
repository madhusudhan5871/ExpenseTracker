package com.expensetracker.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.expensetracker.db.Category;
import com.expensetracker.db.Expense;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
	@Inject
	Provider<EntityManager> emp;

	@Inject
	private Logger logger;

	public void add(String name, Integer price, String date, String categorystring) throws ParseException {
		try {
			Expense expense = new Expense();
			expense.setName(name);
			expense.setPrice(price);
			Category category = emp.get().find(Category.class, categorystring);
			expense.setCategory(category);
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			expense.setDate(date1);
			emp.get().persist(expense);
		} catch (Exception e) {
			logger.info(
					"Unable to add. An exception has occured: " + e.getMessage() + e.getCause() + e.getStackTrace());
			throw e;
		}
	}

	public List<Expense> viewAll(String searchName) {
		List<Expense> expenseList = emp.get()
				.createQuery("select e from Expense e where e.name like ?1 order by e.name").setParameter(1, searchName)
				.getResultList();
		return expenseList;
	}

	public Expense view(Integer id) {
		Expense expense = emp.get().find(Expense.class, id);
		return expense;
	}

	public void delete(Integer id) {
		try {
			Expense expense = emp.get().find(Expense.class, id);
			emp.get().remove(expense);
		} catch (Exception e) {
			logger.info(
					"Unable to delete. AN exception has occured: " + e.getMessage() + e.getCause() + e.getStackTrace());
			throw e;
		}
	}

	public void update(Integer id, String name, Integer price, String date) throws ParseException {
		try {
			Expense expense = emp.get().find(Expense.class, id);
			expense.setPrice(price);
			expense.setName(name);
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			expense.setDate(date1);
		} catch (Exception e) {
			logger.info(
					"Unable to update. An exception has occured " + e.getMessage() + e.getCause() + e.getStackTrace());
			throw e;
		}
	}
}
