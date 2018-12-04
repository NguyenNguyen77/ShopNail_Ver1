package com.example.admin.shopnail.Model.MyCustomer;

import java.util.List;

public class GsonClientTime {


    /**
     * status : true
     * success : {"code":200,"time":[{"orderId":"19","time":["02:15:00"]},{"orderId":"20","time":["02:18:00"]},{"orderId":"21","time":["02:25:00"]}]}
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
         * time : [{"orderId":"19","time":["02:15:00"]},{"orderId":"20","time":["02:18:00"]},{"orderId":"21","time":["02:25:00"]}]
         */

        private int code;
        private List<TimeBean> time;
        private String error;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public List<TimeBean> getTime() {
            return time;
        }

        public void setTime(List<TimeBean> time) {
            this.time = time;
        }

        public static class TimeBean {
            /**
             * orderId : 19
             * time : ["02:15:00"]
             */

            private String orderId;
            private List<String> time;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public List<String> getTime() {
                return time;
            }

            public void setTime(List<String> time) {
                this.time = time;
            }
        }
    }
}
