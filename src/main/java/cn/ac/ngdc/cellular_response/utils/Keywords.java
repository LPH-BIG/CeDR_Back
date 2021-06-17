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
    private HashSet<String> celltype;
    private HashSet<String> drug;
    private HashSet<String> overlapgene;

}
