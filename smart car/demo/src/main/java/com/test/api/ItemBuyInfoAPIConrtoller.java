package com.test.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.test.mapper.CarMasterMapper;
import com.test.mapper.ItemBuyInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemBuyInfoAPIConrtoller {
    @Autowired CarMasterMapper c_mapper;
    @Autowired ItemBuyInfoMapper mapper;
    @GetMapping("/item/gen")
    public Map<String, Object> getItemGen(@RequestParam String gen, @RequestParam String item){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        Integer cnt = mapper.selectItemBuyCountByGen(gen, item);

        resultMap.put("statsu", true);
        resultMap.put("cnt",cnt);

        return resultMap;
    }
    @GetMapping("/item/age")
    public Map<String, Object> getItemAeg(@RequestParam String item){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<Integer> ageList = mapper.selectItemBuyCountAgeList(item);

        resultMap.put("statsu", true);
        resultMap.put("ageList", ageList);

        return resultMap;
    }
    @GetMapping("/item/score")
    public Map<String, Object> getItemScoer(@RequestParam String item){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        Double score = mapper.selectItemAvgScore(item);

        resultMap.put("statsu", true);
        resultMap.put("score",score);

        return resultMap;
    }
    @GetMapping("/item/region")
    public Map<String, Object> getItemRefion(@RequestParam String item){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<String> regions = c_mapper.selectRegions();
        List<Integer> itemCounts = new ArrayList<Integer>();
        for(String r : regions) {
            Integer cnt = mapper.selectItemBuyCountByRegion(r, item);
            itemCounts.add(cnt);
        }

        resultMap.put("statsu", true);
        resultMap.put("regions", regions);
        resultMap.put("item_counts", itemCounts);
        // resultMap.put("region", region);
        // resultMap.put("cnt",cnt);

        return resultMap;
    }

    @GetMapping("/item/gen_all")
    public Map<String, Object> getItemgenAll(@RequestParam String item){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<Integer> cnt = mapper.selectItemCntGroupByGen(item);

        resultMap.put("statsu", true);
        resultMap.put("cnt",cnt);

        return resultMap;
    }
}
