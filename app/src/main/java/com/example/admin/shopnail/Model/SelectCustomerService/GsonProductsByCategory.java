package com.example.admin.shopnail.Model.SelectCustomerService;

import java.util.List;

public class GsonProductsByCategory {


    /**
     * status : true
     * success : {"code":200,"data":[{"id":1,"name":"Natural Full Set","image":"","parent":"5","desc":"Natural Full Set","price":"10","is_new":"1","is_hot":"0","created_at":"2018-10-16 15:10:02","updated_at":"2018-10-16 15:10:02"},{"id":2,"name":"Manicure","image":"","parent":"6","desc":"Manicure","price":"20","is_new":"1","is_hot":"0","created_at":"2018-10-16 15:10:07","updated_at":"2018-10-16 15:10:07"},{"id":3,"name":"Eye Brows","image":"","parent":"7","desc":"Eye Brows","price":"10","is_new":"0","is_hot":"1","created_at":"2018-10-16 15:10:13","updated_at":"2018-10-16 15:10:13"},{"id":4,"name":"Eye Brows Tinting","image":"","parent":"7","desc":"Eye Brows Tinting","price":"40","is_new":"0","is_hot":"1","created_at":"2018-10-16 15:10:18","updated_at":"2018-10-16 15:10:18"}]}
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
         * data : [{"id":1,"name":"Natural Full Set","image":"","parent":"5","desc":"Natural Full Set","price":"10","is_new":"1","is_hot":"0","created_at":"2018-10-16 15:10:02","updated_at":"2018-10-16 15:10:02"},{"id":2,"name":"Manicure","image":"","parent":"6","desc":"Manicure","price":"20","is_new":"1","is_hot":"0","created_at":"2018-10-16 15:10:07","updated_at":"2018-10-16 15:10:07"},{"id":3,"name":"Eye Brows","image":"","parent":"7","desc":"Eye Brows","price":"10","is_new":"0","is_hot":"1","created_at":"2018-10-16 15:10:13","updated_at":"2018-10-16 15:10:13"},{"id":4,"name":"Eye Brows Tinting","image":"","parent":"7","desc":"Eye Brows Tinting","price":"40","is_new":"0","is_hot":"1","created_at":"2018-10-16 15:10:18","updated_at":"2018-10-16 15:10:18"}]
         */

        private int code;
        private List<DataBean> data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1
             * name : Natural Full Set
             * image :
             * parent : 5
             * desc : Natural Full Set
             * price : 10
             * is_new : 1
             * is_hot : 0
             * created_at : 2018-10-16 15:10:02
             * updated_at : 2018-10-16 15:10:02
             */

            private int id;
            private String name;
            private String image;
            private String parent;
            private String desc;
            private String price;
            private String is_new;
            private String is_hot;
            private String created_at;
            private String updated_at;
            private String up_to;

            public void setUp_to(String up_to) {
                this.up_to = up_to;
            }

            public String getUp_to() {
                return up_to;
            }

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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
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
