package com.jlc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.bean.PublicParam;
import com.jlc.commons.utils.PageInfo;
import com.jlc.dao.PublicParamMapper;
import com.jlc.service.PublicParamService;

@Service
public class PublicParamServiceImpl implements PublicParamService{
	@Autowired
	private PublicParamMapper publicParamMapper;
	public int deleteByPrimaryKey(Integer publicId) throws Exception{
		return publicParamMapper.deleteByPrimaryKey(publicId);
	}

    public int insert(PublicParam record) throws Exception{
    	return publicParamMapper.insert(record);
    }

    public int insertSelective(PublicParam record) throws Exception{
    	return publicParamMapper.insertSelective(record) ;
    }

    public PublicParam selectByPrimaryKey(Integer publicId) throws Exception{
    	return publicParamMapper.selectByPrimaryKey(publicId);
    }

    public int updateByPrimaryKeySelective(PublicParam record) throws Exception{
    	return publicParamMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PublicParam record) throws Exception{
    	return publicParamMapper.updateByPrimaryKey(record);
    }
    
    public List<PublicParam> selectByParam(Map<String,Object> map) throws Exception{
    	return publicParamMapper.selectByParam(map);
    }
    public void selectParamPage(PageInfo pageInfo) throws Exception{
    	pageInfo.setRows(publicParamMapper.findParamPageCondition(pageInfo));
        pageInfo.setTotal(publicParamMapper.findParamPageCount(pageInfo));
    }
}
