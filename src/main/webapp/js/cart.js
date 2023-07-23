function addToCart() {
    var quantity = $("#quantity").val();
    var productId = $("#productId").val();

    $.ajax({
        type: "POST",
        url: "addToCart",
        data: {
            productId: productId,
            quantity: quantity
        },
        success: function (response) {
        alert("Prodotto aggiunto al carrello con successo.");
            console.log("Richiesta inviata con successo");
            console.log(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Errore nella richiesta AJAX: " + textStatus + " - " + errorThrown);
        }
    });
}

