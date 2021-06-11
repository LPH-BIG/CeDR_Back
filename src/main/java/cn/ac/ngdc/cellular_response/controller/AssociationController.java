package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.entity.Association;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultCode;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import cn.ac.ngdc.cellular_response.service.AssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssociationController {
    @Autowired
    AssociationService associationService;

    @CrossOrigin
    @GetMapping("/search")
    public Result query(@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "pageIndex",required = false,defaultValue = "0") Integer pageIndex,
                        @RequestParam(value = "source",required = false) String source,
                        @RequestParam(value = "tissue",required = false) String tissue,
                        @RequestParam(value = "cellType",required = false) String cellType,
                        @RequestParam(value = "phenotype",required = false) String phenotype,
                        @RequestParam(value = "drug",required = false) String drug,
                        @RequestParam(value = "pcutoff",required = false,defaultValue = "0.1") Double pcutoff,
                        @RequestParam(value = "orcutoff",required = false,defaultValue = "1") Double orcutoff){
        Result associationList = associationService.queryAssociation(pageSize,pageIndex-1,source,tissue,cellType,phenotype,drug,pcutoff,orcutoff);
        return associationList;
    }
    @CrossOrigin
    @GetMapping("/searchLike")
    public Result queryLike(@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "pageIndex",required = false,defaultValue = "0") Integer pageIndex,
                        @RequestParam(value = "source",required = false) String source,
                        @RequestParam(value = "tissue",required = false) String tissue,
                        @RequestParam(value = "cellType",required = false) String cellType,
                        @RequestParam(value = "phenotype",required = false) String phenotype,
                        @RequestParam(value = "drug",required = false) String drug,
                        @RequestParam(value = "pcutoff",required = false,defaultValue = "0.1") Double pcutoff,
                        @RequestParam(value = "orcutoff",required = false,defaultValue = "1") Double orcutoff){
        List<Association> associationList = associationService.queryLike(0,0,source,tissue,cellType,phenotype,drug,pcutoff,orcutoff);

        if(null != associationList){
            return ResultFactory.buildSuccessResult(associationList,null);
        }else {
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null,null);
        }
    }

}
