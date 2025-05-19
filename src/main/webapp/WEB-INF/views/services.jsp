<!-- services.jsp -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Our Services</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <style>
        .service-box {
            border-radius: 10px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            padding: 30px;
            background-color: #f9f9f9;
        }
        .service-box:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-info">
    <a class="navbar-brand" href="/home">Online Courier Delivery System</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="/home">Home</a></li>
            <li class="nav-item active"><a class="nav-link" href="/services">Services</a></li>
            <li class="nav-item"><a class="nav-link" href="/about">About Us</a></li>
            <li class="nav-item"><a class="nav-link" href="/contact">Contact</a></li>
        </ul>
    </div>
</nav>
<section class="p-5">
    <div class="container text-center">
        <h2 class="mb-5">What We Offer</h2>
        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="service-box">
                    <h4>Standard Delivery</h4>
                    <p>Cost-effective deliveries within 24 hours.</p>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="service-box">
                    <h4>Express Delivery</h4>
                    <p>Lightning-fast delivery within a few hours, perfect for urgent needs.</p>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="service-box">
                    <h4>Scheduled Delivery</h4>
                    <p>Choose your preferred delivery time for maximum convenience.</p>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>