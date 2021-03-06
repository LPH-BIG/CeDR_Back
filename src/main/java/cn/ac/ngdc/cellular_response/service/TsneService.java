package cn.ac.ngdc.cellular_response.service;

import cn.ac.ngdc.cellular_response.dao.TsneDAO;
import cn.ac.ngdc.cellular_response.entity.Tsne;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TsneService {
    @Autowired
    TsneDAO tsneDAO;
    public Object findByName(String name){
        List<Tsne> tsneList = tsneDAO.findByDatasetid(name);
//        System.out.println(tsneList.size());
//        TODO://如果总细胞数小于5000，返回所有细胞，否则只返回其十分之一
        List<Object> maps = new ArrayList<>();
        HashSet<String> cellTypes = new HashSet<>();
        for (Tsne tsne : tsneList){
            cellTypes.add(tsne.getAnnotation());
        }
        for (String c : cellTypes){
            Map<String,Object> cell = new HashMap<>();
            cell.put("name",c);
            List<Object> allxy = new ArrayList<>();
            for (Tsne t : tsneList){
                List<Double> xy = new ArrayList<>();
                if (t.getAnnotation().equals(c)){
                    xy.add(t.getX());
                    xy.add(t.getY());
                    allxy.add(xy);
                }
            }
            if (tsneList.size() > 5000){
                Collections.shuffle(allxy);
//            调整点的数目
                int randomSeriesLength = allxy.size()/10;
                if (randomSeriesLength == 0){
                    randomSeriesLength = 1;
                }
                List<Object> randomSeries = allxy.subList(0,randomSeriesLength);
                cell.put("data",randomSeries);
                maps.add(cell);
            }else {
                cell.put("data",allxy);
                maps.add(cell);
            }

        }
//        System.out.println(tsneList.toString());
        return maps;
    }
}
