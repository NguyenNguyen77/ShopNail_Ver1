package com.example.admin.shopnail.Model.ManageStaff;

public class CheckBoxObject {
    int Id;
    boolean isService;
    boolean isBonus;
    boolean isWax;

    public CheckBoxObject(int id, boolean isService, boolean isBonus, boolean isWax) {
        this.isService = isService;
        this.isBonus = isBonus;
        this.isWax = isWax;
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isService() {
        return isService;
    }

    public void setService(boolean service) {
        isService = service;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public void setBonus(boolean bonus) {
        isBonus = bonus;
    }

    public boolean isWax() {
        return isWax;
    }

    public void setWax(boolean wax) {
        isWax = wax;
    }
}
