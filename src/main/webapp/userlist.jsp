<!DOCTYPE html>
<html>
<head>
    <title>Lista Utenti</title>
    <%@ page import="entity.Utente" %>
    <%@ page import="java.util.List" %>
    <%@include file="template/head.jsp" %>
    <style>
        td {
            text-align: center;
        }
    </style>
</head>
<body>
<%@include file="template/header.jsp" %>

<h1>Lista Utenti</h1>
<table id="userTable" style="width:100%; padding:20px;">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Password</th>
        <th> </th>
    </tr>
    <% 
    List<Utente> userList = (List<Utente>) request.getAttribute("userList");
    for (Utente utente : userList) {
    %>
    <tbody>
        <tr>
            <td><%= utente.getId() %></td>
            <td><%= utente.getUsername() %></td>
            <td><%= utente.getName() %></td>
            <td><%= utente.getLastname() %></td>
            <td><%= utente.getEmail() %></td>
            <td><%= utente.getPassword() %></td>
            <td>
            	<form action="deleteUser" method="post">
            		<input type="submit" value="delete">
            		<input type="hidden" value="<%= utente.getId() %>" name="id">
            	</form>
            	
            <!-- Altre colonne degli utenti -->
        </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>
