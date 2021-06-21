<%@page import ="java.util.List"%>
<%@page import="com.expensetracker.db.Expense"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Expense Tracker</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/pricing/">

    <!-- Bootstrap core CSS -->
    <link href="/app/assets/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/app/assets/pricing.css" rel="stylesheet">
  </head>

  <body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
      <h5 class="my-0 mr-md-auto font-weight-normal">Expense Tracker</h5>
      <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="#"></a>
      </nav>
      <a class="btn btn-outline-primary" href="/app/homepage.html">Go to Home</a>
    </div>

    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      <h1 class="display-4">Expense Tracker</h1>
      <p class="lead">Be in charge of your finances</p>
    </div>

    <div class="container">
      <div class="card-deck mb-3 ">
        <div class="card mb-6 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">View Expenses</h4>
            <form action="view"> 
              <div class="form-row">
    <div class="form-group col-md-6">
      <input type="text" name="searchname" class="form-control" placeholder="search expense..">
    </div>
    <input type="submit" class="btn btn-primary" value="Search">
    </div>
    
            
          </div>
          <div class="card-body">
<table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Expense</th>
      <th scope="col">Category</th>
      <th scope="col">Date</th>
      <th scope="col">Price</th>
      <th scope=""col">Update</th>
      <th scope="col">Delete</th>
    </tr>
  </thead>
  <tbody>
    <% 
      List<Expense> expenseList = (ArrayList<Expense>)request.getAttribute("expenseList");
       int counter = 1;
       for(Expense expense:expenseList){
       out.print("<tr>");
       	out.print("<td>"+counter+"</td>");
       	out.print("<td>"+expense.getName()+"</td>");
       	out.print("<td>"+expense.getCategory().getCategoryName()+"</td>");
       	
   
    String pattern = "yyyy-MM-dd";
	DateFormat df = new SimpleDateFormat(pattern);
	Date date1 = expense.getDate();        
	String date1string = df.format(date1);
       	out.print("<td>"+date1string+"</td>");
       	
       	
       	out.print("<td>"+expense.getPrice()+"</td>");
       	out.print("<td>"+"<a href='/app/expense/update-view?id="+expense.getId()+"'>Update</a>"+"</td>");
       	out.print("<td>"+"<a href='/app/expense/delete?id="+expense.getId()+"'>Delete</a>"+"</td>");
       out.print("</tr>");
       counter++;
       } 
       %>
   
  
  </tbody>
</table>


    
          </div>
        </div>
       
      </div>

      <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <div class="row">
         A demo project by Madhusudhan
        </div>
      </footer>
    </div>

  </body>
</html>
