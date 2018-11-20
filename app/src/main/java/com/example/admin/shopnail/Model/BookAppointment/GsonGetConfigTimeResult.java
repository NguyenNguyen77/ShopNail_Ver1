package com.example.admin.shopnail.Model.BookAppointment;

import java.util.ArrayList;

public class GsonGetConfigTimeResult {
    private boolean status;
    private SuccessBean success;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SuccessBean getSuccess() {
        return success;
    }

    public void setSuccess(SuccessBean success) {
        this.success = success;
    }

    public static class SuccessBean {

        private int code;
        private ArrayList<TimeBean> time;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public ArrayList<TimeBean> getTime() {
            return this.time;
        }

        public void setTime(ArrayList<TimeBean> time) {
            this.time = time;
        }
    }

    public static class TimeBean {

        private int id;
        private String start;
        private String end;
        private String created_at;
        private String updated_at;

        public int getId () {
            return id;
        }

        public void setId (int id) {
            this.id = id;
        }

        public String getStart () {
            return start;
        }

        public void setStart (String start) {
            this.start = start;
        }

        public String getEnd () {
            return this.end;
        }

        public void setEnd (String end) {
            this.end = end;
        }

        public String getCreate () {
            return this.created_at;
        }

        public void setCreate (String create) {
            this.created_at = create;
        }

        public String getUpdate () {
            return this.updated_at;
        }

        public void setUpdate (String update) {
            this.updated_at = update;
        }
    }
}
