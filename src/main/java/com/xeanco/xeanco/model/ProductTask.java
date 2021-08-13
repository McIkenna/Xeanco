package com.xeanco.xeanco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String productIdentifier;
    @Column(updatable = false)
    private String productSequence;
    private String productTskName;
    @Column(length=500)
    private String productTskSummary;
    @Column(length=1000)
    private String productTskDetail;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch=FetchType.EAGER)
    @JoinColumn(name="product_log_id", updatable = false, nullable = false)
    @JsonIgnore
    private ProductLog productLog;
    @Lob
    private byte[] productTskImg;
    private String productTskImgName;
    private String productTskImgType;
    private String productTskDownloadUrl;
    private Date createdDate;

    @PrePersist
    protected void setCreatedDate(){
        this.createdDate = new Date();
    }

    public String setProductTskDownloadUrl(String productTskDownloadUrl){
        this.productTskDownloadUrl = productTskDownloadUrl;
        return productTskDownloadUrl;
    }
}
