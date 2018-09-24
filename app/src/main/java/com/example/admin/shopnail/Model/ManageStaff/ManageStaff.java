package com.example.admin.shopnail.Model.ManageStaff;

public class ManageStaff {
    Boolean checkService;
    Boolean checkWax;
    Boolean checkBonus;
    String txtService;

    public ManageStaff(Boolean checkService, String txtService, Boolean checkWax, Boolean checkBonus) {
        this.checkService = checkService;
        this.checkWax = checkWax;
        this.checkBonus = checkBonus;
        this.txtService = txtService;
    }

    public Boolean getCheckService() {
        return checkService;
    }

    public void setCheckService(Boolean checkService) {
        this.checkService = checkService;
    }

    public Boolean getCheckWax() {
        return checkWax;
    }

    public void setCheckWax(Boolean checkWax) {
        this.checkWax = checkWax;
    }

    public Boolean getCheckBonus() {
        return checkBonus;
    }

    public void setCheckBonus(Boolean checkBonus) {
        this.checkBonus = checkBonus;
    }

    public String getTxtService() {
        return txtService;
    }

    public void setTxtService(String txtService) {
        this.txtService = txtService;
    }
}
