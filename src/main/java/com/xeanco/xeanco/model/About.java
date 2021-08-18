package com.xeanco.xeanco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class About {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String aboutName;
    private String aboutHeading;
    @Column(length = 1000)
    private String aboutSubHeading;
    @Column(length = 3000)
    private String aboutDescription;
}
