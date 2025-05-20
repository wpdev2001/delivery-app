<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container mt-5">
    <div class="col-lg-8 m-auto d-block">
        <div class="card-header bg-info">
            <h1 class="text-white text-center">Admin Login</h1>
        </div>

        <form method="post" class="bg-light p-4 rounded">

            <div class="form-group">
                <label>Email:</label>
                <input class="form-control" id="email" type="text" name="email" />
                <span id="emailid" class="text-danger font-weight-bold"></span>
            </div>

            <div class="form-group">
                <label>Password:</label>
                <input class="form-control" id="pass" type="password" name="pass">
                <span id="Password" class="text-danger font-weight-bold"></span>
            </div>

            <div class="form-group text-right">
                <a href="/admin/forgotpassword" class="text-info">Forgot Password?</a>
            </div>

            <!-- Display messages -->
            <c:if test="${not empty success}">
                <div class="alert alert-success text-center">${success}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger text-center">${error}</div>
            </c:if>

            <input class="btn btn-success" type="submit" name="submit" value="Login">
        </form>
    </div>
</div>
</body>
</html>
