<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="template/head.jsp" %>
<link rel="stylesheet" href="css/style_home.css">
<script src="script_home.js"></script>	
<title>Retro World</title>
</head>
<body>

	<%@include file="template/header.jsp" %>

	<div class="header-2">
		<div class="left-h">
			<p>
				Vivi i migliori decenni della storia del <span class="font-logo">gaming</span>
			</p>
				<a href="shop"><button type="submit" class="button" name="category" value="shop">SHOP</button></a>
		</div>
		<div class="tv">
			<img src="image/home_2.gif" id="gif">
			<img src="image/home.gif" id="gif">
		</div>
	</div>

	<div class="title">
		<h1>WHAT WE DO</h1>
	</div>
	<div class="wwd">
		<p class="testo">Il nostro obiettivo è di poter fornire, a
			chiunque ne abbia bisogno, un pezzo di storia del videogioco</p>
		<img src="image\hand.png" alt="hand">
	</div>


	<div class="collection">
		<p>
			<span class="font-logo">COLLECTIONS</span>
		</p>
	</div>

	<form action="ProductsServlet" method="POST" id="form_tag">
		<input type="hidden" id="tag_selezionato" name="tag">
	</form>
	<div class="grid-container">
		<div class="item item-1" onclick="submitForm('dk')">
			<img src="image/dk.png">
		</div>
		<div class="item item-2" onclick="submitForm('spyro')">
			<img src="image/spyro.png">
		</div>
		<div class="item item-3" onclick="submitForm('pezzi_unici')">
			<img src="image/pezzi.png">
		</div>
		<div class="item item-4" onclick="submitForm('best_story')">
			<img src="image/best.png">
		</div>
		<div class="item item-5" onclick="submitForm('collector_edition')">
			<img src="image/coll.png">
		</div>
		<div class="item item-6" onclick="submitForm('young_timer')">
			<img src="image/yt.png">
		</div>
		<div class="item item-7" onclick="submitForm('mario')">
			<img src="image/mario.png">
		</div>
		<div class="item item-8" onclick="submitForm('pokemon')">
			<img src="image/pok.png">
		</div>
	</div>

	<h1 id="h1-2">MIGLIORI CONSOLE</h1>
	<div class="product-sell"></div>

</body>
</html>
