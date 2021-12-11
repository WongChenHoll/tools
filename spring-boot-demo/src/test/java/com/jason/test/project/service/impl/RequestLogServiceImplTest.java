package com.jason.test.project.service.impl;


import com.jason.test.TestApplication;
import com.jason.test.project.dao.RequestLogDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class RequestLogServiceImplTest {

    @InjectMocks
    private RequestLogServiceImpl requestLogService;

    @Mock
    private RequestLogDao requestLogDao;

    @Test
    public void addLog() {
        Mockito.when(requestLogDao.addRequestLog("/s/s")).thenReturn(12);
        int i = requestLogService.addLog("/s/s");
        Assert.assertEquals(12, i);
    }
}