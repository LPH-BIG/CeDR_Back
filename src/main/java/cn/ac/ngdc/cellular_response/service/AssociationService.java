package cn.ac.ngdc.cellular_response.service;

import cn.ac.ngdc.cellular_response.dao.AssociationDAO;
import cn.ac.ngdc.cellular_response.entity.Association;
import cn.ac.ngdc.cellular_response.result.Meta;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Result queryAssociation(Integer pageSize, Integer pageIndex,
                                   String source, String tissue, String cellType,
                                   String phenotype, String drug, Double pcutoff, Double orcutoff){
        Long total = 0L;
        List<Association> data = null;
        Meta meta = new Meta();
        Specification<Association> queryCondition = new Specification<Association>() {
            @Override
            public Predicate toPredicate(Root<Association> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                if (source != null){
                    predicateList.add(criteriaBuilder.equal(root.get("source"),source));
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
                if (drug != null){
                    predicateList.add(criteriaBuilder.equal(root.get("inst").as(String.class),drug));
                }

//                System.out.println(drug);
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
            total = associationDAO.count(queryCondition);
            meta.setTotal(total);
            meta.setPageSize(pageSize);
            meta.setPageIndex(pageIndex);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResultFactory.buildSuccessResult(data,meta);
    }

    public List<Association> queryLike(Integer pageSize,Integer pageIndex,
                                              String source,String tissue,String cellType,
                                              String phenotype,String drug,Double pcutoff,Double orcutoff){
//        Long total = 0L;
        List<Association> data = null;
        Specification<Association> queryCondition = new Specification<Association>() {
            @Override
            public Predicate toPredicate(Root<Association> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();
                if (source != null){
                    predicateList.add(criteriaBuilder.like(root.get("source"),"%" + source + "%"));
                }
                if (tissue != null){
                    predicateList.add(criteriaBuilder.like(root.get("tissue"),"%" + tissue + "%"));
                }
                if (cellType != null){
                    predicateList.add(criteriaBuilder.like(root.get("celltype"),"%" + cellType + "%"));
                }
                if (phenotype != null){
                    predicateList.add(criteriaBuilder.like(root.get("phenotype"),"%" + phenotype + "%"));
                }
                if (drug != null){
                    predicateList.add(criteriaBuilder.like(root.get("inst").as(String.class),"%" + drug + "%"));
                }

//                System.out.println(drug);
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