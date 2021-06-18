package cn.ac.ngdc.cellular_response.dao;

import cn.ac.ngdc.cellular_response.entity.Gene;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneDAO extends JpaRepository<Gene,Integer> {
    public Gene findBySymbol(String symbol);
}
