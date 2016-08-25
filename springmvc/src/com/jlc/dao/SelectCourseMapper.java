package com.jlc.dao;

import java.util.List;

import com.jlc.bean.selectCourse;
import com.jlc.commons.result.UserVo;
import com.jlc.commons.utils.PageInfo;

public interface SelectCourseMapper {
    int deleteByPrimaryKey(Long selectId);

    int insert(selectCourse record);

    int insertSelective(selectCourse record);

    selectCourse selectByPrimaryKey(Long selectId);

    int updateByPrimaryKeySelective(selectCourse record);

    int updateByPrimaryKey(selectCourse record);

}
