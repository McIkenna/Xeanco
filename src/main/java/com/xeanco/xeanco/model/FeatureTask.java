package com.xeanco.xeanco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class FeatureTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long featureTaskId;
    @Column(updatable = false)
    private String featureIdentifier;
    private String headline;
    @Column(length=2000)
    private String summary;
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "feature_id", nullable = false)
    @JsonIgnore
    private Feature feature;


    @Lob
    private byte[] image;
    private String imageName;
    private String imageType;
    private String downloadUrl;
    private Date createdDate;


    public FeatureTask(
                        Feature feature,
                        String featureIdentifier,
                        String headline,
                       String summary,
                       byte[] image,
                       String imageName,
                       String imageType,
                       String downloadUrl
                    ) {
        this.feature = feature;
        this.featureIdentifier = featureIdentifier;
        this.headline = headline;
        this.summary = summary;
        this.image = image;
        this.imageName = imageName;
        this.imageType = imageType;
        this.downloadUrl = downloadUrl;
    }

    @PrePersist
    protected void setCreatedDate(){
        this.createdDate = new Date();
    }
    public String setFeatureTaskDownloadUrl(String downloadUrl){
        this.downloadUrl = downloadUrl;
        return downloadUrl;
    }

}
