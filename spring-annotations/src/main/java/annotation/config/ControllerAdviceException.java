package annotation.config;

import com.jason.base.exception.ServiceException;
import com.jason.base.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 全局异常处理
 *
 * @author ChenHol.Wong
 * @create 2021/9/26 16:47
 */
@ControllerAdvice
public class ControllerAdviceException {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdviceException.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<String> error(Exception e) {
        logger.error("异常：", e);
        return BaseResponse.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public BaseResponse<Object> paramValidError(Exception e) {
        logger.error("参数校验异常:", e);
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            BindingResult result = ex.getBindingResult();
            List<FieldError> errors = result.getFieldErrors();
            List<Map<String, String>> list = new ArrayList<>();
            for (FieldError error : errors) {
                Map<String, String> map = new HashMap<>();
                map.put("errorField", error.getField());
                map.put("errorMessage", error.getDefaultMessage());
                map.put("errorValue", Objects.requireNonNull(error.getRejectedValue()).toString());
                list.add(map);
            }
            return BaseResponse.error(list);
        } else {
            BindException ex = (BindException) e;
            BindingResult result = ex.getBindingResult();
            Map<String, String> map = new HashMap<>();
            ObjectError error = result.getGlobalError();
            if (error != null) {
                map.put("errorMessage", error.getDefaultMessage());
            }
            FieldError fieldError = result.getFieldError();
            if (fieldError != null) {
                map.put("errorField", fieldError.getField());
                map.put("errorMessage", fieldError.getDefaultMessage());
                map.put("errorValue", Objects.requireNonNull(fieldError.getRejectedValue()).toString());
            }
            return BaseResponse.error(map);
        }
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public BaseResponse<String> serviceError(ServiceException e) {
        logger.error("业务异常：", e);
        return BaseResponse.error(e.getMessage());
    }

}
