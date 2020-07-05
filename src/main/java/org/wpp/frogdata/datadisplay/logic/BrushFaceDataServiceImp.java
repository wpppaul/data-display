package org.wpp.frogdata.datadisplay.logic;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.wpp.frogdata.datadisplay.common.param.BaseResponse;
import org.wpp.frogdata.datadisplay.common.code.ResultCode;
import org.wpp.frogdata.datadisplay.common.dto.BrushFaceDataDto;
import org.wpp.frogdata.datadisplay.common.utils.FileUtils;
import org.wpp.frogdata.datadisplay.common.utils.ParseExcelUtil;
import org.wpp.frogdata.datadisplay.common.utils.StringUtil;
import org.wpp.frogdata.datadisplay.dao.BrushFaceDataDao;
import org.wpp.frogdata.datadisplay.model.BrushFaceData;
import org.wpp.frogdata.datadisplay.service.BrushFaceDataService;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Pack:        org.wpp.frogdata.datadisplay.logic
 * File:        BrushFaceDataServiceImp
 * User:        wupp
 * CreateTime:  2020-07-04 16:40
 */
@Service
@Slf4j
public class BrushFaceDataServiceImp implements BrushFaceDataService {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private BrushFaceDataDao brushFaceDataDao;

    @Override
    public BaseResponse updateBrushFaceData(MultipartFile file) {

        try {
            List<String> list = new ArrayList<>();

            list.add("SBSN");
            list.add("GC");
            list.add("SHH");
            list.add("SHMC");
            list.add("SLZFBS");
            list.add("SLZFYHS");
            list.add("YSLJYDTS");
            list.add("TJRQ");

            ParseExcelUtil.setColumns(list);

            File excelFile = null;

            try {
                String path = System.getProperty("user.dir")+File.separator+"uploads"+File.separator;
                excelFile = FileUtils.multipartFileToFile(file,path);
            } catch (Exception e) {
                e.printStackTrace();
                return BaseResponse.fail(ResultCode.FILE_READ_FAIL);
            }

            String data = ParseExcelUtil.readExcel(excelFile,1,0);

            Gson gson = new Gson();

            List<Map<String,Object>> arrList = gson.fromJson(data, new TypeToken<List<Map<String, Object>>>() {}.getType());

            for (Map<String,Object> map: arrList) {

                if (map!=null&&map.get("SBSN")!=null&&!map.get("SBSN").equals("")){

                    threadPoolTaskExecutor.submit(new Runnable(){

                        @Override
                        public void run() {
                            List<BrushFaceData> brushFaceDataList = brushFaceDataDao.selectBySnAndStatisticsDate((String) map.get("SBSN"),(String) map.get("TJRQ"));
                            //统一SN号和统计日期，数据做更新操作
                            if (brushFaceDataList!=null&&brushFaceDataList.size()>0){
                                BrushFaceData brushFaceData = brushFaceDataList.get(0);
                                brushFaceData.setArea((String) map.get("GC"));
                                brushFaceData.setBusinessNumber((String) map.get("SHH"));
                                brushFaceData.setBusinessName((String) map.get("SHMC"));
                                brushFaceData.setBrushFaceNumber((String) map.get("SLZFBS"));
                                brushFaceData.setBrushFaceUserNumber((String) map.get("SLZFYHS"));
                                brushFaceData.setBrushFaceDayNumber((String) map.get("YSLJYDTS"));
                                brushFaceDataDao.updateByPrimaryKeySelective(brushFaceData);
                            }else {
                                BrushFaceData brushFaceData = new BrushFaceData();
                                brushFaceData.setId(StringUtil.generateId());
                                brushFaceData.setSnNumber((String) map.get("SBSN"));
                                brushFaceData.setArea((String) map.get("GC"));
                                brushFaceData.setBusinessNumber((String) map.get("SHH"));
                                brushFaceData.setBusinessName((String) map.get("SHMC"));
                                brushFaceData.setBrushFaceNumber((String) map.get("SLZFBS"));
                                brushFaceData.setBrushFaceUserNumber((String) map.get("SLZFYHS"));
                                brushFaceData.setBrushFaceDayNumber((String) map.get("YSLJYDTS"));
                                brushFaceData.setStatisticsDate((String) map.get("TJRQ"));
                                brushFaceDataDao.insertSelective(brushFaceData);
                            }

                        }
                        });
                    }
                }
            return BaseResponse.ok(ResultCode.REQUEST_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.fail(ResultCode.RESULT_FAIL);
        }
    }

    @Override
    public BaseResponse selectBrushFaceData(BrushFaceDataDto brushFaceDataDto) {
        try {
            String snNumber = brushFaceDataDto.getSnNumber();
            String statisticsDate = brushFaceDataDto.getStatisticsDate();
            int totalNumber = brushFaceDataDao.selectTotalBySnOrStatisticsDate(snNumber,statisticsDate);
            List<BrushFaceData> brushFaceDataList = brushFaceDataDao.selectBySnOrStatisticsDate(snNumber,statisticsDate,
                    (brushFaceDataDto.getPageInfo().getCurrentPage()-1)*brushFaceDataDto.getPageInfo().getPageSize(),brushFaceDataDto.getPageInfo().getPageSize());
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("size",totalNumber);
            resultMap.put("list",brushFaceDataList);
            return BaseResponse.ok(ResultCode.REQUEST_SUCCESS,resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResponse.fail(ResultCode.RESULT_FAIL);
        }
    }
}
