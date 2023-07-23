function getProductId(productId) {
    $.ajax({
        type: "POST",
        url: "getProductId",
        data: {
            productId: productId
        },
        success: function (response) {
            console.log("Richiesta inviata con successo");
            console.log(response);

            // Gestisci la risposta qui e fai ciò che vuoi con l'oggetto product
            var product = response; // Supponendo che la risposta sia già un oggetto JSON rappresentante il prodotto
            // Fai qualcosa con l'oggetto product, ad esempio:
            // displayProductDetails(product);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Errore nella richiesta AJAX: " + textStatus + " - " + errorThrown);
        }
    });
}