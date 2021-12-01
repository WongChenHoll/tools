package com.jason.test.project.dao;

import com.jason.test.common.bean.PageQuery;
import com.jason.test.project.model.HotFilm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ChenHol.Wong
 * @create 2020/8/1 12:44
 */
@Mapper
public interface HotFilmDao {

    int addHotFilms(List<HotFilm> list);

    List<HotFilm> queryHotFilmList(PageQuery pageQuery);

    Integer queryTotal();
}
