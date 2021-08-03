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
            Collections.shuffle(allxy);
//            调整点的数目
            int randomSeriesLength = allxy.size()/12;
            if (randomSeriesLength == 0){
                randomSeriesLength = 1;
            }
            List<Object> randomSeries = allxy.subList(0,randomSeriesLength);
            cell.put("data",randomSeries);
            maps.add(cell);
        }
//        System.out.println(tsneList.toString());
        return maps;
    }
}
