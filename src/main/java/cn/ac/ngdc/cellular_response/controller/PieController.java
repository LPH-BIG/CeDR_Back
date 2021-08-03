package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.dao.PieDAO;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PieController {
    @Autowired
    PieDAO pieDAO;

    @CrossOrigin
    @GetMapping("/pie")
    public Result getByName(@RequestParam(value = "dataset",required = true) String dataset){

        return ResultFactory.buildSuccessResult(pieDAO.findByDatasetid(dataset),null);
    }
}
