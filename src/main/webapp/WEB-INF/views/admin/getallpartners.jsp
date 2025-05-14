
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
  <!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
        integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <title>Home</title>
</head>

<body>

        <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-info">
        <a class="navbar-brand" href="#">W E F A S T</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              
                <li class="nav-item ">
                    <a class="nav-link" href="/admin/allorders">All Orders </a>
                </li>
                
     

                <li class="nav-item ">
                    <a class="nav-link" href="/admin/allcustomer">All Customers </a>
                </li>
                
                <li class="nav-item ">
                    <a class="nav-link" href="/admin/allpartners">All Delivery Partners </a>
                </li>
                
                
                
                
                
                
                
            </ul>
          

            <ul class="navbar-nav mr-right px-4">
                <li class="nav-item">
                    <a class="nav-link " href="#" > ${sessionScope.admin_details.getAemail()}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link "  href="/customer/logout">Log Out</a>
                </li>

            </ul>

        </div>
    </nav>

    
    <!-- get all customer -->

  
    <div class="col-lg-9 m-auto d-block"><br>
        <div class="card-header bg-info">
            <h1 class="text-white text-center">All Delivery Partners</h1>
        </div>


        <div id="items" class="my-4">


            <table class="table">
            
            <thead>
    			<tr>
      				<th scope="col">  ID</th>
      				<th scope="col"> Name</th>
      				<th scope="col"> Email</th>
      				<th scope="col"> Mobile No</th>
      				<th scope="col"> Area</th>
      				<th scope="col"> License No</th>
      				<th scope="col"> Earning</th>
      				<th scope="col"> Delete</th>
    			</tr>
  			</thead>
                <tbody id="tableBody">
                
                <c:forEach var="d" items="${requestScope.all_partners}">
                
                    <tr>
                       
                        <td>${d.did}</td>
                    
                        
                        <td>${d.dname}</td>
                   
                        
                        <td>${d.demail}</td>
                  
                  
                        <td>${d.dmobileno}</td>
                   
                   
                        <td>${d.area}</td>
                        
                        <td>${d.licenceno}</td>
                        
                        <td>${d.earning}</td>
                        
                        <td><a class="btn btn-danger" href="/admin/deletepartner/${d.did}" role="button">Delete</a></td>
                    
                    </tr>
					
                </c:forEach> 

                </tbody>	
            </table>
            
          
        </div>

    </div>
   
    

    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>

    <!-- Option 2: jQuery, Popper.js, and Bootstrap JS
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    -->
</body>

</html>
