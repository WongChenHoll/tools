package com.jason.test.common.utils;

import com.jason.test.TestApplication;
import com.jason.test.common.exception.JsonException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.ParseException;

/**
 * @author WangChenHol
 * @date 2021-12-7 21:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class ReadJsonFileTest {

    @Test
    public void testread() throws JsonException, ParseException {
        File folder = new File("D:\\data\\test-data\\hotFilm\\豆瓣热门电影1.json");
        ReadJsonFile.read(folder);
        ReadJsonFile.getReleaseDate("2021");
        ReadJsonFile.getReleaseDate("2021-01");
        ReadJsonFile.getReleaseDate("2021-01-0(");
        ReadJsonFile.getReleaseDate("2021-01-10");
    }
}
