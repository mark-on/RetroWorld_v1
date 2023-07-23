<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<%@include file="template/head.jsp" %>
<link rel="stylesheet" href="css/style_login.css">
<title>LOGIN</title>
 <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">

</head>
<body>
	<div class="logo">
		<a href="home.jsp"><img class="logo" src="image/logo.svg"></a>
	</div>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
   <form action="logged" method="post">
   <div class="heading">
        <h3>Login Here</h3>
        <div>
		  <% if (request.getAttribute("errorMessage") != null) { %>
		    <p><%= request.getAttribute("errorMessage") %></p>
		  <% } %>
		</div>
	</div>
        <label for="username">Username</label>
        <input type="text" placeholder="Username" id="username" name="username" required>

        <label for="password">Password</label>
        <input type="password" placeholder="Password" id="password" name="password" required>

        <button>LOG IN</button>
        <div class="social">
          <div class="go"><a href="forget_pass.jsp" id="re">Forget password</a></div>
          <div class="fb"><a href="registration.jsp" id="re">Registration</a></div>
        </div>
    </form>
</body>
</html>