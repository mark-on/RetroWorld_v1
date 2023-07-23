<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/shop_style.css">
  <%@ page import="java.util.Base64" %>
	<%@ page import="entity.Product" %>
	<%@ page import="java.io.InputStream" %>
	<%@ page import="java.util.List" %>
	<%@include file="template/head.jsp" %>
	<script src="js/shop.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="js/cart.js"></script>
<title>shop</title>
</head>
<body>
	<%@include file="template/header.jsp" %>
	<div class="sort">
		 <select name="sort" id="sort" onchange="sortProducts()">
		      <option value="" disabled selected>SORT BY</option>
		      <option value="price-cre">Prezzo crescente</option>
		      <option value="price-dre">Prezzo decrescente</option>
		      <option value="">default</option>
		    </select>
	</div>

		<div class="p_container">
	 <% 
		List<Product> productList = (List<Product>) request.getAttribute("productList");
		    for (Product product : productList) {
		%>
	
	
	
		<div class = "product">
			<div class="img_container">
			<a href="product?id=<%= product.getId()%>">
				<img src="image_brand\brand_<%= product.getBrandId() %>.png" id="brand">

				<% if (product.getImage() != null) {
			        InputStream inputStream = product.getImage();
			        byte[] imageBytes = new byte[inputStream.available()];
			        inputStream.read(imageBytes);
			        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			    %>
			        <img src="data:image/jpeg;base64,<%= base64Image %>" alt="Product Image" id="img">
			    <% } %>
				</a>
			</div>
				<div class="title_container">
					<%
						String productName = product.getName(); // Assumendo che tu abbia il nome del prodotto in una variabile chiamata "product"
						int maxLength = 10; // Lunghezza massima consentita per il nome del prodotto
						
						// Controlla se la lunghezza del nome del prodotto supera la lunghezza massima
						if (productName.length() > maxLength) {
						    productName = productName.substring(0, maxLength) + "..."; // Taglia la stringa e aggiunge "..." per indicare che è stata troncata
						}
					%>
					<p id="title" style="text-transform: uppercase;"><%= productName %></p>
					<p id="price"><%= product.getPrice() %> &euro;</p>
				</div>
		</div>
		<%} %>	
	</div>

</body>
</html>