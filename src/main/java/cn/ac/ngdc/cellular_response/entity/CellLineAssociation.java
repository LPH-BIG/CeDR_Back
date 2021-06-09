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
public class CellLineAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String accession;

    @Column(length = 100)
    private String tissue;

    @Column(length = 100)
    private String status;

    @Column(length = 100)
    private String inst;

    @Column(length = 100)
    private String celltype;

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

    @Column(columnDefinition = "text")
    private String overlapgene;

}
