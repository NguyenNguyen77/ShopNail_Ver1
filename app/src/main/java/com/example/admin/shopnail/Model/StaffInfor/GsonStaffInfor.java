package com.example.admin.shopnail.Model.StaffInfor;

public class GsonStaffInfor {


    /**
     * status : true
     * success : {"id":4,"username":"staff1","fullname":"staff1","avatar":"http://142.93.29.45/nailweb/public/uploads/default.png","code":200}
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
         * id : 4
         * username : staff1
         * fullname : staff1
         * avatar : http://142.93.29.45/nailweb/public/uploads/default.png
         * code : 200
         */

        private int id;
        private String username;
        private String fullname;
        private String avatar;
        private int code;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
