package com.iuh.kttkpm.be.lookUpPoints.models;

public enum WeekDay {
    MONDAY("Thứ 2"),
    TUESDAY("Thứ 3"),
    WEDNESDAY("Thứ 4"),
    THURSDAY("Thứ 5"),
    FRIDAY("Thứ 6"),
    SATURDAY("Thứ 7"),
    SUNDAY("Chủ nhật");
    WeekDay(String day) {
    }
    private String day;

    public String getDay() {
        return day;
    }

}
