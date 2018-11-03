package com.example.admin.shopnail.Model.ManageStaff;

public class GsonGenerateCheckbox {


    /**
     * status : true
     * success : {"code":200,"setting":{"id":1,"service_number":"5","wax":"6","bonus":"7","created_at":null,"updated_at":null}}
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
         * setting : {"id":1,"service_number":"5","wax":"6","bonus":"7","created_at":null,"updated_at":null}
         */

        private int code;
        private SettingBean setting;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public SettingBean getSetting() {
            return setting;
        }

        public void setSetting(SettingBean setting) {
            this.setting = setting;
        }

        public static class SettingBean {
            /**
             * id : 1
             * service_number : 5
             * wax : 6
             * bonus : 7
             * created_at : null
             * updated_at : null
             */

            private int id;
            private String service_number;
            private String wax;
            private String bonus;
            private Object created_at;
            private Object updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getService_number() {
                return service_number;
            }

            public void setService_number(String service_number) {
                this.service_number = service_number;
            }

            public String getWax() {
                return wax;
            }

            public void setWax(String wax) {
                this.wax = wax;
            }

            public String getBonus() {
                return bonus;
            }

            public void setBonus(String bonus) {
                this.bonus = bonus;
            }

            public Object getCreated_at() {
                return created_at;
            }

            public void setCreated_at(Object created_at) {
                this.created_at = created_at;
            }

            public Object getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(Object updated_at) {
                this.updated_at = updated_at;
            }
        }
    }
}
