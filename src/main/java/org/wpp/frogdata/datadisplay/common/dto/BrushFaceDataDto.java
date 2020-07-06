package org.wpp.frogdata.datadisplay.common.dto;

import lombok.Data;
import org.wpp.frogdata.datadisplay.common.page.PageInfo;

/**
 * Description:
 * Pack:        org.wpp.frogdata.datadisplay.common.dto
 * File:        BrushFaceDataDto
 * User:        wupp
 * CreateTime:  2020-07-04 16:36
 */
@Data
public class BrushFaceDataDto {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 设备号
     */
    private String snNumber;

    /**
     * 地区
     */
    private String area;

    /**
     * 商户号
     */
    private String businessNumber;

    /**
     * 商户名称
     */
    private String businessName;

    /**
     * 刷脸笔数
     */
    private String brushFaceNumber;

    /**
     * 刷脸用户数
     */
    private String brushFaceUserNumber;

    /**
     * 有刷脸支付的天数
     */
    private String brushFaceDayNumber;

    /**
     * 统计日期
     */
    private String statisticsDate;

    /**
     * 查询开始日期
     */
    private String startTime;

    /**
     * 查询结束时间日期
     */
    private String endTime;

    /**
     * 分页数据
     */
    private PageInfo pageInfo;
}
