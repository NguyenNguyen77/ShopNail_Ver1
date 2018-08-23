package com.example.admin.shopnail.Model;

public class Employee implements IEmployee {

    int idEmployee;
    String nameEmployee;
    int phoneNumberEmployee;

    public Employee(int idEmployee, String nameEmployee, int phoneNumberEmployee) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.phoneNumberEmployee = phoneNumberEmployee;
    }

    @Override
    public int getIDEmployee() {
        return idEmployee;
    }

    @Override
    public String getNameEmployee() {
        return nameEmployee;
    }

    @Override
    public int getPhoneNumberEmployee() {
        return phoneNumberEmployee;
    }
}
