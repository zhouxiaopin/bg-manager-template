package cn.gzticc.bgmanagertemplate.framework.handle;


import cn.gzticc.bgmanagertemplate.base.constant.BaseConstant;
import cn.gzticc.bgmanagertemplate.base.enums.BaseEnum;
import cn.gzticc.bgmanagertemplate.base.exception.BaseException;
import cn.gzticc.bgmanagertemplate.base.exception.TipException;
import cn.gzticc.bgmanagertemplate.base.pojo.BaseResult;
import cn.gzticc.bgmanagertemplate.framework.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理器
 * Created by Administrator on 2017/11/20.
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult handle(Exception e) {
        //以后要添加判断是否是ajax请求
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultUtils.baseResult(baseException.getCode(), baseException.getMessage());
        }else if (e instanceof TipException) {
            TipException tipException = (TipException) e;
            return ResultUtils.baseResult(tipException.getCode(), tipException.getMessage());
        }else if (e instanceof HttpRequestMethodNotSupportedException) {
            logger.error("【请求方式异常】{}", e);
            return ResultUtils.baseResult(BaseConstant.EXCEPTION_CODE, e.getMessage());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtils.baseResult(BaseEnum.SYS_UNKNOWN_ERROR.getCode(), BaseEnum.SYS_UNKNOWN_ERROR.getMsg());
        }
    }
}
