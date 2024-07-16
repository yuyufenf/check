package com.muxingzhe.check.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 * @author kampf
 * @date 2019/7/18 10:55
 */
public class DateUtils {

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String FULL_DATE_TIME = "1";


    private static final DateTimeFormatter NOMINAL_LOCAL_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 时间格式 yyyy-MM-dd
     */
    public static final String JUST_DATE = "2";
    /**
     * 时间格式 yyyy年MM月dd日
     */
    public static final String JUST_DATE_CHS = "3";
    /**
     * 时间格式 yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String FULL_DATE_TIME_CHS = "4";
    /**
     * DIY时间格式
     */
    public static final String DIYDATE = "5";

    /**
     * 生成时间格式类
     * @param dateType
     * @return
     */
    private static SimpleDateFormat  dateFormat(String dateType, String diyType){
        SimpleDateFormat sdf;
        switch (dateType){
            case "1" :
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case "2" :
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case "3" :
                sdf = new SimpleDateFormat("yyyy年MM月dd日");
                break;
            case "4" :
                sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            default:
                sdf = new SimpleDateFormat(diyType);
        }

        return sdf;
    }

    /**
     * 字符串转日期
     * @param time 需要转换的字符串时间
     * @param dateType 转换时间类型，用本类下的枚举
     * @param diyType 如果没有匹配的时间类型可以自行添加类型
     * @return
     */
    public static Date string2Date(String time, String dateType, String diyType){

        SimpleDateFormat sdf = dateFormat(dateType, diyType);

        try {
            Date date = sdf.parse(time);

            return date;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期转字符串
     * @param date 需要转换的时间格式日期
     * @param dateType 转换时间类型，用本类下的枚举
     * @param diyType 如果没有匹配的时间类型可以自行添加类型
     * @return
     */
    public static String date2String(Date date, String dateType, String diyType){
        SimpleDateFormat sdf = dateFormat(dateType, diyType);
        String time = sdf.format(date);
        return time;
    }

    /**
     * 获取两个时间之间的间隔时间值（根据不同间隔划分颗粒度）
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> timeListByInterval(String startTime, String endTime){
        LocalDateTime beginDate = localDateTime(startTime).withSecond(0);
        LocalDateTime endDate = localDateTime(endTime).withSecond(0);

        long dateLength = Duration.between(beginDate, endDate).toHours();

        List<String> timeList = new ArrayList<>();

        if(dateLength > 1){
            LocalDateTime currentTime = beginDate;
            while (currentTime.isBefore(endDate) || currentTime.isEqual(endDate)){
                timeList.add(currentTime.format(NOMINAL_LOCAL_DATE_TIME));
                if (2 <= dateLength && dateLength <= 6) {
                    //2-6小时内（包含6小时）10min维度
                    currentTime = currentTime.plusMinutes(10);
                } else if (7 <= dateLength && dateLength <= 24) {
                    //7-24小时内（包含24小时）30min维度
                    currentTime = currentTime.plusMinutes(30);
                } else if (25 <= dateLength && dateLength <= 120){
                    //1-5天内（包含5天）1hours维度
                    currentTime = currentTime.plusHours(1);
                } else if (121 <= dateLength && dateLength <= 240){
                    //6-10天内（包含10天）12hours维度
                    currentTime = currentTime.plusHours(12);
                } else if (dateLength >= 241){
                    //大于10天直接按照每日维度计算
                    currentTime = currentTime.plusDays(1);
                }
            }
        }

        return timeList;
    }

    private static LocalDateTime localDateTime(String date){
        return LocalDateTime.parse(date, NOMINAL_LOCAL_DATE_TIME);
    }

    private static String localDateToString(LocalDate localDate){
        return localDate.atStartOfDay().format(NOMINAL_LOCAL_DATE_TIME);
    }

    public static String getStringFromLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(NOMINAL_LOCAL_DATE_TIME);
    }
}
