package com.jason.base.utils;

import com.jason.base.exception.ServiceException;

import java.io.*;

/**
 * @author WongChenHoll
 * @description
 * @date 2023-1-4 星期三 18:08
 **/
public class BeanUtil {

    private BeanUtil() {
    }

    /**
     * 深克隆对象
     *
     * @param t   被克隆的对象必须实现{@link Serializable} 接口
     * @param <T> 对象类型
     * @return 新对象
     * @throws ServiceException 异常
     */
    public static <T> T cloneDeep(T t) throws ServiceException {
        if (!(t instanceof Serializable)) {
            throw ServiceException.paramsException("非Serializable类型不允许深克隆");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(t);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object o = ois.readObject();
            return (T) o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
