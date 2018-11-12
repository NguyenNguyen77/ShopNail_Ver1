package com.example.admin.shopnail.Model.ManageStaff;

import java.util.List;

public class GsonAllNavigateStaff {


    /**
     * status : true
     * success : {"code":200,"navigates":[{"id":70,"staff_id":"4","is_enable":"1","service_id":"1","wax":"0","bonus":"0","order_number":"1","type":"1","date":"2018-11-12"},{"id":71,"staff_id":"4","is_enable":"0","service_id":"0","wax":"1","bonus":"0","order_number":"2","type":"2","date":"2018-11-12"},{"id":72,"staff_id":"4","is_enable":"0","service_id":"0","wax":"0","bonus":"1","order_number":"3","type":"3","date":"2018-11-12"}]}
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
         * navigates : [{"id":70,"staff_id":"4","is_enable":"1","service_id":"1","wax":"0","bonus":"0","order_number":"1","type":"1","date":"2018-11-12"},{"id":71,"staff_id":"4","is_enable":"0","service_id":"0","wax":"1","bonus":"0","order_number":"2","type":"2","date":"2018-11-12"},{"id":72,"staff_id":"4","is_enable":"0","service_id":"0","wax":"0","bonus":"1","order_number":"3","type":"3","date":"2018-11-12"}]
         */

        private int code;
        private List<NavigatesBean> navigates;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<NavigatesBean> getNavigates() {
            return navigates;
        }

        public void setNavigates(List<NavigatesBean> navigates) {
            this.navigates = navigates;
        }

        public static class NavigatesBean {
            /**
             * id : 70
             * staff_id : 4
             * is_enable : 1
             * service_id : 1
             * wax : 0
             * bonus : 0
             * order_number : 1
             * type : 1
             * date : 2018-11-12
             */

            private int id;
            private String staff_id;
            private String is_enable;
            private String service_id;
            private String wax;
            private String bonus;
            private String order_number;
            private String type;
            private String date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStaff_id() {
                return staff_id;
            }

            public void setStaff_id(String staff_id) {
                this.staff_id = staff_id;
            }

            public String getIs_enable() {
                return is_enable;
            }

            public void setIs_enable(String is_enable) {
                this.is_enable = is_enable;
            }

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
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

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
