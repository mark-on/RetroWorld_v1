package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Dao.BrandDAO;
import Dao.ConsoleDAO;
import Dao.ProductDAO;
import Database.Database;
import entity.Brand;
import entity.Console;
import entity.Product;

@WebServlet("/editProduct")
@MultipartConfig
public class EditProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private ConsoleDAO consoleDAO;
    private BrandDAO brandDAO;

    public void init() throws ServletException {
        super.init();
        Database connection = new Database();

        productDAO = new ProductDAO(connection);
        brandDAO = new BrandDAO(connection);
        consoleDAO = new ConsoleDAO(connection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	  HttpSession session = request.getSession();
    	  Product product = (Product) session.getAttribute("product");
          int id = (int) session.getAttribute("idParameter");
          

          String name = request.getParameter("name");
          if (name == null || name.trim().isEmpty()) {
              name = product.getName();
          }

          String priceString = request.getParameter("price");
          Double price = product.getPrice();
          if (priceString != null && !priceString.trim().isEmpty()) {
              try {
                  price = Double.parseDouble(priceString);
              } catch (NumberFormatException e) {
                  // Il valore immesso non è un numero, mantieni il valore precedente
              }
          }

          String description = request.getParameter("description");
          if (description == null || description.trim().isEmpty()) {
              description = product.getDescription();
          }

          String brandName = request.getParameter("brand");
          String consoleName = request.getParameter("console");
          Brand selectedBrand;
          Console selectedConsole;
          int brandId;
          int consoleId;
          try {
              selectedBrand = brandDAO.findByName(brandName);
              selectedConsole = consoleDAO.findByName(consoleName);
              brandId = selectedBrand.getId();
              consoleId = selectedConsole.getId();
          } catch (SQLException e) {
              e.printStackTrace();
              response.sendRedirect("error.jsp");
              return;
          }

          String type = request.getParameter("type");
          if (type == null || type.trim().isEmpty()) {
              type = product.getType();
          }

          String amountString = request.getParameter("amount");
          Integer amount = product.getAmount();
          if (amountString != null && !amountString.trim().isEmpty()) {
              try {
                  amount = Integer.parseInt(amountString);
              } catch (NumberFormatException e) {
                  // Il valore immesso non è un numero, mantieni il valore precedente
              }
          }

          String tag = request.getParameter("tag");
          if (tag == null || tag.trim().isEmpty()) {
              tag = product.getTag();
          }

          Part filePart = request.getPart("image");
          InputStream inputStream = null;
          if (filePart != null && filePart.getSize() > 0) {
              // L'immagine è stata selezionata, leggi il nuovo InputStream dell'immagine
              inputStream = filePart.getInputStream();
          } else {
              // L'immagine non è stata selezionata, mantieni l'immagine originale
              inputStream = product.getImage();
          }

          Product updatedProduct = new Product(id, name, price, description, brandId, consoleId, amount, tag, type, inputStream);
          session.removeAttribute("idParameter");

          try {
              productDAO.updateProduct(updatedProduct);
              session.removeAttribute("product");
              response.sendRedirect("productlist_admin");
          } catch (SQLException e) {
              e.printStackTrace();
              session.removeAttribute("product");
              response.sendRedirect("error_e.jsp");
          }
    }
}
