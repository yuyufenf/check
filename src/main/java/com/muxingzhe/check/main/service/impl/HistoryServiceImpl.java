package com.muxingzhe.check.main.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.muxingzhe.check.main.dao.HistoryMapper;
import com.muxingzhe.check.main.entity.form.CheckForm;
import com.muxingzhe.check.main.entity.po.HistoryPO;
import com.muxingzhe.check.main.service.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/1 15:59
 */
@Slf4j
@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private HistoryMapper historyMapper;

    private static Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    @Override
    public void saveCheckLog(CheckForm form, String ipAddr) {
        //先将json信息解密后制作成json（或者对象）
        String jsonStr =  Base64.decodeStr(form.getMsg());
        JSONObject json = JSONObject.parseObject(jsonStr);

        log.info("deviceCheck:{}", json.toString());

        historyMapper.insert(HistoryPO.builder()
                .historyId(snowflake.nextIdStr())
                .logDate(new Date())
                .macId(Base64.decodeStr(form.getId()))
                .ipAddress(ipAddr)
                .build());
    }
}
