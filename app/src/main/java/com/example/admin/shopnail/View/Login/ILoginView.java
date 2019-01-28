package com.example.admin.shopnail.View.Login;

import com.example.admin.shopnail.Model.Login.GsonLoginOutSide;

public interface ILoginView {
    void onLoginResult(boolean result);
    void dismissProgress();

    void onLoginOutSideSuccess(GsonLoginOutSide mGsonLoginOutSide);

    void onLoginOutSideFail(String login_fail);
}
