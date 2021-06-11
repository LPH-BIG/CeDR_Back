package cn.ac.ngdc.cellular_response.dao;

import cn.ac.ngdc.cellular_response.entity.Association;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AssociationDAO extends PagingAndSortingRepository<Association,Integer>, JpaSpecificationExecutor<Association> {

}
