package com.xeanco.xeanco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer productSequence = 0;
    private String productIdentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "productLog")
    private List<ProductTask> productTasks = new ArrayList<>();
}
