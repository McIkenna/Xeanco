package com.xeanco.xeanco.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true)
    private String productIdentifier;
    private String productName;
    @Column(length=500)
    private String productSummary;

    @Lob
    private byte[] productImg;
    private String productImgName;
    private String productImgType;
    private String productDownloadUrl;
    private Date createdDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private ProductLog productLog;

    public Product(String productName,
                   String productSummary,
                   byte[] productImg,
                   String productImgName,
                   String productImgType,
                   String productDownloadUrl) {
        this.productName = productName;
        this.productSummary = productSummary;
        this.productImg = productImg;
        this.productImgName = productImgName;
        this.productImgType = productImgType;
        this.productDownloadUrl = productDownloadUrl;
    }

    @PrePersist
    protected void setCreatedDate(){
        this.createdDate = new Date();
    }

    public String setProductDownloadUrl(String productDownloadUrl){
        this.productDownloadUrl = productDownloadUrl;
        return productDownloadUrl;
    }

}
