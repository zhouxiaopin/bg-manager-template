package cn.gzticc.bgmanagertemplate.framework.service;

import cn.gzticc.bgmanagertemplate.framework.pojo.SysAdmin;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zcp
 * @since 2018-07-17
 */
public interface ISysAdminService extends IService<SysAdmin> {
    SysAdmin loginVerify(String userName, String password);
}
