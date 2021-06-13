package cn.ac.ngdc.cellular_response.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keywords {
    private HashSet<String> source;
    private HashSet<String> tissue;
    private HashSet<String> phenotype;
    private HashSet<String> celltype;
    private HashSet<String> inst;

}
