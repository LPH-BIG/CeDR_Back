package cn.ac.ngdc.cellular_response.service;

import cn.ac.ngdc.cellular_response.dao.GeneralDAO;
import cn.ac.ngdc.cellular_response.entity.Association;
import cn.ac.ngdc.cellular_response.entity.General;
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
public class GeneralService {
    @Autowired
    GeneralDAO generalDAO;

    public Result queryGeneral(Integer pageSize, Integer pageIndex,String datasetid,
                               String source,String project, String tissue,String tissuegroup ,String cellType,
                               String phenotype, String drug){

        Long total = 0L;
        List<General> data = null;
        Meta meta = new Meta();
        Specification<General> queryCondition = new Specification<General>(){
            @Override
            public Predicate toPredicate(Root<General> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                if (datasetid != null){
                    predicateList.add(criteriaBuilder.equal(root.get("datasetid"),datasetid));
                }
                if (source != null){
                    predicateList.add(criteriaBuilder.equal(root.get("source"),source));
                }
                if (project != null){
                    predicateList.add(criteriaBuilder.equal(root.get("project"),project));
                }
                if (tissue != null){
                    predicateList.add(criteriaBuilder.equal(root.get("tissue"),tissue));
                }
                if (tissuegroup != null){
                    predicateList.add(criteriaBuilder.equal(root.get("tissuegroup"),tissuegroup));
                }
                if (cellType != null){
                    predicateList.add(criteriaBuilder.like(root.get("celltype"),cellType));
                }
                if (phenotype != null){
                    predicateList.add(criteriaBuilder.equal(root.get("phenotype"),phenotype));
                }
                if (drug != null){
                    predicateList.add(criteriaBuilder.like(root.get("inst").as(String.class),drug));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            };
        };
        try {
            if (pageIndex == 0 && pageSize == 0){
                data = generalDAO.findAll(queryCondition);
            }else {
                Sort sort = Sort.by(Sort.Direction.ASC, "id");
                data = generalDAO.findAll(queryCondition, PageRequest.of(pageIndex, pageSize,sort)).getContent();
            }
            total = generalDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageSize(pageSize);
            meta.setPageIndex(pageIndex);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResultFactory.buildSuccessResult(data,meta);
    }

    public List<General> queryLike(String source,String project,String tissue,String tissuegroup,String phenotype,String celltype,String drug){
        List<General> generalList = null;
        Specification<General> queryCondition = new Specification<General>() {
            @Override
            public Predicate toPredicate(Root<General> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                if (source != null){
                    predicateList.add(criteriaBuilder.like(root.get("source"),"%" + source + "%"));
                }
                if (project != null){
                    predicateList.add(criteriaBuilder.like(root.get("project"),"%" + project + "%"));
                }
                if (tissue != null){
                    predicateList.add(criteriaBuilder.like(root.get("tissue"),"%" + tissue + "%"));
                }
                if (tissuegroup != null){
                    predicateList.add(criteriaBuilder.like(root.get("tissuegroup"),"%" + tissuegroup + "%"));
                }
                if (phenotype != null){
                    predicateList.add(criteriaBuilder.like(root.get("phenotype"),"%" + phenotype + "%"));
                }
                if (celltype != null){
                    predicateList.add(criteriaBuilder.like(root.get("celltype"),"%" + celltype + "%"));
                }
                if (drug != null){
                    predicateList.add(criteriaBuilder.like(root.get("drug").as(String.class),"%" + drug + "%"));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        try {
            Sort sort = Sort.by(Sort.Direction.ASC, "id");
            generalList = generalDAO.findAll(queryCondition);
        } catch (Exception e){
            e.printStackTrace();
        }
        return generalList;
    }
}
