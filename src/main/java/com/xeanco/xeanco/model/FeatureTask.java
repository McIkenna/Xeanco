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
    private Long id;
    @Column(updatable = false, insertable = false)
    private String featureSequence;
    private String headline;
    private String summary;
    private String details;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
    @JoinColumn(name="featureLog_id", updatable = false, nullable = false)
    @JsonIgnore
    private FeatureLog featureLog;

    @Column(updatable = false)
    private String featureIdentifier;

    @Lob
    private byte[] image;
    private String imageName;
    private String imageType;
    private String downloadUrl;
    private Date createdDate;


    public FeatureTask(
                        String featureSequence,
                        FeatureLog featureLog,
                        String headline,
                       String summary,
                       String details,
                       byte[] image,
                       String imageName,
                       String imageType,
                       String downloadUrl
                    ) {
        this.featureSequence = featureSequence;
        this.featureLog = featureLog;
        this.headline = headline;
        this.summary = summary;
        this.details = details;
        this.image = image;
        this.imageName = imageName;
        this.imageType = imageType;
        this.downloadUrl = downloadUrl;
    }

    @PrePersist
    protected void onCreate(){
        this.createdDate = new Date();
    }
    public String setFeatureTaskDownloadUrl(String downloadUrl){
        this.downloadUrl = downloadUrl;
        return downloadUrl;
    }

}
