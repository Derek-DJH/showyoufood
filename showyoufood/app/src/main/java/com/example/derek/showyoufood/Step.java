package com.example.derek.showyoufood;

public class Step {
    private String Sid;
            private String MID;
    private String Step;
    private byte[]  Steppthoto;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getStep() {
        return Step;
    }

    public void setStep(String step) {
        Step = step;
    }

    public byte[] getSteppthoto() {
        return Steppthoto;
    }

    public void setSteppthoto(byte[] steppthoto) {
        Steppthoto = steppthoto;
    }
}
