package cn.ac.ngdc.cellular_response.dao;

import cn.ac.ngdc.cellular_response.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugDAO extends JpaRepository<Drug,Integer> {
    Drug findByName(String name);
}
