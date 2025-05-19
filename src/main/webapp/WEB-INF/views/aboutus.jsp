<!-- aboutus.jsp -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About Us</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <style>
        .about-section {
            background: linear-gradient(to right, #9ce3f0, #024044);
            color: white;
            padding: 50px;
            border-radius: 8px;
        }
        .about-section h2 {
            font-weight: 700;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-info">
    <a class="navbar-brand" href="/home">Online Courier Delivery System</a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="/home">Home</a></li>
            <li class="nav-item"><a class="nav-link" href="/services">Services</a></li>
            <li class="nav-item active"><a class="nav-link" href="/about">About Us</a></li>
            <li class="nav-item"><a class="nav-link" href="/contact">Contact</a></li>
        </ul>
    </div>
</nav>
<section class="about-section text-center">
    <div class="container">
        <h2 class="mb-4">Who We Are</h2>
        <p class="lead">At Wefast, we believe in making deliveries simple, fast, and reliable. We are not just a courier service; we are your logistics partner in growth and convenience.</p>
        <p>Founded in 2025, our mission is to revolutionize last-mile delivery using technology and a human touch. From individuals to businesses, we deliver with speed and care.</p>
        <div class="mt-4">
            <img src="/images/team.jpg" alt="Our Team" class="img-fluid rounded shadow">
        </div>
    </div>
</section>
</body>
</html>