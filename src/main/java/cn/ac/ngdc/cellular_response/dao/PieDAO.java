package cn.ac.ngdc.cellular_response.dao;

import cn.ac.ngdc.cellular_response.entity.Pie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PieDAO extends JpaRepository<Pie,Integer> {

    public List<Pie> findByDatasetid(String dataset);

}
