package com.expensetracker.resource;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.HttpResponse;

import com.expensetracker.db.Expense;
import com.expensetracker.service.ExpenseServiceImpl;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
@Path("/expense")
public class ExpenseResource {

	@Inject
	private EntityManager em;

	@Inject
	private ExpenseServiceImpl expenseService;

	@POST
	@Path("add")
	public void add(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException {
		Expense expense = new Expense();
		expense.setName(req.getParameter("name"));
		expense.setPrice(Integer.parseInt(req.getParameter("price")));
		expense.setCategory(req.getParameter("category"));
		expenseService.add(expense);
		res.sendRedirect("view");
	}

	@GET
	@Path("view")
	public void viewAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ServletException, IOException {
		req.setAttribute("expenseList", expenseService.viewAll());
		req.getRequestDispatcher("/view.html").forward(req, res);
	}

	@GET
	@Path("view/{id}")
	public void view(@Context HttpServletRequest req, @Context HttpServletResponse res, @PathParam("id") Integer id)
			throws ServletException, IOException {
		req.setAttribute("expense", expenseService.view(id));
		req.getRequestDispatcher("/viewexpense.html").forward(req, res);
	}

	@GET
	@Path("delete")
	public void delete(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		expenseService.delete(id);
		res.sendRedirect("view");
	}

	@GET
	@Path("update")
	public void update(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException {
		expenseService.update(req);
		res.sendRedirect("view");
	}
}
