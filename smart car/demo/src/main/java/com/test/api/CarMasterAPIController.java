package com.test.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.test.mapper.CarMasterMapper;
import com.test.mapper.SmartCarMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarMasterAPIController {
    @Autowired CarMasterMapper mapper;
    @GetMapping("/car/master/cnt")
    public Map<String, Object> getCarMasterCount(@RequestParam @Nullable String region) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<Integer> listCnt = null;
        if(region == null || region.equals("all")){
            listCnt = mapper.selectAgesCount();
        }
        else {
            listCnt = mapper.selectAgesCountByRegion(region);
        }
        resultMap.put("status", true);
        resultMap.put("listCnt", listCnt);

        return resultMap;
    }
    @GetMapping("/car/year/cnt")
    public Map<String, Object> getCarYearCnt(@RequestParam @Nullable String region) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<Integer> list = null;
        if(region == null || region.equals("all")) {
            list = mapper.selectCarYearCount();
        }
        else {
            list = mapper.selectCarYearCountByRegion(region);
        }
        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
    }
}
