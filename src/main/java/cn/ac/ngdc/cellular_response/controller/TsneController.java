package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.entity.Tsne;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import cn.ac.ngdc.cellular_response.service.TsneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TsneController {
    @Autowired
    TsneService tsneService;
    @CrossOrigin
    @GetMapping("/tsne")
    public Result getByName(@RequestParam(value = "name",required = true) String name) {
        Object tsne = tsneService.findByName(name);
        return ResultFactory.buildSuccessResult(tsne,null);
    }
}
