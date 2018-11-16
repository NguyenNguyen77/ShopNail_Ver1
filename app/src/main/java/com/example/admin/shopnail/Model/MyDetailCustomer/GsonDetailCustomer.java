package com.example.admin.shopnail.Model.MyDetailCustomer;

import java.util.List;

public class GsonDetailCustomer {


    /**
     * status : true
     * success : {"code":200,"customers":{"userId":"5","fullname":"client 3","phone":"222-333-6666","date":"2018-11-16","orders":[{"orderId":"129","orderExtra":"222","orderComment":"","orderProducts":[{"productId":"1","productName":"Natural Full Set","productPrice":"10","status":"0"},{"productId":"2","productName":"Manicure","productPrice":"20","status":"0"},{"productId":"3","productName":"Eye Brows","productPrice":"10","status":"0"},{"productId":"4","productName":"Eye Brows Tinting","productPrice":"40","status":"0"}]}]}}
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
         * customers : {"userId":"5","fullname":"client 3","phone":"222-333-6666","date":"2018-11-16","orders":[{"orderId":"129","orderExtra":"222","orderComment":"","orderProducts":[{"productId":"1","productName":"Natural Full Set","productPrice":"10","status":"0"},{"productId":"2","productName":"Manicure","productPrice":"20","status":"0"},{"productId":"3","productName":"Eye Brows","productPrice":"10","status":"0"},{"productId":"4","productName":"Eye Brows Tinting","productPrice":"40","status":"0"}]}]}
         */

        private int code;
        private CustomersBean customers;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public CustomersBean getCustomers() {
            return customers;
        }

        public void setCustomers(CustomersBean customers) {
            this.customers = customers;
        }

        public static class CustomersBean {
            /**
             * userId : 5
             * fullname : client 3
             * phone : 222-333-6666
             * date : 2018-11-16
             * orders : [{"orderId":"129","orderExtra":"222","orderComment":"","orderProducts":[{"productId":"1","productName":"Natural Full Set","productPrice":"10","status":"0"},{"productId":"2","productName":"Manicure","productPrice":"20","status":"0"},{"productId":"3","productName":"Eye Brows","productPrice":"10","status":"0"},{"productId":"4","productName":"Eye Brows Tinting","productPrice":"40","status":"0"}]}]
             */

            private String userId;
            private String fullname;
            private String phone;
            private String date;
            private List<OrdersBean> orders;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
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

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public List<OrdersBean> getOrders() {
                return orders;
            }

            public void setOrders(List<OrdersBean> orders) {
                this.orders = orders;
            }

            public static class OrdersBean {
                /**
                 * orderId : 129
                 * orderExtra : 222
                 * orderComment :
                 * orderProducts : [{"productId":"1","productName":"Natural Full Set","productPrice":"10","status":"0"},{"productId":"2","productName":"Manicure","productPrice":"20","status":"0"},{"productId":"3","productName":"Eye Brows","productPrice":"10","status":"0"},{"productId":"4","productName":"Eye Brows Tinting","productPrice":"40","status":"0"}]
                 */

                private String orderId;
                private String orderExtra;
                private String orderComment;
                private List<OrderProductsBean> orderProducts;

                public String getOrderId() {
                    return orderId;
                }

                public void setOrderId(String orderId) {
                    this.orderId = orderId;
                }

                public String getOrderExtra() {
                    return orderExtra;
                }

                public void setOrderExtra(String orderExtra) {
                    this.orderExtra = orderExtra;
                }

                public String getOrderComment() {
                    return orderComment;
                }

                public void setOrderComment(String orderComment) {
                    this.orderComment = orderComment;
                }

                public List<OrderProductsBean> getOrderProducts() {
                    return orderProducts;
                }

                public void setOrderProducts(List<OrderProductsBean> orderProducts) {
                    this.orderProducts = orderProducts;
                }

                public static class OrderProductsBean {
                    /**
                     * productId : 1
                     * productName : Natural Full Set
                     * productPrice : 10
                     * status : 0
                     */

                    private String productId;
                    private String productName;
                    private String productPrice;
                    private String status;

                    public String getProductId() {
                        return productId;
                    }

                    public void setProductId(String productId) {
                        this.productId = productId;
                    }

                    public String getProductName() {
                        return productName;
                    }

                    public void setProductName(String productName) {
                        this.productName = productName;
                    }

                    public String getProductPrice() {
                        return productPrice;
                    }

                    public void setProductPrice(String productPrice) {
                        this.productPrice = productPrice;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }
                }
            }
        }
    }
}
