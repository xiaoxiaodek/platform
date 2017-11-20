package com.www.platform.service.other;

import org.springframework.stereotype.Service;

/**
 * Created by upsmart on 17-11-14.
 *
 * @author wss
 * @version 0.0
 * @desc
 * @modified by  下午1:34
 */
@Service public interface FileService {
    int addFile(String fname, int comid, String flocal, String fsummary);

    String editFile(String fname, int comid, String flocal, String fsummary);

}
