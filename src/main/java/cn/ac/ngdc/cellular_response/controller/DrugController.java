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

import java.util.List;
import java.util.Optional;

@RestController
public class DrugController {
    @Autowired
    DrugService drugService;

    @GetMapping("/drug")
    public Result findByName(@RequestParam("id") Integer id){
        Optional<Drug> drug = drugService.findById(id);
        if (null != drug){
            return ResultFactory.buildSuccessResult(drug);
        }else {
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null);
        }
    }
    @GetMapping("/alldrug")
    public Result findByName(@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize){
        List<Drug> drugs = drugService.getAll(pageIndex,pageSize);
        if (null != drugs){
            return ResultFactory.buildSuccessResult(drugs);
        }else {
            return ResultFactory.buildResult(ResultCode.NOT_FOUND,"please check the name",null);
        }
    }
}
