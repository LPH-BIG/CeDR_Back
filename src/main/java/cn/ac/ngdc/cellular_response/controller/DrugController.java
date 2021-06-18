package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.dao.DrugDAO;
import cn.ac.ngdc.cellular_response.entity.Drug;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultCode;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import cn.ac.ngdc.cellular_response.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DrugController {
    @Autowired
    DrugDAO drugDAO;

    @CrossOrigin
    @GetMapping("/drug")
    public Result findByName(@RequestParam("inst") String name){
        Drug drug = drugDAO.findByInst(name);
        if (null != drug){
            return ResultFactory.buildSuccessResult(drug,null);
        }else {
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null,null);
        }
    }
}
