package com.example.admin.shopnail.Model.BookAppointment;

import java.util.ArrayList;

public class GsonAllStaff {
    /**
     * status : true
     * success : {"code":200,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjM4MWYzNjk3NDMzYzBmZDM1NzFlNTM5MTA0OGJjM2UyY2U4YzRkZmZmYmRkZjRlZDI4YjBiOWFjZGViMzMwODYyM2MyMjU3MjFkN2QxZGM1In0.eyJhdWQiOiIxIiwianRpIjoiMzgxZjM2OTc0MzNjMGZkMzU3MWU1MzkxMDQ4YmMzZTJjZThjNGRmZmZiZGRmNGVkMjhiMGI5YWNkZWIzMzA4NjIzYzIyNTcyMWQ3ZDFkYzUiLCJpYXQiOjE1MzczMjkwMzMsIm5iZiI6MTUzNzMyOTAzMywiZXhwIjoxNTY4ODY1MDMzLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.HNYQFIH582dkXJsYRTqJmNEiDM6V3-Mm03GwcO3AAqFi0ESkKt5WBJc6Yf1tPXc_JjM0DCjmZ66Hm3Qt_kZTDdHFbKXU822aX6E01LOV1YwxrcZzKba4pEtmP8g6-XADOmxXvFJ47vQDII3sYaSeangs7jbfD4ofZVb5g3uV192sjovjf6BT0pC7mJLiSCwpgLz7A_FE3KLIx4pZ1uorRINrPufP9fm9xxitVajht7Vn0zlYsymP_Wt5n4c1pS8xMIeiVOPH-ByRQJRvkA0aaVNbtH60k8A793VPpuQVyws9rQgIDWZkmvVzj6Tp-Npy7_-BWGA5DXncZdywKvtrQgPn8Ds-hwJbH-fbAbY3NBTmSVhdiHquc-4abUZcynMOJWR5I1H6Arl_2VBluFg6VZi-X-_oxzkjuDgTzZM5TgkgQ63T7Kmd1VEdggw-kN2i3oahdio5dQbbD8_qQIjLjI3l0Ulu7dtLnC0Ntz1CGeGLc3hc5WAq__-1XJOUANrxWQeBztFrNtuR_qzSOQYpZzobZmncPN9rRYXhYKmZd_SMpBrjNe-8AnAoomAPOX008JUFYBUNyRsgfLymVBxUKiXHjB0Y965_hQxiZn1B3VsdCvqtxMclgBMo4jtpe5t3PzJt8pQbBsnwQfaX1StOo8JQm7iV_HtKbjb75JRv50E","id":4,"username":"staff1","name":"staff1","email":"huuloc0f9013@gmail.com","avatar":"http://142.93.29.45/nailweb/public/uploads/default.png"}
     */

    private boolean status;
    private SuccessBean success;
    private ArrayList<String> allStaff;
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
        /**
         * code : 200
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjM4MWYzNjk3NDMzYzBmZDM1NzFlNTM5MTA0OGJjM2UyY2U4YzRkZmZmYmRkZjRlZDI4YjBiOWFjZGViMzMwODYyM2MyMjU3MjFkN2QxZGM1In0.eyJhdWQiOiIxIiwianRpIjoiMzgxZjM2OTc0MzNjMGZkMzU3MWU1MzkxMDQ4YmMzZTJjZThjNGRmZmZiZGRmNGVkMjhiMGI5YWNkZWIzMzA4NjIzYzIyNTcyMWQ3ZDFkYzUiLCJpYXQiOjE1MzczMjkwMzMsIm5iZiI6MTUzNzMyOTAzMywiZXhwIjoxNTY4ODY1MDMzLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.HNYQFIH582dkXJsYRTqJmNEiDM6V3-Mm03GwcO3AAqFi0ESkKt5WBJc6Yf1tPXc_JjM0DCjmZ66Hm3Qt_kZTDdHFbKXU822aX6E01LOV1YwxrcZzKba4pEtmP8g6-XADOmxXvFJ47vQDII3sYaSeangs7jbfD4ofZVb5g3uV192sjovjf6BT0pC7mJLiSCwpgLz7A_FE3KLIx4pZ1uorRINrPufP9fm9xxitVajht7Vn0zlYsymP_Wt5n4c1pS8xMIeiVOPH-ByRQJRvkA0aaVNbtH60k8A793VPpuQVyws9rQgIDWZkmvVzj6Tp-Npy7_-BWGA5DXncZdywKvtrQgPn8Ds-hwJbH-fbAbY3NBTmSVhdiHquc-4abUZcynMOJWR5I1H6Arl_2VBluFg6VZi-X-_oxzkjuDgTzZM5TgkgQ63T7Kmd1VEdggw-kN2i3oahdio5dQbbD8_qQIjLjI3l0Ulu7dtLnC0Ntz1CGeGLc3hc5WAq__-1XJOUANrxWQeBztFrNtuR_qzSOQYpZzobZmncPN9rRYXhYKmZd_SMpBrjNe-8AnAoomAPOX008JUFYBUNyRsgfLymVBxUKiXHjB0Y965_hQxiZn1B3VsdCvqtxMclgBMo4jtpe5t3PzJt8pQbBsnwQfaX1StOo8JQm7iV_HtKbjb75JRv50E
         * id : 4
         * username : staff1
         * name : staff1
         * email : huuloc0f9013@gmail.com
         * avatar : http://142.93.29.45/nailweb/public/uploads/default.png
         */

        private int code;
        private ArrayList<StaffBean> staff;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public ArrayList<StaffBean> getStaffBean () {
            return staff;
        }

        public void setStaffBean (ArrayList<StaffBean> staffBean) {
            this.staff = staffBean;
        }


    }
    public static class StaffBean {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName () {
            return name;
        }

        public void setName (String name) {
            this.name = name;
        }

    }
}
