package com.example.derek.showyoufood;

public class Menu {
    private String mid;
    private String mname;
    private String scriptId;
    private byte[] headPhoto;
    private String stepId;
    private String createTime;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getScriptId() {
        return scriptId;
    }

    public void setScriptId(String scriptId) {
        this.scriptId = scriptId;
    }

    public byte[] getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(byte[] headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
