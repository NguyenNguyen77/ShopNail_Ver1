package com.example.admin.shopnail.Model.ManageStaff;

public class CheckBoxObject {
    int Id;
    boolean isService;
    boolean isBonus;
    boolean isWax;
    int order;
    int typeService;
    int typeBonus;
    int typeWax;
    int valueService;
    int valueBonus;
    int valueWax;

    public CheckBoxObject(int id, boolean isService, boolean isBonus, boolean isWax, int order, int typeService, int typeBonus, int typeWax, int valueService, int valueBonus, int valueWax) {
        Id = id;
        this.isService = isService;
        this.isBonus = isBonus;
        this.isWax = isWax;
        this.order = order;
        this.typeService = typeService;
        this.typeBonus = typeBonus;
        this.typeWax = typeWax;
        this.valueService = valueService;
        this.valueBonus = valueBonus;
        this.valueWax = valueWax;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getTypeService() {
        return typeService;
    }

    public void setTypeService(int typeService) {
        this.typeService = typeService;
    }

    public int getTypeBonus() {
        return typeBonus;
    }

    public void setTypeBonus(int typeBonus) {
        this.typeBonus = typeBonus;
    }

    public int getTypeWax() {
        return typeWax;
    }

    public void setTypeWax(int typeWax) {
        this.typeWax = typeWax;
    }

    public int getValueService() {
        return valueService;
    }

    public void setValueService(int valueService) {
        this.valueService = valueService;
    }

    public int getValueBonus() {
        return valueBonus;
    }

    public void setValueBonus(int valueBonus) {
        this.valueBonus = valueBonus;
    }

    public int getValueWax() {
        return valueWax;
    }

    public void setValueWax(int valueWax) {
        this.valueWax = valueWax;
    }
}
