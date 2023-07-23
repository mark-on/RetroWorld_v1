<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="entity.Order" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Orders</title>
    <%@ include file="template/head.jsp" %>
    <link rel="stylesheet" href="css/style_allorder.css">
</head>
<body>
    <%@ include file="template/header.jsp" %>

    <div class="page">
        <div class="top">
            <h2>I tuoi ordini</h2>
        </div>
        <div class="order-container">
            <% List<Order> userOrders = (List<Order>) request.getAttribute("userOrders"); %>
            <% if (userOrders != null && !userOrders.isEmpty()) { %>
                <table>
                    <tr>
                        <th>Order ID</th>
                        <th>Date</th>
                        <th>Total Amount</th>
                        <th>Address</th>
                        <th>Name on Card</th>
                        <th>Card Number</th>
                        <th>CVC</th>
                    </tr>
                    <% for (Order order : userOrders) { %>
                        <tr>
                            <td><%= order.getId() %></td>
                            <td><%= order.getDate() %></td>
                            <td><%= order.getTotal() %></td>
                            <td><%= order.getAddress() %></td>
                            <td><%= order.getName() %></td>
                            <td><%= order.getCard() %></td>
                            <td><%= order.getCvc() %></td>
                        </tr>
                        <hr>
                    <% } %>
                </table>
            <% } else { %>
                <p>No orders found.</p>
            <% } %>
        </div>
    </div>
</body>
</html>
