package listing;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * This class is used for representing listing data
 * @contains
 * listing id
 * attributes - listing attributes are stored in attributes map
 * Key is the name of the attribute and value is the data of the attribute
 *
 *getter and setter methods for listingId and attributes
 * Created by jananiravi on 7/7/16.
 */
public class Listing {
    // listingid value
    private String listingId;
    // attributes of listing object
    private Map<String, Object> attributes = Maps.newHashMap();


    public Listing() {
    }

    // Returns the listingid
    public String getListingId() {
        return listingId;
    }
    // to set the listing id when creating listing object
    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    //Returns map of attributes of listing
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    // to set the attributes map when creating listing object
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "listingId='" + listingId + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}

