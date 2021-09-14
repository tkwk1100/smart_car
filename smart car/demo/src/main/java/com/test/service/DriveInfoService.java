package com.test.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.test.mapper.DriveInfoMapper;
import com.test.vo.DriveInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriveInfoService {
    @Autowired DriveInfoMapper mapper;
    public Map<String, Object> getDriveInfo() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<DriveInfoVO> list = mapper.selectDriveInfo();
        
        List<String> car_number_list = new ArrayList<String>();
        List<Integer> age_list = new ArrayList<Integer>();
        List<Double> spd_pedal_list = new ArrayList<Double>();
        List<Double> brk_pedal_list = new ArrayList<Double>();
        List<Double> spd_list = new ArrayList<Double>();

        for(DriveInfoVO vo : list) {
            car_number_list.add(vo.getCar_number());
            age_list.add(vo.getAge());
            spd_pedal_list.add(vo.getSpeed_pedal());
            brk_pedal_list.add(vo.getBreak_pedal());
            spd_list.add(vo.getSpeed());
        }
        resultMap.put("count", list.size());
        resultMap.put("car_number_list", car_number_list);
        resultMap.put("age_list", age_list);
        resultMap.put("spd_pedal_list", spd_pedal_list);
        resultMap.put("brk_pedal_list", brk_pedal_list);
        resultMap.put("spd_list", spd_list);

        return resultMap;
    }
}
