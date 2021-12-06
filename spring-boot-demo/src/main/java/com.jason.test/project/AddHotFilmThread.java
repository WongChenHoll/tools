package com.jason.test.project;

import com.jason.test.common.exception.JsonException;
import com.jason.test.common.utils.ReadJsonFile;
import com.jason.test.project.dao.HotFilmDao;
import com.jason.test.project.model.HotFilm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * @author WangChenHol
 * @date 2021-12-6 21:32
 **/
public class AddHotFilmThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(AddHotFilmThread.class);

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
