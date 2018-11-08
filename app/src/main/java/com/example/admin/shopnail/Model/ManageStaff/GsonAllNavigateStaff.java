package com.example.admin.shopnail.Model.ManageStaff;

import java.util.List;

public class GsonAllNavigateStaff {


    /**
     * status : true
     * success : {"code":200,"navigates":[{"id":49,"staff_id":"4","is_enable":"1","service_id":"1","wax":null,"bonus":null,"order_number":"1","type":"1","date":"2018-11-08"},{"id":50,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"1","type":"2","date":"2018-11-08"},{"id":51,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"1","type":"3","date":"2018-11-08"},{"id":52,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"2","type":"1","date":"2018-11-08"},{"id":53,"staff_id":"4","is_enable":null,"service_id":null,"wax":"1","bonus":null,"order_number":"2","type":"2","date":"2018-11-08"},{"id":54,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"2","type":"3","date":"2018-11-08"},{"id":55,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"3","type":"1","date":"2018-11-08"},{"id":56,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"3","type":"2","date":"2018-11-08"},{"id":57,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"1","order_number":"3","type":"3","date":"2018-11-08"},{"id":58,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"4","type":"1","date":"2018-11-08"},{"id":59,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"4","type":"2","date":"2018-11-08"},{"id":60,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"4","type":"3","date":"2018-11-08"},{"id":61,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"5","type":"1","date":"2018-11-08"},{"id":62,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"5","type":"2","date":"2018-11-08"},{"id":63,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"5","type":"3","date":"2018-11-08"},{"id":64,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"6","type":"1","date":"2018-11-08"},{"id":65,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"6","type":"2","date":"2018-11-08"},{"id":66,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"6","type":"3","date":"2018-11-08"},{"id":67,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"7","type":"1","date":"2018-11-08"},{"id":68,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"7","type":"2","date":"2018-11-08"},{"id":69,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"7","type":"3","date":"2018-11-08"}]}
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
         * navigates : [{"id":49,"staff_id":"4","is_enable":"1","service_id":"1","wax":null,"bonus":null,"order_number":"1","type":"1","date":"2018-11-08"},{"id":50,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"1","type":"2","date":"2018-11-08"},{"id":51,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"1","type":"3","date":"2018-11-08"},{"id":52,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"2","type":"1","date":"2018-11-08"},{"id":53,"staff_id":"4","is_enable":null,"service_id":null,"wax":"1","bonus":null,"order_number":"2","type":"2","date":"2018-11-08"},{"id":54,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"2","type":"3","date":"2018-11-08"},{"id":55,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"3","type":"1","date":"2018-11-08"},{"id":56,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"3","type":"2","date":"2018-11-08"},{"id":57,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"1","order_number":"3","type":"3","date":"2018-11-08"},{"id":58,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"4","type":"1","date":"2018-11-08"},{"id":59,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"4","type":"2","date":"2018-11-08"},{"id":60,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"4","type":"3","date":"2018-11-08"},{"id":61,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"5","type":"1","date":"2018-11-08"},{"id":62,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"5","type":"2","date":"2018-11-08"},{"id":63,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"5","type":"3","date":"2018-11-08"},{"id":64,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"6","type":"1","date":"2018-11-08"},{"id":65,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"6","type":"2","date":"2018-11-08"},{"id":66,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"6","type":"3","date":"2018-11-08"},{"id":67,"staff_id":"4","is_enable":"0","service_id":"0","wax":null,"bonus":null,"order_number":"7","type":"1","date":"2018-11-08"},{"id":68,"staff_id":"4","is_enable":null,"service_id":null,"wax":"0","bonus":null,"order_number":"7","type":"2","date":"2018-11-08"},{"id":69,"staff_id":"4","is_enable":null,"service_id":null,"wax":null,"bonus":"0","order_number":"7","type":"3","date":"2018-11-08"}]
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
             * id : 49
             * staff_id : 4
             * is_enable : 1
             * service_id : 1
             * wax : null
             * bonus : null
             * order_number : 1
             * type : 1
             * date : 2018-11-08
             */

            private int id;
            private String staff_id;
            private String is_enable;
            private String service_id;
            private Object wax;
            private Object bonus;
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

            public Object getWax() {
                return wax;
            }

            public void setWax(Object wax) {
                this.wax = wax;
            }

            public Object getBonus() {
                return bonus;
            }

            public void setBonus(Object bonus) {
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
