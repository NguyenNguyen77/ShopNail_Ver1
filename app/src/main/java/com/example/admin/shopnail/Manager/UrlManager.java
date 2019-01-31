package com.example.admin.shopnail.Manager;

public class UrlManager {
    public static final String DOMAIN = "http://142.93.29.45:8888";
    public static final String LOGIN_URL = DOMAIN + "/api/login";
 
    public static final String LOGIN_OUT_SIDE_URL = DOMAIN + "/api/clientLoginOutside";
    public static final String GET_USER_BY_ID_URL = DOMAIN + "/api/getUserById/";
    public static final String CHANGE_PASSWORD_URL = DOMAIN + "/api/updatePass";
    public static final String CREATE_ACCOUNT_CUSTOMER_URL = DOMAIN + "/api/newClient";
    public static final String LOGIN_OLD_CUSTOMER_URL = DOMAIN + "/api/clientLogin";
    public static final String GET_CATEGORY_LIST_URL = DOMAIN + "/api/getAllCategory";
    public static final String GET_PRODUCTS_BY_CATEGORY_URL = DOMAIN + "/api/getProductByCate";
    public static final String CHANGE_AVATAR_URL = DOMAIN + "/api/changeAvatar";
    public static final String GET_ALL_STAFF_URL = DOMAIN + "/api/getAllStaff";
    public static final String GET_ALL_SERVICE_URL = DOMAIN + "/api/getAllService";
    public static final String ORDER_SERVICE_BY_STAFF_URL = DOMAIN + "/api/order";
    public static final String GET_CLIENT_OF_STAFF = DOMAIN + "/api/clientOfStaff";
    public static final String GET_CUSTOMER_INFOR = DOMAIN + "/api/getCustomerInfo";
    public static final String GET_HISTORY_OF_STAFF_BY_ORDER_ID_ARRAY_URL = DOMAIN + "/api/historyClientByStaff";
    public static final String FORGOT_PASSWORD_URL = DOMAIN + "/api/forgotPassword";
    public static final String UPDATE_STATUS_SERVICE_URL = DOMAIN + "/api/updateStatusService";
    public static final String CANCEL_SERVICE_SERVICE_URL = DOMAIN + "/api/deleteServiceOrder";
    public static final String UPDATE_EXTRA_URL = DOMAIN + "/api/updateExtraByOrder";
    public static final String GENERATE_CHECK_BOX_URL = DOMAIN + "/api/generateCheckbox";
    public static final String ADD_BOOKING_ONLINE = DOMAIN + "/api/bookingServiceOnline";
    public static final String ADD_OR_UPDATE_SERVICE_CHECKING_URL = DOMAIN + "/api/addOrUpdateServiceChecking";
    public static final String BOOKING_TIME_CHECKING_URL = DOMAIN + "/api/checkTimeStaff";
    public static final String GET_TIME_OF_CLIENT_FROM_STAFF_URL = DOMAIN + "/api/getCustomerTimeOrder";
    public static final String GET_SERVICE_TYPE_URL = DOMAIN + "/api/getServiceType";
    public static final String GET_ALL_NAVIGATE_STAFF_URL = DOMAIN + "/api/getAllNavigateStaff";
    public static final String GET_MY_CUSTOMER_URL = DOMAIN + "/api/getMyCustomer";
    public static final String GET_TIME_OPEN_CLOSE_URL = DOMAIN + "/api/getTime";
    public static final String GET_ORDER_BOOKING_ONLINE_URL = DOMAIN + "/api/getOrderAppointmentOnline";
    public static final String DELETE_ORDER_APPOINTMENT_ONLINE_URL = DOMAIN + "/api/deleteOrderAppointmentOnline";

}
