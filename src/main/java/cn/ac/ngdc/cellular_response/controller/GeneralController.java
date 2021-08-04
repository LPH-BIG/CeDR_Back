package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.entity.Association;
import cn.ac.ngdc.cellular_response.entity.General;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultCode;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import cn.ac.ngdc.cellular_response.service.GeneralService;
import cn.ac.ngdc.cellular_response.utils.Keywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
public class GeneralController {
    @Autowired
    GeneralService generalService;

    @CrossOrigin
    @GetMapping("/general")
    public Result query(@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "current",required = false,defaultValue = "1") Integer pageIndex,
                        @RequestParam(value = "datasetid",required = false) String datasetid,
                        @RequestParam(value = "source",required = false) String source,
                        @RequestParam(value = "project",required = false) String project,
                        @RequestParam(value = "tissuegroup",required = false) String tissuegroup,
                        @RequestParam(value = "tissue",required = false) String tissue,
                        @RequestParam(value = "cellType",required = false) String cellType,
                        @RequestParam(value = "phenotype",required = false) String phenotype,
                        @RequestParam(value = "drug",required = false) String drug){

        Result generalList = generalService.queryGeneral(pageSize,pageIndex-1,datasetid,source,project,tissue,tissuegroup,cellType,phenotype,drug);
        return generalList;
    }


    @CrossOrigin
    @GetMapping("/generalLike")
    public Result queryLike(@RequestParam(value = "source",required = false) String source,
                            @RequestParam(value = "project",required = false) String project,
                            @RequestParam(value = "tissuegroup",required = false) String tissuegroup,
                            @RequestParam(value = "tissue",required = false) String tissue,
                            @RequestParam(value = "phenotype",required = false) String phenotype,
                            @RequestParam(value = "celltype",required = false) String cellType,
                            @RequestParam(value = "drug",required = false) String drug){
//        if(null == source && null == project && null == subproject && null == tissue && null == phenotype && null == cellType   && null == drug){
//            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null,null);
//        }
        List<General> generalList = generalService.queryLike(source,project,tissue,tissuegroup,phenotype,cellType,drug);
        if(null != generalList){
            HashSet<String> Source = new HashSet<String>();
            HashSet<String> Project = new HashSet<String>();
            HashSet<String> Dataset = new HashSet<String>();
            HashSet<String> Tissue = new HashSet<String>();
            HashSet<String> Tissuegroup = new HashSet<String>();
            HashSet<String> Phenotype = new HashSet<String>();
            HashSet<String> Celltype = new HashSet<String>();
            HashSet<String> Drug = new HashSet<String>();
            for (General general : generalList){
                Source.add(general.getSource());
                Project.add(general.getProject());
                Dataset.add(general.getDatasetid());
                Tissue.add(general.getTissue());
                Tissuegroup.add(general.getTissuegroup());
                Phenotype.add(general.getPhenotype());
                Celltype.add(general.getCelltype());
                Drug.add(general.getDrug());
            }
            Map<String,Object> maps = new HashMap<>();
            maps.put("source",Source);
            maps.put("project",Project);
            maps.put("dataset",Dataset);
            maps.put("tissue",Tissue);
            maps.put("tissuegroup",Tissuegroup);
            maps.put("phenotype",Phenotype);
            maps.put("celltype",Celltype);
            maps.put("drug",Drug);
            return ResultFactory.buildSuccessResult(maps,null);
        }else {
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null,null);
        }
    }

}
