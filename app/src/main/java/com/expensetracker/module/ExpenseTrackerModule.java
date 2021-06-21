package com.expensetracker.module;

import com.expensetracker.resource.ExpenseResource;
import com.google.inject.AbstractModule;

public class ExpenseTrackerModule extends AbstractModule {

	public void configure() {

		bind(ExpenseResource.class);

	}
}
