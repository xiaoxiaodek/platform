package com.www.platform.service.operateLog;

import com.www.platform.entity.Log;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:用户日记操作
 * @Date: Created by 下午12:10 on 17-11-15.
 * @Modified By:yy
 */
public interface OperateLogService {

    List<Log> selectByComid(int comid);
}
