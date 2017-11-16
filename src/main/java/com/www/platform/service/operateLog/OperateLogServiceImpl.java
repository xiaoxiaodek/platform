package com.www.platform.service.operateLog;

import com.www.platform.dao.LogMapper;
import com.www.platform.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午12:11 on 17-11-15.
 * @Modified By:
 */

@Service
public class OperateLogServiceImpl implements OperateLogService{

    @Autowired
    private LogMapper logMapper;

    /**
     * @desc 通过公司id查找记录
     * @return List<Log>
     */
    @Override
    public List<Log> selectByComid(int comid) {

        List<Log> logs = logMapper.selectByComid(comid);
        return logs;
    }
}
