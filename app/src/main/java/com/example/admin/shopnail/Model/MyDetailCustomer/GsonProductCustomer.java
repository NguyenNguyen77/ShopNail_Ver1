package com.example.admin.shopnail.Model.MyDetailCustomer;

import java.util.List;

public class GsonProductCustomer {


    /**
     * status : true
     * success : {"code":200,"products":[{"orderId":"19","extraMoney":"20","product":[{"productId":"1","productName":"Natural Full Set","productPrice":"10"},{"productId":"2","productName":"Manicure","productPrice":"20"},{"productId":"3","productName":"Eye Brows","productPrice":"10"},{"productId":"4","productName":"Eye Brows Tinting","productPrice":"40"}]}]}
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
         * products : [{"orderId":"19","extraMoney":"20","product":[{"productId":"1","productName":"Natural Full Set","productPrice":"10"},{"productId":"2","productName":"Manicure","productPrice":"20"},{"productId":"3","productName":"Eye Brows","productPrice":"10"},{"productId":"4","productName":"Eye Brows Tinting","productPrice":"40"}]}]
         */

        private int code;
        private List<ProductsBean> products;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * orderId : 19
             * extraMoney : 20
             * product : [{"productId":"1","productName":"Natural Full Set","productPrice":"10"},{"productId":"2","productName":"Manicure","productPrice":"20"},{"productId":"3","productName":"Eye Brows","productPrice":"10"},{"productId":"4","productName":"Eye Brows Tinting","productPrice":"40"}]
             */

            private String orderId;
            private String extraMoney;
            private List<ProductBean> product;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getExtraMoney() {
                return extraMoney;
            }

            public void setExtraMoney(String extraMoney) {
                this.extraMoney = extraMoney;
            }

            public List<ProductBean> getProduct() {
                return product;
            }

            public void setProduct(List<ProductBean> product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
                 * productId : 1
                 * productName : Natural Full Set
                 * productPrice : 10
                 */

                private String productId;
                private String productName;
                private String productPrice;

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
            }
        }
    }
}
