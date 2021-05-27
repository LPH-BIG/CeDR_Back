package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.entity.Drug;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultCode;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import cn.ac.ngdc.cellular_response.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrugController {
    @Autowired
    DrugService drugService;

    @GetMapping("/api/drug")
    public Result findByName(@RequestParam("name") String name){
        Drug drug = drugService.findByName(name);
        if (null != drug){
            return ResultFactory.buildSuccessResult(drug);
        }else {
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null);
        }
    }
}