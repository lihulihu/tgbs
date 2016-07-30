package com.jlc.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jlc.bean.tree;

public interface TreeService {
	public List<tree> selectByParam(Map map);
}
