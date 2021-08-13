package cn.ac.ngdc.cellular_response.service;

import cn.ac.ngdc.cellular_response.dao.AssociationDAO;
import cn.ac.ngdc.cellular_response.entity.Association;
import cn.ac.ngdc.cellular_response.result.Meta;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssociationService {
    @Autowired
    AssociationDAO associationDAO;
    public Result queryAssociation(Integer pageSize, Integer pageIndex,String datasetid, String associationid,
                                   String source,String project, String tissue,String tissuegroup, String cellType,
                                   String phenotype,String overlapgene, String drug, Double pcutoff, Double orcutoff,
                                   Double pcutoff2, Double orcutoff2,Double spcutoff, Double spearman){
        Long total = 0L;
        List<Association> data = null;
        Meta meta = new Meta();
        Specification<Association> queryCondition = new Specification<Association>() {
            @Override
            public Predicate toPredicate(Root<Association> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                if (datasetid != null){
                    predicateList.add(criteriaBuilder.equal(root.get("datasetid"),datasetid));
                }
                if (associationid != null){
                    predicateList.add(criteriaBuilder.equal(root.get("associationid"),associationid));
                }
                if (source != null){
                    predicateList.add(criteriaBuilder.equal(root.get("source"),source));
                }
                if (project != null){
                    predicateList.add(criteriaBuilder.equal(root.get("project"),project));
                }
                if (tissuegroup != null){
                    predicateList.add(criteriaBuilder.equal(root.get("tissuegroup"),tissuegroup));
                }
                if (tissue != null){
                    predicateList.add(criteriaBuilder.equal(root.get("tissue"),tissue));
                }
                if (cellType != null){
                    predicateList.add(criteriaBuilder.equal(root.get("celltype"),cellType));
                }
                if (phenotype != null){
                    predicateList.add(criteriaBuilder.equal(root.get("phenotype"),phenotype));
                }
                if (overlapgene != null){
                    predicateList.add(criteriaBuilder.equal(root.get("overlapgene"),overlapgene));
                }
                if (drug != null){
                    predicateList.add(criteriaBuilder.equal(root.get("drug").as(String.class),drug));
                }
                if (pcutoff != null){
                    predicateList.add(criteriaBuilder.le(root.get("pvalue1").as(Double.class),pcutoff));
                }
                if (orcutoff != null){
                    predicateList.add(criteriaBuilder.ge(root.get("oddsratio1").as(Double.class),orcutoff));
                }
                if (pcutoff2 != null){
                    predicateList.add(criteriaBuilder.le(root.get("pvalue2").as(Double.class),pcutoff));
                }
                if (orcutoff2 != null){
                    predicateList.add(criteriaBuilder.ge(root.get("oddsratio2").as(Double.class),orcutoff));
                }
                if (spcutoff != null){
                    predicateList.add(criteriaBuilder.le(root.get("spvalue").as(Double.class),spcutoff));
                }
                if (spearman != null){
                    predicateList.add(criteriaBuilder.le(root.get("spearman").as(Double.class),spearman));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        try {
            if (pageIndex == 0 && pageSize == 0){
                data = associationDAO.findAll(queryCondition);
            }else {
                Sort sort = Sort.by(Sort.Direction.ASC, "pvalue1");
                data = associationDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize,sort)).getContent();
            }
            total = associationDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageSize(pageSize);
            meta.setPageIndex(pageIndex);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResultFactory.buildSuccessResult(data,meta);
    }

    public List<Association> queryLike(Integer pageSize,Integer pageIndex,String datasetid, String associationid,
                                              String source,String project,String tissue,String tissuegroup,String cellType,
                                              String overlapgene,String drug,Double pcutoff,Double orcutoff){
//        Long total = 0L;
        List<Association> data = null;
        Specification<Association> queryCondition = new Specification<Association>() {
            @Override
            public Predicate toPredicate(Root<Association> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                if (datasetid != null){
                    predicateList.add(criteriaBuilder.equal(root.get("datasetid"),datasetid));
                }
                if (associationid != null){
                    predicateList.add(criteriaBuilder.equal(root.get("associationid"),associationid));
                }
                if (source != null){
                    predicateList.add(criteriaBuilder.equal(root.get("source"),source));
                }
                if (project != null){
                    predicateList.add(criteriaBuilder.equal(root.get("project"),project));
                }
                if (tissuegroup != null){
                    predicateList.add(criteriaBuilder.equal(root.get("tissuegroup"),tissuegroup));
                }
                if (tissue != null){
                    predicateList.add(criteriaBuilder.like(root.get("tissue"),"%" + tissue + "%"));
                }
                if (cellType != null){
                    predicateList.add(criteriaBuilder.like(root.get("celltype"),"%" + cellType + "%"));
                }
                if (overlapgene != null){
                    predicateList.add(criteriaBuilder.like(root.get("overlapgene"),"%" + overlapgene + "%"));
                }
                if (drug != null){
                    predicateList.add(criteriaBuilder.like(root.get("drug").as(String.class),"%" + drug + "%"));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        try {
            if (pageIndex == 0 && pageSize == 0){
                data = associationDAO.findAll(queryCondition);
            }else {
                Sort sort = Sort.by(Sort.Direction.ASC, "id");
                data = associationDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize,sort)).getContent();
            }
//            total = associationDAO.count(queryCondition);
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

}
