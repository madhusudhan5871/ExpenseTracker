<%@page import ="java.util.List"%>
<%@page import="com.expensetracker.db.Category"%>
<%@page import="java.util.ArrayList"%>
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
            <h4 class="my-0 font-weight-normal">Add an expense</h4>
          </div>
          <div class="card-body">

<form action="add" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">What's the expense?</label>
    <input type="text" name = "name" required class="form-control" id="exampleInputEmail1" placeholder="What's the expense">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">What's the Price?</label>
    <input type="number" name="price" required class="form-control" id="exampleInputPassword1" placeholder="What's the price?">
  </div>
   <div class="form-group">
    <label for="exampleFormControlSelect1">Add to an existing category</label>
    <select class="form-control" id="exampleFormControlSelect1" name=category>
      <% 
      List<Category> categoryList = (ArrayList<Category>)request.getAttribute("categoryList");
       for(Category category:categoryList){
       	out.print("<option>"+category.getCategoryName()+"</option>");
       } 
       %>
    </select>
  </div>
   <div class="form-group">
    <label for="exampleInputPassword2">If you want this to classify under a new category, Please enter it below, otherwise leave blank</label>
    <input type="text" name="newCategory" class="form-control" id="exampleInputPassword2" placeholder="Category">
  </div>
     <div class="form-group">
    <label for="exampleInputPasswor32">Date</label>
    <input type="date" name="date" required class="form-control" id="exampleInputPassword32" placeholder="Date">
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
