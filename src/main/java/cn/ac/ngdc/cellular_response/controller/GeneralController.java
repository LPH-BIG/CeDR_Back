package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    @Autowired
    GeneralService generalService;

    @CrossOrigin
    @GetMapping("/general")
    public Result query(@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "current",required = false,defaultValue = "1") Integer pageIndex,
                        @RequestParam(value = "source",required = false) String source,
                        @RequestParam(value = "project",required = false) String project,
                        @RequestParam(value = "subproject",required = false) String subproject,
                        @RequestParam(value = "tissue",required = false) String tissue,
                        @RequestParam(value = "cellType",required = false) String cellType,
                        @RequestParam(value = "phenotype",required = false) String phenotype,
                        @RequestParam(value = "drug",required = false) String drug){

        Result generalList = generalService.queryGeneral(pageSize,pageIndex-1,source,project,subproject,tissue,cellType,phenotype,drug);
        return generalList;
    }
}
