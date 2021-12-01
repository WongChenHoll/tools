package com.jason.test.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jason.test.common.exception.JsonException;
import com.jason.test.project.model.HotFilm;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ChenHol.Wong
 * @create 2020/8/1 10:26
 */
public class ReadJsonFile {

    private static final Logger logger = LoggerFactory.getLogger(ReadJsonFile.class);

    public static List<HotFilm> read(File file) throws JsonException {
        List<HotFilm> list = new ArrayList<>();
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine = bufferedReader.readLine();
            JSONArray jsonArray = JSON.parseArray(readLine);
            for (int i = 0; i < jsonArray.size(); i++) {
                Object o = jsonArray.get(i);
                String jsonString = JSONObject.toJSONString(o);
                JSONObject jsonObject = JSON.parseObject(jsonString);

                HotFilm hotFilm = buildHotFile(jsonObject);
                list.add(hotFilm);
            }
        } catch (Exception e) {
            logger.error("读取文件异常：", e);
            throw new JsonException(e);
        }
        return list;
    }

    private static HotFilm buildHotFile(JSONObject jsonObject) throws ParseException {
        String filmName = jsonObject.getString("影名");
//        logger.info("电影名：{}", filmName);
        String director = jsonObject.getString("导演");
        String screenWriter = jsonObject.getString("编剧");
        String majorStar = jsonObject.getString("主演");
        String filmCountry = jsonObject.getString("制片国家地区");
        String filmLanguage = jsonObject.getString("语言");
        String filmLength = jsonObject.getString("片长");
        filmLength = getFileLength(filmLength);
        String anotherName = jsonObject.getString("又名");
        BigDecimal filmScore = jsonObject.getBigDecimal("评分");
        BigDecimal starLevel = jsonObject.getBigDecimal("星级");
        String fiveStar = jsonObject.getString("五星");
        String fourStar = jsonObject.getString("四星");
        String threeStar = jsonObject.getString("三星");
        String twoStar = jsonObject.getString("二星");
        String oneStar = jsonObject.getString("一星");
        String synopsis = jsonObject.getString("剧情简介");
        String filmDoubanUrl = jsonObject.getString("页面网址");
        String releaseDate = jsonObject.getString("上映日期");
        String filmType = jsonObject.getString("类型");

        HotFilm hotFilm = new HotFilm();
        hotFilm.setAnotherName(subStr(dropXieGang(anotherName), 200, 198));
        hotFilm.setDirector(subStr(director, 200, 198));
        hotFilm.setFilmCountry(subStr(dropXieGang(filmCountry), 100, 99));
        hotFilm.setFilmDoubanUrl(filmDoubanUrl);
        hotFilm.setFilmLanguage(subStr(filmLanguage, 10, 9));
        hotFilm.setFilmLength(getFilmLength(filmLength));
        hotFilm.setFilmName(StringUtils.isBlank(filmName) ? "未知" : filmName);
        hotFilm.setFilmScore(filmScore);
        hotFilm.setFilmType(subStr(dropXieGang(filmType), 100, 99));
        hotFilm.setFiveStar(getStar(fiveStar));
        hotFilm.setFourStar(getStar(fourStar));
        hotFilm.setThreeStar(getStar(threeStar));
        hotFilm.setTwoStar(getStar(twoStar));
        hotFilm.setOneStar(getStar(oneStar));
        hotFilm.setMajorStar(subStr(dropXieGang(majorStar), 1000, 999));
        hotFilm.setReleaseDate(getReleaseDate(releaseDate));
        hotFilm.setScreenWriter(subStr(dropXieGang(screenWriter), 200, 198));
        hotFilm.setStarLevel(starLevel);
        hotFilm.setSynopsis(synopsis);

        return hotFilm;
    }

    public static String dropXieGang(String string) {
        if (!string.contains("/")) {
            return string;
        }
        String[] split = string.split("/");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            sb.append(str.trim()).append("_");
        }
        String s = sb.toString();
        return s.substring(0, s.length() - 1);
    }

    private static String subStr(String sub, int length, int end) {
        int byteLength = sub.getBytes().length;
        if (byteLength >= length) {
            return sub.substring(0, length / 3);
        }
        return sub;
    }

    private static BigDecimal getFilmLength(String filmLength) {
        try {
            return StringUtils.isBlank(filmLength) ? null : new BigDecimal(filmLength);

        } catch (Exception e) {
//            logger.error("电影时长转换异常：{}", e.getMessage());
            return null;
        }
    }

    private static String getFileLength(String filmLength) {
        try {
            if (StringUtils.isNotBlank(filmLength)) {
                if (filmLength.contains("分")) {
                    int min = filmLength.indexOf("分");
                    String substring = filmLength.substring(0, min);
                    switch (substring.length()) {
                        case 3:
                            filmLength = filmLength.substring(min - 3, min);
                            break;
                        case 2:
                            filmLength = filmLength.substring(min - 2, min);
                            break;
                        case 1:
                            filmLength = filmLength.substring(min - 1, min);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("[{}]电影时长处理错误：", filmLength, e);
            filmLength = "0";
        }

        return filmLength;
    }

    private static Date getReleaseDate(String releaseDate) throws ParseException {
        if (StringUtils.isBlank(releaseDate)) {
            return null;
        }
        switch (releaseDate.length()) {
            case 4:
                releaseDate = releaseDate + "-01-01";
                break;
            case 7:
                releaseDate = releaseDate + "-01";
                break;
            case 10:
                if (releaseDate.contains("(")) {
                    releaseDate = releaseDate.substring(0, 4) + "-01-01";
                } else {
                    releaseDate = releaseDate.substring(0, 10);
                }
                break;
            default:
                return null;
        }
        return DateUtils.parseDate(releaseDate, "yyyy-MM-dd");
    }

    private static BigDecimal getStar(String fiveStar) {
        return StringUtils.isBlank(fiveStar) ? null : new BigDecimal(fiveStar.substring(0, fiveStar.length() - 1));
    }

    public static void main(String[] args) throws JsonException {

    }
}
