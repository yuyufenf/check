package com.muxingzhe.check.main.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/1 16:06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mxz_history_log")
public class HistoryPO {

    @TableId(value = "id")
    private String historyId;

    @TableField(value = "mac")
    private String macId;

    @TableField(value = "ipv4Address")
    private String ipAddress;

    @TableField(value = "logDate", fill = FieldFill.INSERT_UPDATE)
    private Date logDate;

}
