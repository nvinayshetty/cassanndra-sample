import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import persistence.Connector;
import product.Product;
import product.ProductPersistenceHandler;

import java.util.List;
import java.util.Map;

import static common.dto.AttributeNames.*;
import static common.dto.AttributeNames.LENGTH;
import static common.dto.AttributeNames.TITLE;

/**
 * You can run the main() method from IDE
 * Creates rows for product - Used in slide example
 * Created by navdeepsingh on 24/07/16.
 * You can test this example by running the main() method
 */
public class AddProductsMain {

    public static void main(String[] args){

        addData();
        // to close the opened session
        Connector.close();
        System.out.println("CLosing the session");

    }

    private static void addData(){
        ProductPersistenceHandler persistenceHandler = new ProductPersistenceHandler();
        Product product1 = new Product();
        Map<String, Object> attributesMaps1 = Maps.newHashMap();
        attributesMaps1.put(CATEGORY.getValue(), "sofa");
        attributesMaps1.put(BRAND.getValue(), "Fab");
        attributesMaps1.put(BREADTH.getValue(), 100);
        attributesMaps1.put(HEIGHT.getValue(), 200);
        attributesMaps1.put(LENGTH.getValue(), 500);
        attributesMaps1.put(TITLE.getValue(), "Urban Living Derby");
        product1.setProductId("SOFA1");
        product1.setAttributesMap(attributesMaps1);
        persistenceHandler.insertProducts(product1);
        Connector.close();


        Product product2 = new Product();
        Map<String, Object> attributesMaps2 = Maps.newHashMap();
        attributesMaps2.put(CATEGORY.getValue(), "sofa");
        attributesMaps2.put(BRAND.getValue(), "Decor");
        attributesMaps2.put(BREADTH.getValue(), 100);
        attributesMaps2.put(HEIGHT.getValue(), 200);
        attributesMaps2.put(LENGTH.getValue(), 700);
        attributesMaps2.put(TITLE.getValue(), "Urban 5 seater");
        product2.setProductId("SOFA10");
        product2.setAttributesMap(attributesMaps2);
        persistenceHandler.insertProducts(product2);
        Connector.close();


        Product product3 = new Product();
        Map<String, Object> attributesMaps3 = Maps.newHashMap();
        attributesMaps3.put(CATEGORY.getValue(), "sofa");
        attributesMaps3.put(BRAND.getValue(), "Decor");
        attributesMaps3.put(BREADTH.getValue(), 100);
        attributesMaps3.put(HEIGHT.getValue(), 200);
        attributesMaps3.put(LENGTH.getValue(), 500);
        attributesMaps3.put(TITLE.getValue(), "Urban 4 seater");
        product3.setProductId("SOFA9");
        product3.setAttributesMap(attributesMaps3);
        persistenceHandler.insertProducts(product3);
        Connector.close();


        Product product4 = new Product();
        Map<String, Object> attributesMaps4 = Maps.newHashMap();
        attributesMaps4.put(CATEGORY.getValue(), "sofa");
        attributesMaps4.put(BRAND.getValue(), "Fab");
        List<String> featureValues = Lists.newArrayList();
        featureValues.add("Good Design");
        featureValues.add("Elegant");
        attributesMaps4.put(KEYFEATURES.getValue(), featureValues);
        product4.setProductId("SOFA10");
        product4.setAttributesMap(attributesMaps4);
        persistenceHandler.insertProducts(product4);
        Connector.close();


        Product product5 = new Product();
        Map<String, Object> attributesMaps5 = Maps.newHashMap();
        attributesMaps5.put(CATEGORY.getValue(), "sofa");
        attributesMaps5.put(BRAND.getValue(), "Fab");
        attributesMaps5.put(BREADTH.getValue(), 100);
        attributesMaps5.put(HEIGHT.getValue(), 200);
        attributesMaps5.put(LENGTH.getValue(), 700);
        attributesMaps5.put(TITLE.getValue(), "Urban Decor 2 seater");
        product5.setProductId("SOFA2");
        product5.setAttributesMap(attributesMaps5);
        persistenceHandler.insertProducts(product5);
        Connector.close();


        Product product6 = new Product();
        Map<String, Object> attributesMaps6 = Maps.newHashMap();
        attributesMaps6.put(CATEGORY.getValue(), "sofa");
        attributesMaps6.put(BRAND.getValue(), "Fab");
        attributesMaps6.put(BREADTH.getValue(), 100);
        attributesMaps6.put(HEIGHT.getValue(), 200);
        attributesMaps6.put(LENGTH.getValue(), 500);
        attributesMaps6.put(TITLE.getValue(), "Urban Living Sofa 3 Seater");
        product6.setProductId("SOFA5");
        product6.setAttributesMap(attributesMaps6);
        persistenceHandler.insertProducts(product6);
        Connector.close();


        Product product7 = new Product();
        Map<String, Object> attributesMaps7 = Maps.newHashMap();
        attributesMaps7.put(CATEGORY.getValue(), "top");
        attributesMaps7.put(BRAND.getValue(), "Shine");
        attributesMaps7.put(BREADTH.getValue(), 100);
        attributesMaps7.put(HEIGHT.getValue(), 300);
        attributesMaps7.put(LENGTH.getValue(), 100);
        attributesMaps7.put(TITLE.getValue(), "Marble Top");
        product7.setProductId("TOP1");
        product7.setAttributesMap(attributesMaps7);
        persistenceHandler.insertProducts(product7);
        Connector.close();


        Product product8 = new Product();
        Map<String, Object> attributesMaps8 = Maps.newHashMap();
        attributesMaps8.put(CATEGORY.getValue(), "chair");
        attributesMaps8.put(BRAND.getValue(), "Relaxo");
        attributesMaps8.put(BREADTH.getValue(), 100);
        attributesMaps8.put(HEIGHT.getValue(), 200);
        attributesMaps8.put(LENGTH.getValue(), 200);
        attributesMaps8.put(TITLE.getValue(), "Reclining Chair");
        product8.setProductId("CHA1");
        product8.setAttributesMap(attributesMaps8);
        persistenceHandler.insertProducts(product8);
        Connector.close();



    }
}
