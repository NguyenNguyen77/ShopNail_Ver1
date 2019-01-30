package com.example.admin.shopnail.Model.CancelAppointmentOnline;

import java.util.List;

public class GsonOppointment {


    /**
     * status : true
     * success : {"code":200,"serviceType":[{"dateOrder":"2019-01-30","orders":[{"name":"Natural Full Set","price":"10","orderId":"280","productId":"1","timeOrder":"14:28:00"}]}],"limit":20,"offset":0}
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
         * serviceType : [{"dateOrder":"2019-01-30","orders":[{"name":"Natural Full Set","price":"10","orderId":"280","productId":"1","timeOrder":"14:28:00"}]}]
         * limit : 20
         * offset : 0
         */

        private int code;
        private int limit;
        private int offset;
        private List<ServiceTypeBean> serviceType;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public List<ServiceTypeBean> getServiceType() {
            return serviceType;
        }

        public void setServiceType(List<ServiceTypeBean> serviceType) {
            this.serviceType = serviceType;
        }

        public static class ServiceTypeBean {
            /**
             * dateOrder : 2019-01-30
             * orders : [{"name":"Natural Full Set","price":"10","orderId":"280","productId":"1","timeOrder":"14:28:00"}]
             */

            private String dateOrder;
            private List<OrdersBean> orders;

            public String getDateOrder() {
                return dateOrder;
            }

            public void setDateOrder(String dateOrder) {
                this.dateOrder = dateOrder;
            }

            public List<OrdersBean> getOrders() {
                return orders;
            }

            public void setOrders(List<OrdersBean> orders) {
                this.orders = orders;
            }

            public static class OrdersBean {
                /**
                 * name : Natural Full Set
                 * price : 10
                 * orderId : 280
                 * productId : 1
                 * timeOrder : 14:28:00
                 */

                private String name;
                private String price;
                private String orderId;
                private String productId;
                private String timeOrder;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getOrderId() {
                    return orderId;
                }

                public void setOrderId(String orderId) {
                    this.orderId = orderId;
                }

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getTimeOrder() {
                    return timeOrder;
                }

                public void setTimeOrder(String timeOrder) {
                    this.timeOrder = timeOrder;
                }
            }
        }
    }
}
