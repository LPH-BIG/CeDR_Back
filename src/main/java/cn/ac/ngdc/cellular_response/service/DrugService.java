package cn.ac.ngdc.cellular_response.service;

import cn.ac.ngdc.cellular_response.dao.DrugDAO;
import cn.ac.ngdc.cellular_response.entity.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrugService {
    @Autowired
    DrugDAO drugDAO;
    public Optional<Drug> findById(Integer id){
        return drugDAO.findById(id);
    }

    public List<Drug> getAll(Integer pageIndex,Integer pageSize){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Drug> drugPage = drugDAO.findAll(pageable);

        List<Drug> drugList = drugPage.getContent();
        return drugList;
    }
}
