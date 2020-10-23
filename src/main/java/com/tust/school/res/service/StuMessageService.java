package com.tust.school.res.service;

import com.tust.school.res.domain.dto.message.StuMessageAddParam;
import com.tust.school.res.domain.dto.message.StuMessageParam;
import com.tust.school.res.domain.dto.message.StuMessageResult;

import java.util.List;

/**
 * @author luoxiaofang
 * @create 2020-05-24 16:43
 */
public interface StuMessageService {

    /**
     * 新增留言
     *
     * @param stuMessageAddParam 新增参数
     * @return 留言id
     */
    Integer addStuMessage(StuMessageAddParam stuMessageAddParam);

    /**
     * 查询留言列表
     * @param stuMessageParam 查询条件
     * @return 留言列表
     */
    List<StuMessageResult> listStuMessage(StuMessageParam stuMessageParam);
}
