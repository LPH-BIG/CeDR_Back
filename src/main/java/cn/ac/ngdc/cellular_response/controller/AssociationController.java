package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.entity.Association;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultCode;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import cn.ac.ngdc.cellular_response.service.AssociationService;
import cn.ac.ngdc.cellular_response.utils.Keywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AssociationController {
    @Autowired
    AssociationService associationService;

    @CrossOrigin
    @GetMapping("/subproject")
    public Result query(@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "pageIndex",required = false,defaultValue = "1") Integer pageIndex,
                        @RequestParam(value = "source",required = false) String source,
                        @RequestParam(value = "project",required = false) String project,
                        @RequestParam(value = "subproject",required = false) String subproject,
                        @RequestParam(value = "tissue",required = false) String tissue,
                        @RequestParam(value = "celltype",required = false) String cellType,
                        @RequestParam(value = "phenotype",required = false) String phenotype,
                        @RequestParam(value = "drug",required = false) String drug,
                        @RequestParam(value = "overlapgene",required = false) String overlapgene,
                        @RequestParam(value = "pcutoff",required = false) Double pcutoff,
                        @RequestParam(value = "orcutoff",required = false) Double orcutoff){

        if (    pageIndex == 0 && pageSize ==0 &&
                project == null && subproject ==null
                && source==null && tissue==null
                && cellType==null && phenotype==null
                && drug==null && overlapgene==null
                && pcutoff==null && orcutoff==null
        ){
            return ResultFactory.buildFailResult("");
        }else {
            if (pageIndex==0){
                pageIndex=1;
            }
            Result associationList = associationService.queryAssociation(pageSize,pageIndex-1,source,project,subproject,tissue,cellType,phenotype,overlapgene,drug,pcutoff,orcutoff);
            return associationList;
        }

    }
    @CrossOrigin
    @GetMapping("/searchLike")
    public Result queryLike(@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "pageIndex",required = false,defaultValue = "0") Integer pageIndex,
                        @RequestParam(value = "source",required = false) String source,
                        @RequestParam(value = "project",required = true) String project,
                        @RequestParam(value = "subproject",required = true) String subproject,
                        @RequestParam(value = "tissue",required = false) String tissue,
                        @RequestParam(value = "cellType",required = false) String cellType,
                        @RequestParam(value = "overlapgene",required = false) String overlapgene,
                        @RequestParam(value = "drug",required = false) String drug,
                        @RequestParam(value = "pcutoff",required = false) Double pcutoff,
                        @RequestParam(value = "orcutoff",required = false) Double orcutoff){
        if (null == source && null == project && null == subproject && null == tissue && null == cellType  && null == overlapgene  && null == drug){
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null,null);
        }
        List<Association> associationList = associationService.queryLike(0,0,source,project,subproject,tissue,cellType,overlapgene,drug,pcutoff,orcutoff);
        if(null != associationList){
//            HashSet<String> Source = new HashSet<String>();
//            HashSet<String> Tissue = new HashSet<String>();
//            HashSet<String> Phenotype = new HashSet<String>();
            HashSet<String> Celltype = new HashSet<String>();
            HashSet<String> Drug = new HashSet<String>();
//            HashSet<String> Overlapgene = new HashSet<String>();
            for (Association association : associationList){
//                Source.add(association.getSource());
//                Tissue.add(association.getTissue());
//                Phenotype.add(association.getPhenotype());
                Celltype.add(association.getCelltype());
                Drug.add(association.getDrug());
//                Overlapgene.add(association.getOverlapgene());
            }
//            Keywords keywords = new Keywords(Celltype,Drug,Overlapgene);
            Keywords keywords = new Keywords(Celltype,Drug,null);
            return ResultFactory.buildSuccessResult(keywords,null);
        }else {
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null,null);
        }
    }

    @CrossOrigin
    @GetMapping("/network")
    public Result getNetwork(@RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "pageIndex",required = false,defaultValue = "0") Integer pageIndex,
                        @RequestParam(value = "source",required = false) String source,
                        @RequestParam(value = "project",required = false) String project,
                        @RequestParam(value = "subproject",required = false) String subproject,
                        @RequestParam(value = "tissue",required = false) String tissue,
                        @RequestParam(value = "celltype",required = false) String cellType,
                        @RequestParam(value = "phenotype",required = false) String phenotype,
                        @RequestParam(value = "overlapgene",required = false) String overlapgene,
                        @RequestParam(value = "drug",required = false) String drug,
                        @RequestParam(value = "pcutoff",required = false) Double pcutoff,
                        @RequestParam(value = "orcutoff",required = false) Double orcutoff){
//调整网络图的点的数目
        Result associationList = associationService.queryAssociation(0,0,source,project,subproject,tissue,cellType,phenotype,overlapgene,drug,0.02,orcutoff);
        List<Association> associations = (List<Association>) associationList.getData();
        HashSet<Object> networkList = new HashSet<>();
        HashSet<Object> nodeList = new HashSet<>();
        Map<String,Object> objectMap = new HashMap<>();
        for (Association association : associations){
            Map<String,String> map1 = new HashMap<>();
            map1.put("from",association.getSubproject());
            map1.put("to",association.getCelltype());
            Map<String,Object> nodeMap1 = new HashMap<>();
            Map<String,Object> markerMap1 = new HashMap<>();
            markerMap1.put("radius",10);
            nodeMap1.put("marker",markerMap1);
            nodeMap1.put("id",association.getSubproject());
            nodeMap1.put("color","red");
            nodeList.add(nodeMap1);

            if (null!=cellType || null!=drug){
                    Map<String,String> map2 = new HashMap<>();
                    map2.put("from",association.getCelltype());
                    map2.put("to",association.getDrug());
                    networkList.add(map1);
                    networkList.add(map2);
                    Map<String,Object> nodeMap2 = new HashMap<>();
                    Map<String,Object> markerMap2 = new HashMap<>();
                    markerMap2.put("radius",7);
                    nodeMap2.put("marker",markerMap2);
                    nodeMap2.put("id",association.getCelltype());
                    nodeMap2.put("color","orange");
                    nodeList.add(nodeMap2);
            }else {
                if (association.getPvalue1()<=0.01 && association.getPvalue2()<=0.01){
                    Map<String,String> map2 = new HashMap<>();
                    map2.put("from",association.getCelltype());
                    map2.put("to",association.getDrug());
                    networkList.add(map1);
                    networkList.add(map2);
                    Map<String,Object> nodeMap2 = new HashMap<>();
                    Map<String,Object> markerMap2 = new HashMap<>();
                    markerMap2.put("radius",7);
                    nodeMap2.put("marker",markerMap2);
                    nodeMap2.put("id",association.getCelltype());
                    nodeMap2.put("color","orange");
                    nodeList.add(nodeMap2);
                }
            }
        }
        objectMap.put("data",networkList);
//        TODO://node调整
        objectMap.put("nodes",nodeList);
        return ResultFactory.buildSuccessResult(objectMap,null);
    }

}
