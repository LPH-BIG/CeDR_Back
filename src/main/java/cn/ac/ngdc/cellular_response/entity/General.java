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
@Table(name = "general")
public class General {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100,nullable = false,unique = true)
    private String datasetid;

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

    @Column
    private String drug;

    @Column
    private String celltype;

    @Column
    private Integer total_reported_cell;

    @Column
    private Integer celltype_num;

    @Column
    private String cell_source;

    @Column(length = 100)
    private String technique;

    @Column
    private String doi;

    @Column
    private String title;

}
