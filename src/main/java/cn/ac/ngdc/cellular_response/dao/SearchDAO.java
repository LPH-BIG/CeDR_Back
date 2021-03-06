package cn.ac.ngdc.cellular_response.dao;

import cn.ac.ngdc.cellular_response.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SearchDAO extends JpaRepository<Search,Integer> {

    List<Search> findByNameLike(String name);

//    @Query(value = "select s from Search s where s.type = ?1 and s.name like %?2%")
    List<Search> findSearchesByTypeAndNameIsLike(String type,String name);
}
