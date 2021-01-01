import com.google.common.collect.Lists;
import persistence.Connector;
import product.Product;
import product.ProductPersistenceHandler;

import java.util.List;

/**
 * To get products in categories sofa or chair and brand Fab
 * Created by navdeepsingh on 24/07/16.
 * You can test this example by running the main() method
 */
public class GetProductsMain {

    public static void main(String[] args){

        getProducts();
        // to close the opened session
        Connector.close();
        System.out.println("CLosing the session");

    }

    private static  void getProducts(){
        List<String> categories = Lists.newArrayList();
        categories.add("sofa");
        categories.add("chair");

        String brand = "Fab";

        ProductPersistenceHandler handler = new ProductPersistenceHandler();
        List<Product> products = handler.getProductsFor(categories, brand);

        System.out.println(products);
    }

}
