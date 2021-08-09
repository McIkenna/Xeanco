package com.xeanco.xeanco.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@Data
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long featureId;
    @Column(updatable = false, unique = true)
    private String featureIdentifier;
    @NotBlank(message = "Feature Title cannot be empty")
    private String featureHeading;
    @Column(length = 1000)
    private String featureSubHeading;
    @Column(length = 2000)
    @NotBlank(message = "Feature Body cannot be empty")
    private String featureDescription;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "feature")
    private FeatureTask featureTask;

    @Lob
    private byte[] featureImage;
    private String featureImageName;
    private String featureImageType;
    private String featureDownloadUrl;


    public Feature(
                   String featureHeading,
                   String featureSubHeading,
                   String featureDescription,
                   byte[] featureImage,
                   String featureImageName,
                   String featureImageType,
                   String featureDownloadUrl) {
        this.featureHeading = featureHeading;
        this.featureSubHeading = featureSubHeading;
        this.featureDescription = featureDescription;
        this.featureImage = featureImage;
        this.featureImageName = featureImageName;
        this.featureImageType = featureImageType;
        this.featureDownloadUrl = featureDownloadUrl;
    }

    public String setFeatureDownloadUrl (String featureDownloadUrl){
        this.featureDownloadUrl = featureDownloadUrl;
        return featureDownloadUrl;
    }
/*
    UUID newId = UUID.randomUUID();

    public void setFeatureIdentifier (){
        this.featureIdentifier = newId.toString();
    }*/
}
