package com.jason.test.project.service.impl;

import com.jason.test.TestApplication;
import com.jason.test.common.bean.PageQuery;
import com.jason.test.common.exception.JsonException;
import com.jason.test.project.dao.HotFilmDao;
import com.jason.test.project.model.HotFilm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = {TestApplication.class})
class HotFilmServiceImplTest {
    @InjectMocks
    private HotFilmServiceImpl hotFilmService;
    @Mock
    private HotFilmDao hotFilmDao;
    @Mock
    private RedisTemplate redisTemplate;
    @Mock
    private ListOperations listOperations;

    @Before
    public void setUp() {
        // Mock所有redis的对象
        RedisTemplate redisTemplate = Mockito.mock(RedisTemplate.class);
        ValueOperations valueOperations = Mockito.mock(ValueOperations.class);
        SetOperations setOperations = Mockito.mock(SetOperations.class);
        HashOperations hashOperations = redisTemplate.opsForHash();
        listOperations = redisTemplate.opsForList();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        when(redisTemplate.opsForSet()).thenReturn(setOperations);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(redisTemplate.opsForList()).thenReturn(listOperations);
        when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);

        RedisOperations redisOperations = Mockito.mock(RedisOperations.class);
        RedisConnection redisConnection = Mockito.mock(RedisConnection.class);
        RedisConnectionFactory redisConnectionFactory = Mockito.mock(RedisConnectionFactory.class);
        when(redisTemplate.getConnectionFactory()).thenReturn(redisConnectionFactory);
        when(valueOperations.getOperations()).thenReturn(redisOperations);
        when(redisTemplate.getConnectionFactory().getConnection()).thenReturn(redisConnection);
    }

    @Test
    void addHotFilm() throws JsonException {
        when(hotFilmService.addHotFilm()).thenReturn(0);
    }

    @Test
    void addHotFilmThread() {
        when(hotFilmService.addHotFilmThread()).thenReturn(0);
    }

    @Test
    void addHotFilmExecutorThread() throws JsonException {
        when(hotFilmService.addHotFilmExecutorThread()).thenReturn(0);
    }

    @Test
    void queryList() {
        List<HotFilm> films = new ArrayList<>();
        HotFilm hotFilm = new HotFilm();
        films.add(hotFilm);
        PageQuery pageQuery1 = new PageQuery();
        pageQuery1.setQueryAll(true);

        int count = 40;
        when(hotFilmDao.queryTotal()).thenReturn(count);
        when(hotFilmDao.queryHotFilmList(pageQuery1)).thenReturn(films);
        List<HotFilm> hotFilms = hotFilmService.queryList(pageQuery1);
        Assertions.assertEquals(2, hotFilms.size());

        PageQuery pageQuery2 = new PageQuery();
        pageQuery2.setQueryAll(false);
        when(hotFilmDao.queryTotal()).thenReturn(count);
        when(hotFilmDao.queryHotFilmList(pageQuery2)).thenReturn(films);

        when(redisTemplate.opsForList()).thenReturn(listOperations);
        when(listOperations.leftPush("testtest", hotFilm)).thenReturn(1L);

        List<HotFilm> hotFilms2 = hotFilmService.queryList(pageQuery2);
        Assertions.assertEquals(1, hotFilms2.size());
    }

    @Test
    void add() {
        List<HotFilm> films = new ArrayList<>();
        int count = 0;
        when(hotFilmDao.addHotFilms(films)).thenReturn(count);
        int add = hotFilmService.add(films);
        Assertions.assertEquals(0, add);

        int count2 = 1;
        films.add(new HotFilm());
        when(hotFilmDao.addHotFilms(films)).thenReturn(count2);
        int add2 = hotFilmService.add(films);
        Assertions.assertEquals(1, add2);
    }
}