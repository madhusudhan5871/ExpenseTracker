package com.expensetracker.module;

import com.expensetracker.resource.ExpenseResource;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class ExpenseTrackerModule extends AbstractModule {
	
	
	public void configure() {
		
		bind(ExpenseResource.class);

	}
}
