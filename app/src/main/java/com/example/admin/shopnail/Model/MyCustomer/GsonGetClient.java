package com.example.admin.shopnail.Model.MyCustomer;

import java.util.List;

public class GsonGetClient {

    /**
     * status : true
     * success : {"code":200,"clients":[{"clientId":"5","clientName":"client 3","clientPhone":"222-333-6666","clientOrderId":["14","15"]}]}
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
         * clients : [{"clientId":"5","clientName":"client 3","clientPhone":"222-333-6666","clientOrderId":["14","15"]}]
         */

        private int code;
        private List<ClientsBean> clients;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<ClientsBean> getClients() {
            return clients;
        }

        public void setClients(List<ClientsBean> clients) {
            this.clients = clients;
        }

        public static class ClientsBean {
            /**
             * clientId : 5
             * clientName : client 3
             * clientPhone : 222-333-6666
             * clientOrderId : ["14","15"]
             */

            private String clientId;
            private String clientName;
            private String clientPhone;
            private List<String> clientOrderId;

            public String getClientId() {
                return clientId;
            }

            public void setClientId(String clientId) {
                this.clientId = clientId;
            }

            public String getClientName() {
                return clientName;
            }

            public void setClientName(String clientName) {
                this.clientName = clientName;
            }

            public String getClientPhone() {
                return clientPhone;
            }

            public void setClientPhone(String clientPhone) {
                this.clientPhone = clientPhone;
            }

            public List<String> getClientOrderId() {
                return clientOrderId;
            }

            public void setClientOrderId(List<String> clientOrderId) {
                this.clientOrderId = clientOrderId;
            }
        }
    }
}
