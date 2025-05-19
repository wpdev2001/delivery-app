 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
  
</head>
<body>

 <div class="container"> <br>         
     <div class="col-lg-8 m-auto d-block"><br>
         <div class="card-header bg-info">
             <h1 class="text-white text-center">Customer Login</h1>
         </div>

         <form method="post" action="/customer/login" class="bg-light">
             <div class="form-group">
                 <label>Email:</label>
                 <input class="form-control" id="email" type="text" name="email"/>
                 <span id="emailid" class="text-danger font-weight-bold"></span>
             </div>

             <div class="form-group position-relative">
                 <label>Password:</label>
                 <div class="input-group">
                     <input class="form-control" id="pass" type="password" name="pass">
                     <div class="input-group-append">
                         <button class="btn btn-outline-secondary" type="button" id="togglePassword">
                             <i class="bi bi-eye" id="eyeIcon"></i>
                         </button>
                     </div>
                 </div>
                 <span id="Password" class="text-danger font-weight-bold"></span>
             </div>

             <div class="form-group d-flex justify-content-between">
                 <a href="/customer/forgotpassword" class="text-info">Forgot Password?</a>
                 <a href="<spring:url value='/customer/reg'/>" class="text-info">Create New Account</a>
             </div>

             <div class="form-group">
                 <span class="text-success font-weight-bold">${requestScope.success}</span>
                 <span class="text-danger font-weight-bold">${requestScope.error}</span>
             </div>

             <input class="btn btn-success" type="submit" name="submit" value="Submit">
         </form>
     </div>
 </div>

<script>
    document.getElementById("togglePassword").addEventListener("click", function () {
        const passField = document.getElementById("pass");
        const eyeIcon = document.getElementById("eyeIcon");
        const type = passField.getAttribute("type") === "password" ? "text" : "password";
        passField.setAttribute("type", type);
        eyeIcon.classList.toggle("bi-eye");
        eyeIcon.classList.toggle("bi-eye-slash");
    });
</script>



</body>
</html>