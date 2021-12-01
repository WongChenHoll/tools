package com.jason.test.project.model;

import com.jason.test.common.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ChenHol.Wong
 * @create 2020/8/1 10:24
 */
public class HotFilm extends BaseModel {


    private Date bizDate; // 业务日期

    private String deleteFlag; // 删除标识

    private String filmName; // 电影名称

    private String director; // 导演

    private String screenWriter; // 编剧

    private String majorStar; // 主演

    private String filmCountry; // 发行国家地区

    private String filmLanguage; // 语言

    private BigDecimal filmLength; // 片长，电影时长

    private String anotherName; // 别名

    private BigDecimal filmScore; // 评分

    private Date releaseDate; // 发行日期

    private String filmType; // 电影类型

    private String synopsis; // 简介

    private String filmDoubanUrl; // 电影爬取网址

    private BigDecimal starLevel; // 星级

    private BigDecimal oneStar; // 一星占比

    private BigDecimal twoStar; // 二星占比

    private BigDecimal threeStar; // 三星占比

    private BigDecimal fourStar; // 四星占比

    private BigDecimal fiveStar; // 五星占比

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getScreenWriter() {
        return screenWriter;
    }

    public void setScreenWriter(String screenWriter) {
        this.screenWriter = screenWriter;
    }

    public String getMajorStar() {
        return majorStar;
    }

    public void setMajorStar(String majorStar) {
        this.majorStar = majorStar;
    }

    public String getFilmCountry() {
        return filmCountry;
    }

    public void setFilmCountry(String filmCountry) {
        this.filmCountry = filmCountry;
    }

    public String getFilmLanguage() {
        return filmLanguage;
    }

    public void setFilmLanguage(String filmLanguage) {
        this.filmLanguage = filmLanguage;
    }

    public BigDecimal getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(BigDecimal filmLength) {
        this.filmLength = filmLength;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    public BigDecimal getFilmScore() {
        return filmScore;
    }

    public void setFilmScore(BigDecimal filmScore) {
        this.filmScore = filmScore;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getFilmDoubanUrl() {
        return filmDoubanUrl;
    }

    public void setFilmDoubanUrl(String filmDoubanUrl) {
        this.filmDoubanUrl = filmDoubanUrl;
    }

    public BigDecimal getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(BigDecimal starLevel) {
        this.starLevel = starLevel;
    }

    public BigDecimal getOneStar() {
        return oneStar;
    }

    public void setOneStar(BigDecimal oneStar) {
        this.oneStar = oneStar;
    }

    public BigDecimal getTwoStar() {
        return twoStar;
    }

    public void setTwoStar(BigDecimal twoStar) {
        this.twoStar = twoStar;
    }

    public BigDecimal getThreeStar() {
        return threeStar;
    }

    public void setThreeStar(BigDecimal threeStar) {
        this.threeStar = threeStar;
    }

    public BigDecimal getFourStar() {
        return fourStar;
    }

    public void setFourStar(BigDecimal fourStar) {
        this.fourStar = fourStar;
    }

    public BigDecimal getFiveStar() {
        return fiveStar;
    }

    public void setFiveStar(BigDecimal fiveStar) {
        this.fiveStar = fiveStar;
    }
}
