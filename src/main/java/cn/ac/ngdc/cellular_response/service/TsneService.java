package cn.ac.ngdc.cellular_response.service;

import cn.ac.ngdc.cellular_response.dao.TsneDAO;
import cn.ac.ngdc.cellular_response.entity.Tsne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TsneService {
    @Autowired
    TsneDAO tsneDAO;
    public List<Tsne> findByName(String name){
        List<Tsne> tsneList = tsneDAO.findByName(name);
        return tsneList;
    }
}
