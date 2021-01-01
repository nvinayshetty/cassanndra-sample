package product;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import common.dto.AttributeNames;
import persistence.Connector;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by navdeepsingh on 23/07/16.
 * This class contains methods to access and update products column family
 * in cms keyspace
 */
public class ProductPersistenceHandler {
    // keyspace name
    private static String keyspace = "cms";
    // column family name
    private static String columnFamily = "products";


    /**
     * Returns products that belong to any of the @categories and has
     * brand value = @brands
     * @param categories list of categories
     * @param brand
     * @return
     */
    public List<Product> getProductsFor(List<String> categories, String brand){
        List<Product> response = Lists.newArrayList();
        //get session object
        Session session = Connector.getSession();


        // query on category
        String categoryAttrName = AttributeNames.CATEGORY.getValue();
        String brandAttrName = AttributeNames.BRAND.getValue();

        // in query on category
        Statement selectInStatement = QueryBuilder.select().all().from(keyspace, columnFamily).
                where(QueryBuilder.in(categoryAttrName, categories))
                .and(QueryBuilder.eq(brandAttrName, brand));

        // result set is returned when statement is executed
        ResultSet results = session.execute(selectInStatement);

        // For paging - getch the iterator
        Iterator<Row> iter = results.iterator();
        while (!results.isFullyFetched()) {
            results.fetchMoreResults();
            Row row = iter.next();
            response.add(getProductFromRow(row));
        }
        // if the no of rows in resultset is < page size (default is 5000)
        while(iter.hasNext()){
            Row row = iter.next();
            response.add(getProductFromRow(row));
        }
        return response;

    }

    /**
     * Creates a new product
     * if category,brand,productId is not unique then values of the existing product
     * be overridden by the new values
     * Method does not check for uniqueness. To be done by the caller
     * @param product
     */
    public void insertProducts(Product product){
        // insert statement
        Insert insertStatement = QueryBuilder.insertInto(keyspace, columnFamily);

        Map<String, Object> attributes = product.getAttributesMap();
        insertStatement.value(AttributeNames.PRODUCTID.getValue(), product.getProductId());
        // add all attributes in product attriute set to insert into()
        for(String attributeName : attributes.keySet()){
            insertStatement = insertStatement.value(attributeName, attributes.get(attributeName));
        }
        // set timestamp at client side (timestamp should always be set by
        // client so that all replicas have same timestamp)
        insertStatement.setDefaultTimestamp(new ThreadLocalMonotonicTimestampGenerator().next());
        Session session = Connector.getSession();
        // execute the statement
        session.execute(insertStatement);

        session.close();


    }

    /**
     *  Returns the product object made by transforming the row object returned by driver
    * to the custom Product Object
    * @param row
    * @return Product
    */
    private Product getProductFromRow(Row row){

        Product product = new Product();
        Map<String, Object> attributes = Maps.newHashMap();

        if(row != null) {
            // get all column definitions
            ColumnDefinitions defns = row.getColumnDefinitions();
            List<ColumnDefinitions.Definition> columnDefinitions = defns.asList();
            // list contains only those columns for which product ahs data
            for (ColumnDefinitions.Definition columnDefn : columnDefinitions){
                String columnName = columnDefn.getName();
                Object data = row.getObject(columnName);
                if (AttributeNames.PRODUCTID.getValue().equals(columnName)) {
                    product.setProductId(data.toString());
                }else{
                    attributes.put(columnName, data);
                }
            }

        }
        product.setAttributesMap(attributes);
        return product;
    }


}
