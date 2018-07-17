package cn.gzticc.bgmanagertemplate.framework.service.impl;

import cn.gzticc.bgmanagertemplate.base.constant.BaseConstant;
import cn.gzticc.bgmanagertemplate.base.enums.BaseEnum;
import cn.gzticc.bgmanagertemplate.base.exception.BaseException;
import cn.gzticc.bgmanagertemplate.framework.dao.ISysAdminMapper;
import cn.gzticc.bgmanagertemplate.framework.pojo.SysAdmin;
import cn.gzticc.bgmanagertemplate.framework.service.ISysAdminService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zcp
 * @since 2018-07-17
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<ISysAdminMapper, SysAdmin> implements ISysAdminService {
    @Override
    public SysAdmin loginVerify(String userName, String password) {
        SysAdmin entity = new SysAdmin();
        entity.setUserName(userName);
        SysAdmin sysAdmin = baseMapper.selectOne(entity);
        if(null == sysAdmin) {
            throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_EXIST);
        }
        if(userName.equals(sysAdmin.getUserName())) {
            if(password.equals(sysAdmin.getPassword())) {
                if(!BaseConstant.USE_STATUS_OK.equals(sysAdmin.getUseStatus())) {
                    throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_USE);
                }
            }else{
                throw new BaseException(BaseEnum.LOGIN_PWD_FAIL);
            }
        }
        return sysAdmin;
    }
}
