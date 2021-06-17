package cn.ac.ngdc.cellular_response.dao;
import cn.ac.ngdc.cellular_response.entity.General;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GeneralDAO extends PagingAndSortingRepository<General,Integer>, JpaSpecificationExecutor<General> {

}
