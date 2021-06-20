package com.expensetracker.resource;

import java.io.IOException;
import java.text.ParseException;
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

import com.expensetracker.db.Category;
import com.expensetracker.db.Expense;
import com.expensetracker.service.CategoryServiceImpl;
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
	
	@Inject
	private CategoryServiceImpl categoryService;

	@POST
	@Path("add")
	public void add(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException,ParseException {
		expenseService.add(req);
		res.sendRedirect("view");
	}
	
	@GET
	@Path("add-view")
	public void addView(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException, ServletException {
		List<Category> categoryList = categoryService.findAll();
		req.setAttribute("categoryList", categoryList);
		req.getRequestDispatcher("/add.jsp").forward(req,res);
	}
	
	@GET
	@Path("update-view")
	public void updateView(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException,ServletException{
		Expense expense = expenseService.view(Integer.parseInt(req.getParameter("id")));
		req.setAttribute("expense", expense);
		req.getRequestDispatcher("/update.jsp").forward(req, res);
	}

	@GET
	@Path("view")
	public void viewAll(@Context HttpServletRequest req, @Context HttpServletResponse res)
			throws ServletException, IOException {
		req.setAttribute("expenseList", expenseService.viewAll(req));
		req.getRequestDispatcher("/view.jsp").forward(req, res);
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

	@POST
	@Path("update")
	public void update(@Context HttpServletRequest req, @Context HttpServletResponse res) throws IOException,ParseException {
		expenseService.update(req);
		res.sendRedirect("view");
	}
}
