package cn.gzticc.bgmanagertemplate.framework.controller;


import cn.gzticc.bgmanagertemplate.base.constant.BaseConstant;
import cn.gzticc.bgmanagertemplate.base.enums.BaseEnum;
import cn.gzticc.bgmanagertemplate.base.exception.BaseException;
import cn.gzticc.bgmanagertemplate.base.pojo.BaseResult;
import cn.gzticc.bgmanagertemplate.framework.pojo.SysAdmin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zcp
 * @since 2018-07-17
 */
@RestController
@RequestMapping("/sysAdmin")
public class SysAdminController {

    @GetMapping(value = "/initLogin")
    public ModelAndView initLogin(ModelAndView mv){
        mv.addObject("oprt","login");
        mv.setViewName("login");
        return mv;
    }
    @PostMapping(value = "/login")
    public BaseResult login(SysAdmin sysAdmin, HttpSession session) throws Exception{

        BaseResult baseResult = new BaseResult();
        try {
            Subject currentUser = SecurityUtils.getSubject();

            if (!currentUser.isAuthenticated()) {
                // 把用户名和密码封装为 UsernamePasswordToken 对象
                String userName = sysAdmin.getUserName();
                String password = sysAdmin.getPassword();
                UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//            token.setRememberMe(true);
                // 执行登录.
                currentUser.login(token);

                SysAdmin adminInfo = (SysAdmin) currentUser.getPrincipal();
//                currentUser.getSession().setAttribute("adminInfo",adminInfo);
                session.setAttribute("adminInfo",adminInfo);

                baseResult.setMsg(BaseConstant.LOGIN_SUCCESS_MSG);
            }

        }catch (UnknownAccountException uae) {
            throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_EXIST);
        }catch (LockedAccountException lae)  {
            throw new BaseException(BaseEnum.LOGIN_ADMIN_NO_USE);
        }catch (IncorrectCredentialsException lce){
            throw new BaseException(BaseEnum.LOGIN_PWD_FAIL);
        }catch (AuthenticationException ae) { // 所有认证时异常的父类.
            throw new BaseException(BaseEnum.LOGIN_FAIL);
        }
        return baseResult;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        session.setAttribute("adminInfo", null);
        session.invalidate();
        return "login";
    }
    @GetMapping(value = "/index")
    public ModelAndView index(ModelAndView mv){
        mv.setViewName("index");
        return mv;
    }
}

