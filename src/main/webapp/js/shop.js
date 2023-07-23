function sortProducts() {
  var sort = document.getElementById("sort").value;
  var urlParams = new URLSearchParams(window.location.search);
  if (urlParams.has("sort")) {
    urlParams.delete("sort");
  }
  if (sort) {
    urlParams.append("sort", sort);
  }
  var url = window.location.pathname + "?" + urlParams.toString();
  window.location.href = url;
}