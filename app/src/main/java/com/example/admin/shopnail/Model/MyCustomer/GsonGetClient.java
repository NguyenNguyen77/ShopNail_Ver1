package com.example.admin.shopnail.Model.MyCustomer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GsonGetClient {


    /**
     * status : true
     * success : {"code":200,"clients":{"5":{"clientId":"5","clientName":"client 3","clientPhone":"222-333-6666","clientOrderId":["14"]}}}
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
         * clients : {"5":{"clientId":"5","clientName":"client 3","clientPhone":"222-333-6666","clientOrderId":["14"]}}
         */

        private int code;
        private ClientsBean clients;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public ClientsBean getClients() {
            return clients;
        }

        public void setClients(ClientsBean clients) {
            this.clients = clients;
        }

        public static class ClientsBean {
            /**
             * 5 : {"clientId":"5","clientName":"client 3","clientPhone":"222-333-6666","clientOrderId":["14"]}
             */

            @SerializedName("5")
            private _$5Bean _$5;

            public _$5Bean get_$5() {
                return _$5;
            }

            public void set_$5(_$5Bean _$5) {
                this._$5 = _$5;
            }

            public static class _$5Bean {
                /**
                 * clientId : 5
                 * clientName : client 3
                 * clientPhone : 222-333-6666
                 * clientOrderId : ["14"]
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
}
