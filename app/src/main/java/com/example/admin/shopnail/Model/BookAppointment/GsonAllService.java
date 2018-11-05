package com.example.admin.shopnail.Model.BookAppointment;

import java.util.ArrayList;

public class GsonAllService {

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

        private int code;
        private ArrayList<ProductBean> product;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public ArrayList<ProductBean> getProduct () {
            return product;
        }

        public void setProduct (ArrayList<ProductBean> productBean) {
            this.product = productBean;
        }
    }

    public static class ProductBean {

        private String cateName;
        private ArrayList<CateChildBean> cateChild;

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public ArrayList<CateChildBean> getCateChild () {
            return cateChild;
        }

        public void setCateChild (ArrayList<CateChildBean> cateChild) {
            this.cateChild = cateChild;
        }

    }
    public static class CateChildBean {

        private int productId;
        private String productName;
        private String productPrice;

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getProductName () {
            return productName;
        }

        public void setProductName (String productName) {
            this.productName = productName;
        }

        public String getProductPrice () {
            return productPrice;
        }

        public void setProductPrice (String productPrice) {
            this.productPrice = productPrice;
        }
    }
}
