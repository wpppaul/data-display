package org.wpp.frogdata.datadisplay.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wpp.frogdata.datadisplay.model.BrushFaceData;

import java.util.List;

@Mapper
public interface BrushFaceDataDao {
    int deleteByPrimaryKey(String id);

    int insert(BrushFaceData record);

    int insertSelective(BrushFaceData record);

    BrushFaceData selectByPrimaryKey(String id);

    List<BrushFaceData> select(BrushFaceData brushFaceData);

    List<BrushFaceData> selectBySnOrStatisticsDate(@Param("snNumber")String snNumber,
                                                   @Param("startTime")String startTime, @Param("endTime")String endTime,
                                                   @Param("startIndex")int startIndex, @Param("endIndex")int endIndex);

    int selectTotalBySnOrStatisticsDate(@Param("snNumber")String snNumber, @Param("startTime")String startTime, @Param("endTime")String endTime);

    int updateByPrimaryKeySelective(BrushFaceData record);

    int updateByPrimaryKey(BrushFaceData record);

    List<BrushFaceData> selectBySnAndStatisticsDate(@Param("snNumber")String snNumber, @Param("statisticsDate")String statisticsDate);
}