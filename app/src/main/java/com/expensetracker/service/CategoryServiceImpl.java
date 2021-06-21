package com.expensetracker.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.expensetracker.db.Category;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Inject
	Provider<EntityManager> emp;

	public List<Category> findAll() {
		List<Category> categoryList = emp.get().createQuery("from Category").getResultList();
		return categoryList;
	}
	
	public void add(String categoryName) {
		Category category = new Category();
		category.setCategoryName(categoryName);
		emp.get().persist(category);
	}
}
