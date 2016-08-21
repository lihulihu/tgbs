package com.jlc.dao;

import java.util.List;

import com.jlc.bean.Select;
import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;

public interface SelectCourseMapper {
    /**
     * 添加课程
     *
     * @param select
     * @return
     */
    int insert(Select select);

}
