package com.jlc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.commons.annotation.DataSourceChange;
import com.jlc.dao.SlaveMapper;
import com.jlc.service.SlaveService;
@Service
public class SlaveServiceImpl implements SlaveService {

    @Autowired
    private SlaveMapper slaveMapper;

    @DataSourceChange(slave = true)
    public Integer count() {
        return slaveMapper.count();
    }


}
