package com.freestyly.timeprotocol.database;

/**
 * Created by Freestyly on 05.03.2017.
 */

public class Worktime {


    private String day;
    private String come;
    private String leave;

    public Worktime(String day, String come, String leave){
        this.day = day;
        this.come = come;
        this.leave = leave;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCome() {
        return come;
    }

    public void setCome(String come) {
        this.come = come;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.day);
        sb.append("  ");
        sb.append(this.come);
        sb.append("  ");
        sb.append(this.leave);

        return sb.toString();
    }
}
