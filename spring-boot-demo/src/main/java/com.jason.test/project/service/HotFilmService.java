package com.jason.test.project.service;

import com.jason.test.common.bean.PageQuery;
import com.jason.test.common.exception.JsonException;
import com.jason.test.project.model.HotFilm;

import java.util.List;

/**
 * @author ChenHol.Wong
 * @create 2020/8/1 13:00
 */
public interface HotFilmService {
    int addHotFilm() throws JsonException;

    int addHotFilmThread() throws JsonException;

    int addHotFilmExecutorThread() throws JsonException;

    List<HotFilm> queryList(PageQuery pageQuery);
}
