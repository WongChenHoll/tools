package com.jason.test.project.service.impl;

import com.jason.test.TestApplication;
import com.jason.test.project.dao.RequestLogDao;
import com.jason.test.project.dao.TestDao;
import com.jason.test.project.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

/**
 * @author WangChenHol
 * @date 2021-12-1 16:52
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(classes = {TestApplication.class})
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private TestDao testDao;
    @Mock
    private RequestLogServiceImpl requestLogService;
    @Mock
    private RequestLogDao requestLogDao;

    @Test
    public void testlogin() {
        User user = new User();
        user.setUserName("hahaha");
        user.setAddress("北京");
        user.setAge(33);
        when(testDao.queryUserById("28DF4AF1042F4B4F9D2B38A6BD476C23")).thenReturn(user);
        when(userServiceImpl.login("28DF4AF1042F4B4F9D2B38A6BD476C23")).thenReturn(user);
    }

    @Test
    public void testaddUser() {
        User user = new User();
        user.setUserName("hahaha");
        user.setAddress("北京");
        user.setAge(33);
        when(testDao.addUser(user)).thenReturn(1);
        when(requestLogService.addLog("/jason/test-jason/test")).thenReturn(1);
        when(userServiceImpl.addUser(user)).thenReturn(1);
    }

}
