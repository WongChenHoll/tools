package com.jason.test.project.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jason.test.common.bean.PageQuery;
import com.jason.test.common.exception.JsonException;
import com.jason.test.common.utils.ReadJsonFile;
import com.jason.test.project.dao.HotFilmDao;
import com.jason.test.project.model.HotFilm;
import com.jason.test.project.service.HotFilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenHol.Wong
 * @create 2020/8/1 13:00
 */
@Service
public class HotFilmServiceImpl implements HotFilmService {
    private static final Logger logger = LoggerFactory.getLogger(HotFilmServiceImpl.class);

    @Autowired
    private HotFilmDao hotFilmDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public int addHotFilm() throws JsonException {
        File[] files = getFiles();
        int count = 0;
        for (File file : files) {
            List<HotFilm> list = ReadJsonFile.read(file);
            logger.info("{},总共[{}]条数据", file.getName(), list.size());
            count += add(list);
        }
        return count;
    }

    @Transactional
    @Override
    public int addHotFilmThread() throws JsonException {
        File[] files = getFiles();
        int count = 0;
        for (File file : files) {
            AddHotFilmThread target = new AddHotFilmThread(file, hotFilmDao);
            Thread thread = new Thread(target);
            thread.start();
            count += target.getCount();
        }
        return count;
    }

    @Transactional
    @Override
    public int addHotFilmExecutorThread() throws JsonException {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        File[] files = getFiles();
        int count = 0;
        for (File file : files) {
            AddHotFilmThread command = new AddHotFilmThread(file, hotFilmDao);
            executorService.execute(command);
            count += command.getCount();

        }
        return count;
    }

    @Override
    public List<HotFilm> queryList(PageQuery pageQuery) {
        List<HotFilm> pageList = new ArrayList<>();
        Integer total = hotFilmDao.queryTotal();
        pageQuery.setTatol(total);
        if (pageQuery.getQueryAll()) {
            if (total == 0) {
                return null;
            }
            pageQuery.setPagrNumTotal((total / pageQuery.getPageSize()) + 1);
            for (int i = 1; i < pageQuery.getPagrNumTotal(); i++) {
                pageQuery.setStart((i - 1) * pageQuery.getPageSize());
                pageQuery.setEnd(pageQuery.getPageSize() * i);
                pageQuery.setPageNum(i);
                List<HotFilm> list = hotFilmDao.queryHotFilmList(pageQuery);
                pageList.addAll(list);
                logger.info("查询的数据：{}", list.toString());
            }
        } else {
            pageQuery.setStart((pageQuery.getPageNum() - 1) * pageQuery.getPageSize());
            pageQuery.setEnd(pageQuery.getPageSize() * pageQuery.getPageNum());
            List<HotFilm> list = hotFilmDao.queryHotFilmList(pageQuery);
            pageList.addAll(list);
            if (redisTemplate.hasKey("testtest")) {
                redisTemplate.delete("testtest");
            }
            for (HotFilm film : pageList) {
                redisTemplate.opsForList().leftPush("testtest", film);
            }
            redisTemplate.expire("testtest", 60 * 2, TimeUnit.SECONDS);
            logger.info("当前时间：{}",new Date());
        }
        return pageList;
    }

    private File[] getFiles() {
        File folder = new File("E:\\资料\\hotFilm");
        return folder.listFiles();
    }

    public int add(List<HotFilm> list) {
        if (CollectionUtil.isEmpty(list)) {
            return 0;
        }
        return hotFilmDao.addHotFilms(list);
    }

    static class AddHotFilmThread implements Runnable {

        private File file;
        private int count;
        private HotFilmDao hotFilmDao;

        public AddHotFilmThread() {
        }

        public AddHotFilmThread(File file, HotFilmDao hotFilmDao) {
            this.file = file;
            this.hotFilmDao = hotFilmDao;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }


        @Override
        public void run() {
            try {
                List<HotFilm> read = ReadJsonFile.read(file);
                int fuile = hotFilmDao.addHotFilms(read);
                setCount(fuile);
            } catch (JsonException e) {
                logger.error("多线程入库异常：", e);
            }
        }

    }
}
