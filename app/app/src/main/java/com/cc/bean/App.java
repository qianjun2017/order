package com.cc.bean;

import java.io.Serializable;

public class App implements Serializable {

    /**
     * 应用中文名
     */
    private String name;

    /**
     * 应用包
     */
    private String packageName;

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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Boolean getMonitor() {
        return monitor;
    }

    public void setMonitor(Boolean monitor) {
        this.monitor = monitor;
    }

    @Override
    public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", monitor=" + monitor +
                '}';
    }
}
