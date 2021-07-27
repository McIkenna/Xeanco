package com.xeanco.xeanco.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true)
    private String featureIdentifier;
    private String featureHeading;
    private String featureSubHeading;
    @Column(length = 500)
    private String featureDescription;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "feature")
    private FeatureLog featureLog;

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
}
