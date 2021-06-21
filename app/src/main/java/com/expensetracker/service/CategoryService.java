package com.expensetracker.service;

import java.util.List;

import com.expensetracker.db.Category;
import com.google.inject.ImplementedBy;

@ImplementedBy(CategoryServiceImpl.class)
public interface CategoryService {
	List<Category> findAll();
	void add(String categoryName);
}
