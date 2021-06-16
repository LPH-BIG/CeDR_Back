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
    public Result getByName(@RequestParam(value = "name",required = true) String name){

        return ResultFactory.buildSuccessResult(pieDAO.findAllByDataset(name),null);
    }
}
