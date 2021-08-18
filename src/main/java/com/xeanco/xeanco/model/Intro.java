package com.xeanco.xeanco.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Intro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String introName;
    @Column(length=500)
    private String introDescription;
    @Lob
    private byte[] image;
    private String imageName;
    private String imageType;
    private String imageDownloadUrl;

    public Intro(String introName,
                 String introDescription,
                 byte[] image,
                 String imageName,
                 String imageType,
                 String imageDownloadUrl) {
        this.introName = introName;
        this.introDescription = introDescription;

        this.image = image;
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageDownloadUrl = imageDownloadUrl;
    }

    public String setImageDownloadUrl(String ImageDownloadUrl){
        this.imageDownloadUrl = imageDownloadUrl;
        return ImageDownloadUrl;
    }
}
