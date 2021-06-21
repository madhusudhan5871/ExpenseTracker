package com.expensetracker.resource;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.expensetracker.db.Category;
import com.expensetracker.db.Expense;
import com.expensetracker.service.CategoryService;
import com.expensetracker.service.ExpenseService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
@Path("/expense")
public class ExpenseResource {
	@Inject
	private ExpenseService expenseService;

	@Inject
	private CategoryService categoryService;

	@POST
	@Path("add")
	public void add(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws IOException, ParseException {
		String name = req.getParameter("name");
		String date = req.getParameter("date");
		String category = req.getParameter("category");
		Integer price = Integer.parseInt(req.getParameter("price"));
		expenseService.add(name, price, date, category);
		res.sendRedirect("view");
	}

	@GET
	@Path("add-view")
	public void addView(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws IOException, ServletException {
		List<Category> categoryList = categoryService.findAll();
		req.setAttribute("categoryList", categoryList);
		req.getRequestDispatcher("/add.jsp").forward(req, res);
	}

	@GET
	@Path("update-view")
	public void updateView(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws IOException, ServletException {
		Expense expense = expenseService.view(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("expense", expense);
		req.getRequestDispatcher("/update.jsp").forward(req, res);
	}

	@GET
	@Path("view")
	public void viewAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ServletException, IOException {
		String searchName = req.getParameterMap().containsKey("searchname") ? "%" + req.getParameter("searchname") + "%"
				: "%";
		req.setAttribute("expenseList", expenseService.viewAll(searchName));
		req.getRequestDispatcher("/view.jsp").forward(req, res);
	}

	@GET
	@Path("delete")
	public void delete(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		expenseService.delete(id);
		res.sendRedirect("view");
	}

	@POST
	@Path("update")
	public void update(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws IOException, ParseException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String date = req.getParameter("date");
		String name = req.getParameter("name");
		Integer price = Integer.parseInt(req.getParameter("price"));
		expenseService.update(id, name, price, date);
		res.sendRedirect("view");
	}
}
