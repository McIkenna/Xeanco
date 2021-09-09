package com.xeanco.xeanco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String extraName;
    @Column(length=1000)
    private String extraSummary;

    @Lob
    private byte[] img1;
    private String imgName1;
    @Lob
    private byte[] img2;
    private String imgName2;
    @Lob
    private byte[] img3;
    private String imgName3;
    @Lob
    private byte[] img4;
    private String imgName4;


}
