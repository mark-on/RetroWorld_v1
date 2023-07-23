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
		<a href="home"><img class="logo" src="image/logo.svg"></a>
	</div>
    <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
    </div>
    <form>
        <h3>Email esistente</h3>
        <div class="gif">
        	<img src="image/mario_sad.gif">
        </div>
        <div class="social">
          <div class="go"><a href="forget_pass.jsp" id="re">Forget password</a></div>
          <div class="fb"><a href="login.jsp" id="re">Login</a></div>
        </div>
    </form>
</body>
</html>