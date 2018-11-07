package com.example.admin.shopnail.Model.ManageStaff;

import java.util.List;

public class GsonServiceType {


    /**
     * status : true
     * success : {"code":200,"serviceType":[{"id":1,"name":"full tour","created_at":"2018-11-05 16:06:02","updated_at":"2018-11-05 16:06:02"},{"id":2,"name":"1/2 tour","created_at":"2018-11-05 16:06:09","updated_at":"2018-11-05 16:06:09"}]}
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
         * serviceType : [{"id":1,"name":"full tour","created_at":"2018-11-05 16:06:02","updated_at":"2018-11-05 16:06:02"},{"id":2,"name":"1/2 tour","created_at":"2018-11-05 16:06:09","updated_at":"2018-11-05 16:06:09"}]
         */

        private int code;
        private List<ServiceTypeBean> serviceType;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<ServiceTypeBean> getServiceType() {
            return serviceType;
        }

        public void setServiceType(List<ServiceTypeBean> serviceType) {
            this.serviceType = serviceType;
        }

        public static class ServiceTypeBean {
            /**
             * id : 1
             * name : full tour
             * created_at : 2018-11-05 16:06:02
             * updated_at : 2018-11-05 16:06:02
             */

            private int id;
            private String name;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }
    }
}
