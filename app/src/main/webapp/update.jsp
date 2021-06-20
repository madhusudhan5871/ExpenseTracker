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


    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
      <h1 class="display-4">Expense Tracker</h1>
      <p class="lead">Be in charge of your finances</p>
    </div>

    <div class="container">
      <div class="card-deck mb-3 ">
        <div class="card mb-6 box-shadow">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal">Update expense</h4>
          </div>
          <div class="card-body">

<form action="update" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">What's the expense?</label>
    <% Expense expense = (Expense)request.getAttribute("expense"); 
    String pattern = "yyyy-MM-dd";
	DateFormat df = new SimpleDateFormat(pattern);
	Date date1 = expense.getDate();        
	String date1string = df.format(date1);%>
    <input type="text" value='<%=expense.getName()%>' name = "name" class="form-control" id="exampleInputEmail1" placeholder="What's the expense">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">What's the Price?</label>
    <input type="number" value='<%=expense.getPrice()%>' name="price" class="form-control" id="exampleInputPassword1" placeholder="What's the price?">
  </div>
  <input type="hidden" name = "id" value='<%=expense.getId()%>'>
 
     <div class="form-group">
    <label for="exampleInputPasswor32">Date</label>
    <input type="date" value='<%=date1string%>' name="date" class="form-control" id="exampleInputPassword32" placeholder="Date">
  </div>

  <button type="submit" class="btn btn-lg btn-block btn-outline-primary">Submit</button>
</form>


    
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
