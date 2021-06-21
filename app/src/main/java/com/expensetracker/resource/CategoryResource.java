package com.expensetracker.resource;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.expensetracker.service.CategoryService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@Path("/category")
public class CategoryResource {
	@Inject
	private CategoryService categoryService;
	
	@POST
	@Path("add")
	public void add(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws IOException, ParseException {
		String categoryName = req.getParameter("name");
		categoryService.add(categoryName);
		res.sendRedirect("/app/expense/add-view");
	}
}
