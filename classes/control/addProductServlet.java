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
import javax.servlet.http.Part;

import Dao.BrandDAO;
import Dao.ConsoleDAO;
import Dao.ProductDAO;
import Database.Database;
import entity.Brand;
import entity.Console;
import entity.Product;

@SuppressWarnings("serial")
@WebServlet("/addProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class addProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    private ConsoleDAO consoleDAO;
    private BrandDAO brandDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        Database connection = new Database();

        productDAO = new ProductDAO(connection);
        brandDAO = new BrandDAO(connection);
        consoleDAO = new ConsoleDAO(connection);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni i valori dei parametri dalla richiesta
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String brandName = request.getParameter("brand");
        String consoleName = request.getParameter("console");
        String type = request.getParameter("type");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String tag = request.getParameter("tag");

        Part filePart = request.getPart("image");
        InputStream inputStream = filePart.getInputStream();

       
        // Imposta il percorso del file nell'oggetto Product
        //String image = filePath;

        // Ottenere gli oggetti Brand e Console in base ai nomi selezionati
        Brand selectedBrand;
        Console selectedConsole;

        try {
            selectedBrand = brandDAO.findByName(brandName);
            selectedConsole = consoleDAO.findByName(consoleName);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione o reindirizza a una pagina di errore
            response.sendRedirect("error.jsp");
            return;
        }

        // Creare un oggetto Product con i valori ottenuti
        int brandId = selectedBrand.getId();
        int consoleId = selectedConsole.getId();
        Product product = new Product(-1, name, price, description, brandId, consoleId, amount, tag, type, inputStream);

        // Effettua l'elaborazione per l'aggiunta del prodotto utilizzando il DAO
        try {
            productDAO.addProduct(product);
            // Reindirizza a una pagina di conferma o altro
            response.sendRedirect("add-product.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestisci l'eccezione o reindirizza a una pagina di errore
            response.sendRedirect("error_e.jsp");
        }
    }

}
