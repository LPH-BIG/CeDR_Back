package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.dao.GeneDAO;
import cn.ac.ngdc.cellular_response.entity.Gene;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GeneController {
    @Autowired
    GeneDAO geneDAO;

    @CrossOrigin
    @GetMapping("/gene")
    public Result getByName(@RequestParam(value = "symbol",required = true) String symbol){
        String[] names = symbol.split(",");
        List<Gene> geneList = new ArrayList<>();
        for (String name : names ){
            Gene gene = geneDAO.findBySymbol(name);
            if (null != gene){
                geneList.add(gene);
            }
        }
        return ResultFactory.buildSuccessResult(geneList,null);
    }
}
