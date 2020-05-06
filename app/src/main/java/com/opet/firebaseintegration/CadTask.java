package com.opet.firebaseintegration;

public class CadTask {
    private String ownerUser;
    private String creationDate;
    private String taskTitle;
    private String priority;
    private String taskCategory;

    public CadTask(String creationDate, String taskTitle, String priority, String taskCategory, String ownerUser) {
        this.creationDate = creationDate;
        this.taskTitle = taskTitle;
        this.priority = priority;
        this.taskCategory = taskCategory;
        this.ownerUser = ownerUser;
    }

    public CadTask() {
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser;
    }
}
