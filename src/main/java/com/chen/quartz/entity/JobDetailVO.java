package com.chen.quartz.entity;


public class JobDetailVO {
    private String groupName;
    private String cronExpression;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public String toString() {
        return "JobDetail{" +
                "groupName='" + groupName + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                '}';
    }
}
