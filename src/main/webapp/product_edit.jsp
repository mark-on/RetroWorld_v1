<%@ page import="java.util.Base64" %>
<%@ page import="entity.Product" %>
<%@ page import="java.io.InputStream" %>


<html>
<head>
	<%@include file="template/head.jsp" %>
	<link rel="stylesheet" href="css/style_productadmin.css">
    <title>Product Details</title>
</head>
<body>
	<%@include file="template/header.jsp" %>
    <% 
    	Product product = (Product) request.getAttribute("product");
	    session.setAttribute("idParameter", product.getId()); 
     %>
    <script src="js/add-product.js"></script>

    <h1>Edit <%= product.getName()%></h1>
    <div class="main">
 			<% if (product.getImage() != null) {
		        InputStream inputStream = product.getImage();
		        byte[] imageBytes = new byte[inputStream.available()];
		        inputStream.read(imageBytes);
		        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		    %>
		    <div id="image_p">
		        <img src="data:image/jpeg;base64,<%= base64Image %>" alt="Product Image" class="img">
		    </div>
		    <% } %>
		    <form action="editProduct" method="post" enctype="multipart/form-data" class="info">
		        <label for="name">Name:</label>
		        <input type="text" name="name" id="name" value="<%= product.getName()%>"><br>
		        
		        <label for="price">Price:</label>
		        <input type="number" name="price" id="price" step="0.01" value="<%= product.getPrice()%>"><br>
		        
		        <label for="description">Description:</label>
		        <input type="text" name="description" id="description" value="<%= product.getDescription()%>"><br>
		        
		        <label for="brand">Brand:</label>
		        <select name="brand" id="brand"  onchange="changeParameter(this.value)" required>
		            <option value="" disabled selected>Select brand</option>
		            <option value="nintendo">Nintendo</option>
		            <option value="sony">Sony</option>
		            <option value="sega">Sega</option>
		            <option value="microsoft">Microsoft</option>
		        </select><br>
		        
		        <label for="console">Console:</label>
		        <select name="console" id="console" required>
		            <option value="" disabled selected>Select console</option>
		        </select><br>
		        <label for="type">Type:</label>
		        <select name="type" id="type">
		            <option value="console">Console</option>
		            <option value="game">Game</option>
		            <option value="accessory">Accessory</option>
		        </select><br>
		        <label for="amount">Amount:</label>
		        <input type="number" name="amount" id="amount" value="<%= product.getAmount()%>"><br>
		        
		        <label for="tag">Tag:</label>
		        <input type="text" name="tag" id="tag" value="<%= product.getTag()%>"><br>
		        <input type="file" name="image" id="image">

		        <input type="submit">
		        
		    </form>
    </div>
</body>
</html>