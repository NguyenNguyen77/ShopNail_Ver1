package com.example.admin.shopnail.Model.Login;

public class GsonLoginOutSide {

    /**
     * status : true
     * success : {"code":200,"user_id":5,"fullname":"client 3","phone":"222-333-6666"}
     */

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
        /**
         * code : 200
         * user_id : 5
         * fullname : client 3
         * phone : 222-333-6666
         */

        private int code;
        private int user_id;
        private String fullname;
        private String phone;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
