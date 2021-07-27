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
public class FeatureLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer FTSequence = 0;
    private String featureIdentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feature_id", nullable = false)
    @JsonIgnore
    private Feature feature;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "featureLog")
    private List<FeatureTask> featureTasks = new ArrayList<>();


}
