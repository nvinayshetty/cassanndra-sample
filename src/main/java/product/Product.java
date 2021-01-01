package product;

import java.util.Map;

/**
 * This class is used to represent product
 * Created by jananiravi on 7/7/16.
 * The class contains:
 * productId
 * Attributes of product are stored in attributesMap
 * Key of the map is the name of the attribute and value is the data of the attribute
 * Thee are getter and seller for productId and attributeMap
 */
public class Product {
    // productIdentifier
    private String productId;
    // Attributes saved in column-value map
    private Map<String, Object> attributesMap;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Map<String, Object> getAttributesMap() {
        return attributesMap;
    }


    public void setAttributesMap(Map<String, Object> attributesMap) {
        this.attributesMap = attributesMap;
    }



    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", attributesMap=" + attributesMap +
                '}';
    }
}
