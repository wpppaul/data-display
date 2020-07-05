package org.wpp.frogdata.datadisplay.service;

import org.springframework.web.multipart.MultipartFile;
import org.wpp.frogdata.datadisplay.common.param.BaseResponse;
import org.wpp.frogdata.datadisplay.common.dto.BrushFaceDataDto;

/**
 * Description:
 * Pack:        org.wpp.frogdata.datadisplay.service
 * File:        brushFaceDataService
 * User:        wupp
 * CreateTime:  2020-07-04 16:26
 */
public interface BrushFaceDataService {

    BaseResponse updateBrushFaceData(MultipartFile file);

    BaseResponse selectBrushFaceData(BrushFaceDataDto brushFaceDataDto);
}
