package cn.ac.ngdc.cellular_response.service;

import cn.ac.ngdc.cellular_response.dao.DrugDAO;
import cn.ac.ngdc.cellular_response.entity.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugService {
    @Autowired
    DrugDAO drugDAO;
    public Drug findByName(String name){
        return drugDAO.findByName(name);
    }
}
