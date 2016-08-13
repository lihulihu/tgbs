package com.jlc.dao;

import java.util.List;
import java.util.Map;

import com.jlc.bean.Announcement;

public interface AnnouncementMapper {
    int deleteByPrimaryKey(Integer announcementId) throws Exception;

    int insert(Announcement record) throws Exception;

    int insertSelective(Announcement record) throws Exception;

    Announcement selectByPrimaryKey(Integer announcementId) throws Exception;
    
    List<Announcement> selectList(Map<String,Object>map) throws Exception;

    int updateByPrimaryKeySelective(Announcement record) throws Exception;

    int updateByPrimaryKey(Announcement record) throws Exception;
}