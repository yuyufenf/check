package com.muxingzhe.check.main.service;

import com.muxingzhe.check.main.entity.form.CheckForm;

/**
 * @author kampf
 * @version 1.0
 * @description:
 * @date 2024/6/1 15:59
 */

public interface HistoryService {

    void saveCheckLog(CheckForm form, String ipAddr);
}
