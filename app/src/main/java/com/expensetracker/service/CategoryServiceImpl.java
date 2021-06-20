package com.expensetracker.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.expensetracker.db.Category;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
@Transactional
public class CategoryServiceImpl {
	@Inject
	Provider<EntityManager> em;
	
	@Inject
	private Logger logger;
	
	public List<Category> findAll() {
		List<Category> categoryList = em.get().createQuery("from Category").getResultList();
		return categoryList;
	}
}
