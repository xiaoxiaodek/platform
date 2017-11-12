package com.www.platform.service;

import com.www.platform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by upsmart on 17-8-4.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午2:31
 */
public interface LoginService {
   String login(Map<String,Object> map);

}
