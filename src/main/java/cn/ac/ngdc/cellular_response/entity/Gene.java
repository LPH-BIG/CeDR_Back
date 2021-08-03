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
@Table(name = "gene")
public class Gene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String symbol;

    @Column
    private Integer chromosome;

    @Column
    private String description;

    @Column(length = 100)
    private String type;

    @Column
    private String fullname;

    @Column
    private String refs;

}
