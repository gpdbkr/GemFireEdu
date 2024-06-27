package com.vmware.gemfire;

public class President {
    protected String name;
    protected String birthLocation;
    protected String birthDay;

    public President() {
        this("", "", "");
    }

    protected President(String strName, String strBirthLocation, String strBirthDay) {
        this.name = strName;
        this.birthLocation = strBirthLocation;
        this.birthDay = strBirthDay;
    }

    public String getName() {
        return name;
    }

    public String getBirthLocation() {
        return birthLocation;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void printPresident() {
        System.out.println("Name:" + name + ",\t Birth Location:" + birthLocation + ",\t Birthday: " + birthDay);
    }
}
