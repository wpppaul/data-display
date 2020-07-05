package org.wpp.frogdata.datadisplay.model;

import java.io.Serializable;
import lombok.Data;

/**
 * brush_face_data
 * @author 
 */
@Data
public class BrushFaceData implements Serializable {
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

    private static final long serialVersionUID = 1L;
}