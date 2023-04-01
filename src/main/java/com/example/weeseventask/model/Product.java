package com.example.weeseventask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.weeseventask.DTO.ProductDTO;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
@Table (name ="products")

@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(name = "category")
    private String category;

    @Column(length = 200)
    private String description;

    @Column(name = "image")
    private String imageURL;

    @Column(name = "price")
    @NotNull
    private String price;


    public Product(ProductDTO productDTO) {
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.category = productDTO.getCategory();
        this.price =  productDTO.getPrice();
        this.imageURL = productDTO.getImageURL();
    }

    public Product() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;

        return id == product.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageURL + '\'' +
                ", price=" + price +
                '}';
    }
}
