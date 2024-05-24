package com.iuh.kttkpm.be.registCourses.models;

public enum WeekDay {
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7),
    SUNDAY(1);


    WeekDay(int day) {
    }
    private int day;

    public int getDay() {
        return day;
    }


}
