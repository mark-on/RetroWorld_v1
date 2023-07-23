<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="entity.Utente" %>
<!DOCTYPE html>
<html>
<head>

<%@include file="template/head.jsp" %>
<link rel="stylesheet" href="css/style_login.css">
<title>PROFILE</title>
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
    <%     Utente utente = (Utente) request.getAttribute("utente"); %>
    <form action="updateUser" method="post" >
        <h3>PROFILE</h3>
        <div class="name_c">
	        <input type="text" placeholder="First Name" id="name" name="name" value="<%= utente.getName()%>">
	        <input type="text" placeholder="Last Name" id="lastname" name="lastname" value="<%= utente.getLastname()%>">
        </div>
        <input type="text" placeholder="Username" id="username" name="username" value="<%= utente.getUsername()%>">
        <input type="email" placeholder="Email" id="mail" name="mail" value="<%= utente.getEmail()%>">
        <input type="password" placeholder="Password" id="password" name="password" value="<%= utente.getPassword()%>">

        <button>Update Profile</button>
    </form>
</body>
</html>