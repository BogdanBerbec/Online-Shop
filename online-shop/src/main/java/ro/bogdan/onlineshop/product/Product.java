package ro.bogdan.onlineshop.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private final int productId;

    @Column(name = "name")
    @NotBlank
    private final String name;

    @Column(name = "description")
    @NotBlank
    private final String description;

    @Column(name = "price")
    @NotBlank
    private final double price;

    @Column(name = "gender")
    @NotNull
    private final Gender gender;

    public Product(@JsonProperty("product_id") int productId,
                   @JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") double price,
                   @JsonProperty("gender") Gender gender) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.gender = gender;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Gender getGender() {
        return gender;
    }

    public enum Gender {
        MALE, FEMALE, BOY, GIRL
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", gender=" + gender +
                '}';
    }
}
