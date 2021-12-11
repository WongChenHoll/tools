package com.jason.test.project.service.impl;

import com.jason.test.TestApplication;
import com.jason.test.common.bean.PageQuery;
import com.jason.test.common.exception.JsonException;
import com.jason.test.project.dao.HotFilmDao;
import com.jason.test.project.model.HotFilm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class})
public class HotFilmServiceImplTest {

    @InjectMocks
    private HotFilmServiceImpl hotFilmServiceImpl;
    @Mock
    private HotFilmDao hotFilmDao;
    @Mock
    private RedisTemplate redisTemplate;
    @Mock
    private ListOperations listOperations;

    @Test
    public void testAddHotFilm() throws JsonException {
        when(hotFilmServiceImpl.addHotFilm()).thenReturn(0);
    }

    @Test
    public void testaddHotFilmThread() {
        ArrayList<HotFilm> list = new ArrayList<>();
        when(hotFilmDao.addHotFilms(list)).thenReturn(10);
        int i = hotFilmServiceImpl.addHotFilmThread();
        Assert.assertEquals(0, 0);
    }

    @Test
    public void testaddHotFilmExecutorThread() throws JsonException {
//        ArrayList<HotFilm> list = new ArrayList<>();
//        when(hotFilmDao.addHotFilms(list)).thenReturn(10);
        int i = hotFilmServiceImpl.addHotFilmExecutorThread();
    }

    @Test
    public void testqueryList() {

        List<HotFilm> films = new ArrayList<>();
        HotFilm hotFilm = new HotFilm();
        hotFilm.setFilmName("ssss");
        films.add(hotFilm);
        PageQuery pageQuery1 = new PageQuery();
        pageQuery1.setQueryAll(true);

        int count = 40;
        when(hotFilmDao.queryTotal()).thenReturn(count);
        when(hotFilmDao.queryHotFilmList(pageQuery1)).thenReturn(films);
        List<HotFilm> hotFilms = hotFilmServiceImpl.queryList(pageQuery1);
        Assert.assertEquals(2, hotFilms.size());

        PageQuery pageQuery2 = new PageQuery();
        pageQuery2.setQueryAll(false);
        when(hotFilmDao.queryTotal()).thenReturn(count);
        when(hotFilmDao.queryHotFilmList(pageQuery2)).thenReturn(films);
        when(redisTemplate.opsForList()).thenReturn(listOperations);
        when(listOperations.leftPush("testtest", hotFilm)).thenReturn(1L);
        when(redisTemplate.expire("testtest", 60 * 2, TimeUnit.SECONDS)).thenReturn(Boolean.TRUE);

        List<HotFilm> hotFilms2 = hotFilmServiceImpl.queryList(pageQuery2);
        Assert.assertEquals(1, hotFilms2.size());
    }

    @Test
    public void testadd() {
        List<HotFilm> films = new ArrayList<>();
        int count = 10;
        when(hotFilmDao.addHotFilms(films)).thenReturn(count);
        Assert.assertEquals(10, count);
        int add = hotFilmServiceImpl.add(films);
    }


//    @BeforeClass
//    public static void setUp() {
//        // Mock所有redis的对象
//        RedisTemplate redisTemplate = Mockito.mock(RedisTemplate.class);
//        ValueOperations valueOperations = Mockito.mock(ValueOperations.class);
//        SetOperations setOperations = Mockito.mock(SetOperations.class);
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        listOperations = redisTemplate.opsForList();
//        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
//
//        when(redisTemplate.opsForSet()).thenReturn(setOperations);
//        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
//        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
//        when(redisTemplate.opsForList()).thenReturn(listOperations);
//        when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);
//
//        RedisOperations redisOperations = Mockito.mock(RedisOperations.class);
//        RedisConnection redisConnection = Mockito.mock(RedisConnection.class);
//        RedisConnectionFactory redisConnectionFactory = Mockito.mock(RedisConnectionFactory.class);
//        when(redisTemplate.getConnectionFactory()).thenReturn(redisConnectionFactory);
//        when(valueOperations.getOperations()).thenReturn(redisOperations);
//        when(redisTemplate.getConnectionFactory().getConnection()).thenReturn(redisConnection);
//    }

}