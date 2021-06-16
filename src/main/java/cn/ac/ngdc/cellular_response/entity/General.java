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

    @Column(columnDefinition = "text")
    private String reference;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String drug;


}
