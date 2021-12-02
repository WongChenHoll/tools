package com.jason.test.project.service.impl;

import com.jason.test.TestApplication;
import com.jason.test.common.exception.JsonException;
import com.jason.test.project.dao.HotFilmDao;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class})
class HotFilmServiceImplTest {
    @InjectMocks
    private HotFilmServiceImpl hotFilmService;

    @Mock
    private HotFilmDao hotFilmDao;
    @Mock
    private RedisTemplate redisTemplate;

    @Test
    void addHotFilm() throws JsonException {
//        when(hotFilmService.addHotFilm()).thenReturn(0);
    }

    @Test
    void addHotFilmThread() {
    }

    @Test
    void addHotFilmExecutorThread() {
    }

    @Test
    void queryList() {
    }

    @Test
    void add() {
    }
}