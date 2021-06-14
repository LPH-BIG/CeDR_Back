package cn.ac.ngdc.cellular_response.dao;

import cn.ac.ngdc.cellular_response.entity.Tsne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TsneDAO extends JpaRepository<Tsne,Integer> {

    public List<Tsne> findByName(String name);
}
