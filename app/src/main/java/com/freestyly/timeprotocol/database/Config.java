package com.freestyly.timeprotocol.database;

/**
 * Created by Freestyly on 05.03.2017.
 */

public class Config {

    private int id;
    private int timeToWork;
    private int startOvertime;

    public int getStartOvertime() {
        return startOvertime;
    }

    public void setStartOvertime(int startOvertime) {
        this.startOvertime = startOvertime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeToWork() {
        return timeToWork;
    }

    public void setTimeToWork(int timeToWork) {
        this.timeToWork = timeToWork;
    }

    public Config(int id, int timeToWork, int startOvertime){
        this.id = id;
        this.timeToWork = timeToWork;
        this.startOvertime = startOvertime;
    }
}
