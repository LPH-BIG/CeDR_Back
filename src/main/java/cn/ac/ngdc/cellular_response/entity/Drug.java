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
@Table(name = "drug")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100,nullable = false,unique = true)
    private String inst;

    @Column(length = 10)
    private String batch_id;

    @Column(length = 100)
    private String name;

    @Column(length = 10)
    private String inn1;

    @Column
    private Double concentration;

    @Column(length = 100)
    private Double duration;

    @Column(length = 100)
    private String cell;

    @Column(length = 100)
    private String array;

    @Column(length = 100)
    private String perturbation_scan_id;

    @Column(length = 100)
    private String vehicle_scan_id;

    @Column(length = 100)
    private String scanner;

    @Column(length = 100)
    private String vehicle;

    @Column(length = 100)
    private String vendor;

    @Column
    private String catalog_number;

    @Column
    private String catalog_name;

}
