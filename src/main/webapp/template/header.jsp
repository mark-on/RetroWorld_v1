<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="entity.Utente" %>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="css/style_header.css">
<link href='https://fonts.googleapis.com/css?family=Oswald'
	rel='stylesheet'>
		
<link href="https://fonts.cdnfonts.com/css/futura-pt" rel="stylesheet">
<link href='https://fonts.google.com/specimen/Roboto?query=roboto'
	rel='stylesheet'>
</head>
<body>
	<header>
	<%
		Boolean isLogged = (Boolean) session.getAttribute("isLogged");
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		%>
	<div class="utility-bar">
	<%
	if (isLogged != null && isLogged.booleanValue() && isAdmin != null && isLogged.booleanValue()) {
		if (isAdmin == true) {
		%>
			<div class="container">
				<p class="utility-bar__text">FREE SHIPPING SU ORDINI SUPERIORI A 10 &euro;</p>
				<div class="utility-bar__login">
					<nav>
					<ul>
						<li class="dropdown" id="shop"><p class="profile" style= "font-size:15px; margin-right:10px;">Admin</p>
							<ul class="dropdown-menu">
								<li><a href="profile"  style= "font-size:15px;">My Profile</a></li>
								<li><a href="productlist_admin"  style= "font-size:15px;">Products</a></li>
								<li><a href="alluser"  style= "font-size:15px;">Users</a></li>
								<li><a href="allOrders"  style= "font-size:15px;">Orders</a></li>
								<li><a href="logout"  style= "font-size:15px;">Log Out</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
			</div>
		<%
		} else if(isAdmin == false){
		%>
			<div class="container">
				<p class="utility-bar__text">FREE SHIPPING SU ORDINI SUPERIORI A 10 &euro;</p>
				<div class="utility-bar__login">
					<nav>
					<ul>
						<li class="dropdown" id="profile">
								<div class="p_pic">
									<a href="#"  style= "font-size:15px;"> 
										<img src="image/face.png" id="pic"> 
									</a>
								</div>
							<ul class="dropdown-menu">
								<li><a href="profile" style= "font-size:15px;" >My Profile</a></li>
								<li><a href="allOrders" style="font-size:15px;">Orders</a></li>
								<li><a href="logout" style="font-size:15px;">Log Out</a></li>
							</ul>
						</li>
					</ul>
				</nav>
				</div>
			</div>

		<%
		}
		%>
	
<%
} else {
%>
	<div class="container">
		<p class="utility-bar__text">FREE SHIPPING SU ORDINI SUPERIORI A 10 &euro;</p>
		<div class="utility-bar__login">
			<a href="login.jsp" class="login">ACCEDI</a> <span class="separator">|</span>
			<a href="registration.jsp" class="login">REGISTRATI</a>
		</div>
	</div>
<%
}
%>
</div>
		<div class="header-box">

			<div class="left">
				<a href="home.jsp"><img class="logo" src="image/logo.svg"></a>
			</div>

			<div class="center">
			
				<nav id="nav">
					<ul>
						<li class="dropdown" id="shop"><a href="shop" class="shop">Shop</a>
							<ul class="dropdown-menu">
								<li><a href="shop?type=console">Consoles</a></li>
								<li><a href="shop?type=game">Games</a></li>
								<li><a href="shop?type=game">Accessori</a></li>
							</ul></li>
						<li class="dropdown"><a href="shop?brandId=1">Nintendo</a>
							<ul class="dropdown-menu">
								<li><a href="shop?brandId=1&consoleId=3&type=console">NES</a></li>
								<li><a href="shop?consoleId=2">SNES Lite</a></li>
								<li><a href="shop?consoleId=1">N64</a></li>
								<li><a href="shop?consoleId=4">GAMECUBE</a></li>
								<li><a href="shop?consoleId=5">GAMEBOY</a></li>
							</ul></li>
						<li class="dropdown"><a href="shop?brandId=4">Sony</a>
							<ul class="dropdown-menu">
								<li><a href="shop?consoleId=6">PlayStation 1</a></li>
								<li><a href="shop?consoleId=7">PlayStation 2</a></li>
								<li><a href="shop?consoleId=8">PSP</a></li>
							</ul></li>
						<li class="dropdown"><a href="shop?brandId=3">Microsoft</a>
							<ul class="dropdown-menu">
								<li><a href="shop?consoleId=11">Xbox</a></li>	
								</ul></li>
						<li class="dropdown"><a href="shop?brandId=2">Sega</a>
							<ul class="dropdown-menu">
								<li><a href="shop?consoleId=9">Dreamcast</a></li>
								<li><a href="shop?consoleId=10">Megadrive</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
			
			<form action="shop" method="get">
				<div class="right">
					<input type="text" placeholder="Cerca..." name="search"><a href="cart.jsp"><img src="image\cart.png" alt="cart" id="cart-icon"></a>
					<input type="hidden" name="isSearch" value="1">
				</div>
			</form>
		</div>
		<div class="menu_2">
			<nav>
					<ul>
						<li class="dropdown" id="shop"><a href="shop" class="shop">Shop</a>
							<ul class="dropdown-menu">
								<li><a href="shop?type=console">Consoles</a></li>
								<li><a href="shop?type=game">Games</a></li>
								<li><a href="shop?type=game">Accessori</a></li>
							</ul></li>
						<li class="dropdown"><a href="shop?brandId=1">Nintendo</a>
							<ul class="dropdown-menu">
								<li><a href="shop?brandId=1&consoleId=3&type=console">NES</a></li>
								<li><a href="shop?consoleId=2">SNES Lite</a></li>
								<li><a href="shop?consoleId=1">N64</a></li>
								<li><a href="shop?consoleId=4">GAMECUBE</a></li>
								<li><a href="shop?consoleId=5">GAMEBOY</a></li>
							</ul></li>
						<li class="dropdown"><a href="shop?brandId=4">Sony</a>
							<ul class="dropdown-menu">
								<li><a href="shop?consoleId=6">PlayStation 1</a></li>
								<li><a href="shop?consoleId=7">PlayStation 2</a></li>
								<li><a href="shop?consoleId=8">PSP</a></li>
							</ul></li>
						<li class="dropdown"><a href="shop?brandId=3">Microsoft</a>
							<ul class="dropdown-menu">
								<li><a href="shop?consoleId=11">Xbox</a></li>	
								</ul></li>
						<li class="dropdown"><a href="shop?brandId=2">Sega</a>
							<ul class="dropdown-menu">
								<li><a href="shop?consoleId=9">Dreamcast</a></li>
								<li><a href="shop?consoleId=10">Megadrive</a></li>
							</ul></li>
					</ul>
				</nav>
		</div>
	</header>
</body>
</html>