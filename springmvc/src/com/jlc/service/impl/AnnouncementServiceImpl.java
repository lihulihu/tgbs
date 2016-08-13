package com.jlc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.bean.Announcement;
import com.jlc.dao.AnnouncementMapper;
import com.jlc.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{
	@Autowired
	private AnnouncementMapper announcementMapper;
		public int deleteByPrimaryKey(Integer announcementId) throws Exception{
			return announcementMapper.deleteByPrimaryKey(announcementId);
		}

	    public int insert(Announcement record) throws Exception{
	    	return announcementMapper.insert(record);
	    }

	    public int insertSelective(Announcement record) throws Exception{
	    	return announcementMapper.insertSelective(record);
	    }

	    public Announcement selectByPrimaryKey(Integer announcementId) throws Exception{
	    	return announcementMapper.selectByPrimaryKey(announcementId);
	    }

	    public int updateByPrimaryKeySelective(Announcement record) throws Exception{
	    	return announcementMapper.updateByPrimaryKeySelective(record);
	    }

	    public int updateByPrimaryKey(Announcement record) throws Exception{
	    	return announcementMapper.updateByPrimaryKey(record);
	    }
	    public List<Announcement> selectList(Map<String,Object>map) throws Exception{
	    	return announcementMapper.selectList(map);
	    }
}
