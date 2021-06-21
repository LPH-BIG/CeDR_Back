package cn.ac.ngdc.cellular_response.controller;

import cn.ac.ngdc.cellular_response.dao.SearchDAO;
import cn.ac.ngdc.cellular_response.entity.Search;
import cn.ac.ngdc.cellular_response.result.Result;
import cn.ac.ngdc.cellular_response.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    SearchDAO searchDAO;

    @CrossOrigin
    @GetMapping("/keyword")
    public Result getByName(@RequestParam(value = "name",required = true) String name){
        List<Search> searches = searchDAO.findByNameLike("%"+name+"%");
        if (searches!=null){
            return ResultFactory.buildSuccessResult(searches,null);
        }else {
            return ResultFactory.buildFailResult("please check the name");
        }
    }


    @CrossOrigin
    @GetMapping("/select")
    public List<Search> getByTypeName(@RequestParam(value = "type",required = true) String type,
                                      @RequestParam(value = "name",required = true) String name){
        return searchDAO.findSearchesByTypeAndNameIsLike(type,"%"+name+"%");
    }
}
