package model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private int id;
    private int quantity;
    private int brand_id;
    private String name;
    private String description;
    private String image_url;
    private double price;
    private Date release_date;
    private double discount;

    // Constructor for inserting a new product
    public Product(int quantity, int brand_id, String name, String description, String image_url, double price, Date release_date, double discount) {
        this.quantity = quantity;
        this.brand_id = brand_id;
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.price = price;
        this.release_date = release_date;
        this.discount = discount;
    }
}
