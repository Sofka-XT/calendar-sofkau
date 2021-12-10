package co.com.sofka.calendar.collections;

import java.util.List;

public class Time {

    private String categoryId;
    private Integer days;
    private String categoryName;
    private List<String> urlsRefGradles;

    public Time() {

    }

    public Time(String categoryId, Integer days, String categoryName, List<String> urlsRefGradles) {
        this.categoryId = categoryId;
        this.days = days;
        this.categoryName = categoryName;
        this.urlsRefGradles = urlsRefGradles;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getUrlsRefGradles() {
        return urlsRefGradles;
    }

    public void setUrlsRefGradles(List<String> urlsRefGradles) {
        this.urlsRefGradles = urlsRefGradles;
    }
}