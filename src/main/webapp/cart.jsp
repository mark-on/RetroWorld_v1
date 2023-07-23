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
<link rel="stylesheet" href="css/style_cart.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<title>Cart</title>
</head>
<body>
    <%@ include file="template/header.jsp" %>
    
    <div class="page">
        <div class="top">
            <p>Shopping Cart</p>
            <a href="RemoveCart">Remove All</a>
        </div>
        <div class="cart_container">
            <%-- Recupera la lista dei cartItem dalla sessione --%>
            <% List<CartItem> cartList = (List<CartItem>) session.getAttribute("cartList");

            double totalPrice = 0.0;
            int cnt = 0;%>
            <% if (cartList != null && !cartList.isEmpty()) { %>
                <% 
                
                for (CartItem cartItem : cartList) { 
                	cnt = cnt+1;
                	 Product product = cartItem.getproduct();
                	 int quantity = cartItem.getQuantity();
                     double productPrice = product.getPrice();
                     double totalProductPrice = productPrice * quantity;
                     totalPrice = totalProductPrice + totalPrice;
                %>
                    <div class="product_row">
                    <%
						// Converte l'input stream dell'immagine in un formato visualizzabile come Base64
						String base64Image = "";
						if (product.getImage() != null) {
						    InputStream inputStream = product.getImage();
						    byte[] imageBytes = new byte[inputStream.available()];
						    inputStream.read(imageBytes);
						    base64Image = Base64.getEncoder().encodeToString(imageBytes);
						}
						%>
                        <div class="product_img">
                         <% if (!base64Image.isEmpty()) { %>
						        <img src="data:image/jpeg;base64,<%= base64Image %>">
						    <% } %>
                        </div>
                        <div class="product_info">
                            <p><%= product.getName() %></p> <%-- Aggiungi il nome del prodotto recuperato dalla chiamata Ajax --%>
                        </div>
                        <div class="product_quantity">
                            <p><%=cartItem.getQuantity() %></p>
                        </div>
                        <div class="product_price">
                            <p><%=product.getPrice() %>&euro;</p> <%-- Aggiungi il prezzo del prodotto recuperato dalla chiamata Ajax --%>
                        </div>
                        <div class="remove">
                            <a href="RemoveCart?productId=<%=product.getId() %>"><img src="image/trash.png" id="trash" style="width:20px;"></a>
                        </div>
                    </div>
                <% } %>
            <% } else { %>
                <p>No items in cart</p>
            <% } %>
        </div>
        <hr>
        <div class="down">
            <div class="down_1">
                <p>SUBTOTAL</p>
                <p id="item"><%= cnt %> item</p>
            </div>
            <div class="down_2">
                <p><%= totalPrice %></p>
            </div>
        </div>
        
        <% if (isLogged != null && isLogged.booleanValue() && isAdmin != null && isLogged.booleanValue()){ %>
        <div class="checkout">
            <a href="checkout"><button id="button">CHECKOUT</button></a>
             </div>
         <%}else{ %>
          <div class="checkout">
            <a href="login.jsp"><button id="button">CHECKOUT</button></a>
             </div>
          <%} %>
       

    </div>
</body>
<script>
    $(document).ready(function() {
        // Carica l'immagine dai dati salvati nell'attributo data-image
        $('.product_img img').each(function() {
            var base64Image = $(this).data('image');
            if (base64Image) {
                $(this).attr('src', 'data:image/jpeg;base64,' + base64Image);
            }
        });
    });
</script>
</html>
