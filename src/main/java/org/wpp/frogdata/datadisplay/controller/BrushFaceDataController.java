package org.wpp.frogdata.datadisplay.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wpp.frogdata.datadisplay.common.param.BaseResponse;
import org.wpp.frogdata.datadisplay.common.dto.BrushFaceDataDto;
import org.wpp.frogdata.datadisplay.service.BrushFaceDataService;

import javax.annotation.Resource;

/**
 * Description:
 * Pack:        org.wpp.frogdata.datadisplay.controller
 * File:        BrushFaceDataController
 * User:        wupp
 * CreateTime:  2020-07-04 18:38
 */
@RestController
@RequestMapping("/brushFaceData")
public class BrushFaceDataController {

    @Resource
    private BrushFaceDataService brushFaceDataService;

    @PostMapping("/updateBrushFaceData")
    public BaseResponse updateBrushFaceData(@RequestParam("file")MultipartFile file){
        BaseResponse baseResponse = brushFaceDataService.updateBrushFaceData(file);
        return baseResponse;
    }

    @PostMapping("/selectBrushFaceData")
    public BaseResponse selectBrushFaceData(@RequestBody BrushFaceDataDto brushFaceDataDto){
        BaseResponse baseResponse = brushFaceDataService.selectBrushFaceData(brushFaceDataDto);
        return baseResponse;
    }
}
