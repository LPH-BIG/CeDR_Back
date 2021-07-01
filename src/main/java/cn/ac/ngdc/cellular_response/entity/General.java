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

    @Column(length = 100)
    private String source;

    @Column(length = 100)
    private String project;

    @Column(length = 200)
    private String subproject;

    @Column(length = 100)
    private String tissue;

    @Column(length = 100)
    private String phenotype;

    @Column
    private String reference;

    @Column
    private String description;

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

    @Column(length = 100)
    private String journal;

    @Column
    private String title;

    @Column
    private String date;

    @Column
    private String contrasts;

    @Column
    private String developmentalstage;

}
