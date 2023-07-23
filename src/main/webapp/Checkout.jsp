<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ include file="template/head.jsp" %>
<%@ page import="java.util.Base64" %>
<%@ page import="entity.Product" %>
<%@ page import="entity.CartItem" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="css/style_check.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<title>CHECKOUT</title>
</head>
<body>
  <div class="model">
    <div class="room">
      <div class="text-cover">
        <h1>CART CHECKOUT</h1>

      </div>
    </div><div class="payment">
      <div class="receipt-box">
        <h3>Cart Summary</h3>
        <table class="table">
          <tr>
            <td>Total Amount</td>
            <td> <%= request.getAttribute("totalPrice") %>&euro;</td>
          </tr>
        </table>
      </div>
      <div class="payment-info">
        <h3>Payment Info</h3>
        <form action="order" method="POST">
         	<label>Address</label>
	        <input type="text" name="address" value="" required>
	        <label>Name on Credit Card</label>
	        <input type="text" name="Name" value="" required>
	        <label>Credit Card Number</label>
	        <input type="text" name="cardNumber" value="" required>
	        <label>cvc</label>
         	<input type="text" name="cvc" value="" style="width:30%;" required>
	        <br><br>
	        <input class="btn" type="submit" value="Book Securly" required>
      	</form>
      </div>
    </div>
  </div>
</body>
</html>