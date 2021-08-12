package cn.ac.ngdc.cellular_response.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "association")
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100,nullable = false)
    private String datasetid;

    @Column(length = 100,nullable = false,unique = true)
    private String associationid;

    @Column(length = 100)
    private String source;

    @Column(length = 100)
    private String project;

    @Column
    private String tissue;

    @Column(length = 100)
    private String tissuegroup;

    @Column(length = 100)
    private String phenotype;

    @Column(length = 100)
    private String celltype;

    @Column(length = 100)
    private String inst;

    @Column(length = 100)
    private String drug;

    @Column
    private Double pvalue1;

    @Column
    private Double oddsratio1;

    @Column
    private Double pvalue2;

    @Column
    private Double oddsratio2;

    @Column
    private Double spearman;

    @Column
    private Double spvalue;

    @Column
    private Double overlapgenenum;

    @Column(columnDefinition = "text")
    private String overlapgene;

    @Column(columnDefinition = "text")
    private String photocelltype;

    @Column(columnDefinition = "text")
    private String photodrug;

    @Column(columnDefinition = "text")
    private String matrixplot;

    @Column(columnDefinition = "text")
    private String violinplot;


}
