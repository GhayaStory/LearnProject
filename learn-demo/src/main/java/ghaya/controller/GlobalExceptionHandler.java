package ghaya.controller;

import ghaya.common.CommonResp;
import ghaya.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     * @param e
     * @return
     * @throws Exception
     */
    @ResponseBody
    @ExceptionHandler( BusinessException.class )
    public CommonResp handleBusinessException (BusinessException e ) throws Exception {
        log.error("BusinessException error", e);
        return CommonResp.create(e.getResultCode());
    }


}
