package com.cc.bean;

public class App {

    /**
     * 应用中文名
     */
    private String name;

    /**
     * 应用包
     */
    private String packge;

    /**
     * 是否监听
     */
    private Boolean  monitor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackge() {
        return packge;
    }

    public void setPackge(String packge) {
        this.packge = packge;
    }

    public Boolean getMonitor() {
        return monitor;
    }

    public void setMonitor(Boolean monitor) {
        this.monitor = monitor;
    }
}
