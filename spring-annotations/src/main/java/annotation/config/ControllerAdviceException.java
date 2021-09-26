package annotation.config;

import com.jason.base.exception.ServiceException;
import com.jason.base.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResponse<List<Map<String, String>>> paramValidError(MethodArgumentNotValidException e) {
        logger.error("参数校验异常:", e);
        BindingResult result = e.getBindingResult();
        List<FieldError> errors = result.getFieldErrors();
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < errors.size(); i++) {
            Map<String, String> map = new HashMap<>();
            FieldError fieldError = errors.get(i);
            map.put("errorField", fieldError.getField());
            map.put("errorMessage", fieldError.getDefaultMessage());
            list.add(map);
        }
        return BaseResponse.error(list);
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public BaseResponse<String> serviceError(ServiceException e) {
        logger.error("业务异常：", e);
        return BaseResponse.error(e.getMessage());
    }

}
