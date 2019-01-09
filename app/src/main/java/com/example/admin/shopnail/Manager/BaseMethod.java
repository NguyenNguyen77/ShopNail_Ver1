package com.example.admin.shopnail.Manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import static com.example.admin.shopnail.Manager.KeyManager.CLIENT_ID;
import static com.example.admin.shopnail.Manager.KeyManager.PASS_WORD;
import static com.example.admin.shopnail.Manager.KeyManager.POST;
import static com.example.admin.shopnail.Manager.KeyManager.TOKEN;
import static com.example.admin.shopnail.Manager.KeyManager.USER_ID;
import static com.example.admin.shopnail.Manager.KeyManager.USER_NAME;

public class BaseMethod   {
    Gson gson = new Gson();

    String userName;
    String passWord;
    String clientID;
    String staffId;

    int positionPage = 1;

    public int getPositionPage() {
        return positionPage;
    }

    public void setPositionPage(int positionPage) {
        this.positionPage = positionPage;
    }

    public String getClientID(Context context) {
        return BaseMethod.getDefaults(CLIENT_ID, context);
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getStaffId(Context context) {
        return BaseMethod.getDefaults(USER_ID, context);
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("hh:mm");

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    // strust for change certificate ssl http connection
    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

    public void getInforAccountFromShareReferenced(Context context) {
        setUserName(getDefaults(USER_NAME, context));
        setPassWord(getDefaults(PASS_WORD, context));
        setUserId(getDefaults(USER_ID, context));
        setToken(getDefaults(TOKEN, context));
    }


    String UserId;
    String Token;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String makePostRequest(String link, Uri.Builder builder, String Token) {
        Log.d(KeyManager.VinhCNLog, link);
        trustEveryone();
        HttpURLConnection connect;
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error!";
        }
        try {
            // cấu hình HttpURLConnection
            connect = (HttpURLConnection) url.openConnection();
//            connect.setSSLSocketFactory(SSLCertificateSocketFactory.getInsecure(0, null));
//            connect.setHostnameVerifier(new AllowAllHostnameVerifier());
            connect.setReadTimeout(15000);
            connect.setConnectTimeout(15000);
            connect.setDoOutput(true);
            connect.setInstanceFollowRedirects(false);
            // unable POST method to send
            connect.setRequestMethod(KeyManager.POST);
//            it must have for add param
            connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (Token != null) {
                Token = "Bearer " + Token;
                connect.setRequestProperty("Authorization", Token);
            }
            // if param != null let write param to the last character
//            Uri.Builder builder = new Uri.Builder();
//            builder.appendQueryParameter(USER_NAME, userID);
//            builder.appendQueryParameter(PASS_WORD, passWord);
            String query = builder.build().getEncodedQuery();
            // open connect data
            OutputStream os = connect.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            connect.connect();
            Log.d("VinhCNLog JsonParam: ", connect + "");
        } catch (IOException e1) {
            return "Error!";
        }
        try {
            int response_code = connect.getResponseCode();

            // kiểm tra kết nối ok
            if (response_code == HttpURLConnection.HTTP_OK) {
                // Đọc nội dung trả về
                InputStream input = connect.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else if (response_code == HttpURLConnection.HTTP_UNAUTHORIZED) {

                InputStreamReader streamReader1 = new InputStreamReader(connect.getErrorStream());
                BufferedReader reader = new BufferedReader(streamReader1);
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else {
                return String.valueOf(response_code);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        } finally {
            connect.disconnect();
        }
    }


    public String getOnlyNumerics(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer strBuff = new StringBuffer();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                strBuff.append(c);
            }
        }
        return strBuff.toString();
    }



    public String makePostRequestJson(String link, String jsonParam, String Token) {
        Log.d(KeyManager.VinhCNLog, link);
        trustEveryone();
        HttpURLConnection connect;
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error!";
        }
        try {
            // cấu hình HttpURLConnection
            connect = (HttpURLConnection) url.openConnection();
//            connect.setSSLSocketFactory(SSLCertificateSocketFactory.getInsecure(0, null));
//            connect.setHostnameVerifier(new AllowAllHostnameVerifier());
            connect.setReadTimeout(15000);
            connect.setConnectTimeout(15000);
            connect.setDoOutput(true);
            connect.setDoInput(true);
            connect.setInstanceFollowRedirects(false);
            // unable POST method to send
            connect.setRequestMethod(KeyManager.POST);
//            it must have for add param
//            connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connect.setRequestProperty("Content-Type", "application/json");
            if (Token != null) {
                Token = "Bearer " + Token;
                connect.setRequestProperty("Authorization", Token);
            }
            // if param != null let write param to the last character
//            Uri.Builder builder = new Uri.Builder();
//            builder.appendQueryParameter("Param", jsonParam);
//            builder.appendQueryParameter(PASS_WORD, passWord);
//            String query = builder.build().getEncodedQuery();
            // open connect data
            OutputStream os = connect.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonParam);
            writer.flush();
            writer.close();
            os.close();
            connect.connect();
            Log.d("VinhCNLog JsonParam: ", connect + "");
        } catch (IOException e1) {
            return "Error!";
        }
        try {
            int response_code = connect.getResponseCode();

            // kiểm tra kết nối ok
            if (response_code == HttpURLConnection.HTTP_OK) {
                // Đọc nội dung trả về
                InputStreamReader streamReader = new InputStreamReader(connect.getInputStream());
//                InputStreamReader streamReader = new InputStreamReader(connect.getErrorStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else if (response_code == HttpURLConnection.HTTP_UNAUTHORIZED) {

                InputStreamReader streamReader1 = new InputStreamReader(connect.getErrorStream());
                BufferedReader reader = new BufferedReader(streamReader1);
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else {
                return String.valueOf(response_code);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        } finally {
            connect.disconnect();
        }
    }




    public String makeGetRequest(String link, String Token) {
        Log.d(KeyManager.VinhCNLog, link);
        trustEveryone();
        HttpURLConnection connect;
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error!";
        }
        try {
            // cấu hình HttpURLConnection
            connect = (HttpURLConnection) url.openConnection();
//            connect.setSSLSocketFactory(SSLCertificateSocketFactory.getInsecure(0, null));
//            connect.setHostnameVerifier(new AllowAllHostnameVerifier());
            connect.setReadTimeout(15000);
            connect.setConnectTimeout(15000);
            // unable POST method to send
            connect.setRequestMethod(KeyManager.GET);
//            it must have for add param
            connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //set header token.
//            String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjU3YjgxZDhkNjkyZTZhNzI5MDc0Y2RjZDkwODI3ZTYyNmI1MDQ2NTI4MjVjZDA3MDY1YmIzNDZmMjkyOWY5YjkxZjUwOWI1ZTE0MzdlNmMwIn0.eyJhdWQiOiIxIiwianRpIjoiNTdiODFkOGQ2OTJlNmE3MjkwNzRjZGNkOTA4MjdlNjI2YjUwNDY1MjgyNWNkMDcwNjViYjM0NmYyOTI5ZjliOTFmNTA5YjVlMTQzN2U2YzAiLCJpYXQiOjE1Mzc0MTIxMDMsIm5iZiI6MTUzNzQxMjEwMywiZXhwIjoxNTY4OTQ4MTAzLCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.TAd9eYn3bewtGcDJOAI-JfN8PQz-Kz37tSXSkPJPSJV18yZLEiVNDkFnktOmqjUzYB7p6GPCZ3smnZXFwZ6dv_p-I3omdYYEuS2NnVO8ZzKmrcnDLg-oXMI22Qi149FzRO8iFb4qlebsa0eOJ4MG9WMY2waeVOQtEuh8E_nb8BmbetrE5fZTVXhe5h1KrIDa5QInK0yV_Yet-EKke_HgutNNjaTsl1oS8NF3XHJaZgRfihqOuFU7K5fonFiCCcyPTyQCc_QswE6H0gL3D9hv9TsxLXm21aXuSVz7_n2z1-ANWH7VAI4qyIMmgrip5NrVNlfPyTX8GS6lk8vbwOMaWP7SlaB0vh1XGqKbcO0YYU1Ly469j4OAKMnuv7dt8VKQa2FMCryuAveoRrZP_NCq4p5HqLHlLSOFUclhztr7m4GCFVoyOck3fI-AE4iWZAWjbNm2IfHfvUSQ0Cx10vJEMZ3cwF08OhIUsyr8yXXw1J9QoO7aKBbPMUhX6XE-3x-2FITQTmIquuOVUo0jZWqbCWzAYuPkVduJUt3lALq8ZxZRlsAt6nizbZOx2DjJBxetrsLw8jdWUvytX176WGj3gsB3tjdUCUnsteVohhDfnt4nb58YW8BDnj4hlQ3hn5hdTNGc5LURQnPXgDje3Q0Aw2FmVBhJgLDrPA-0RZtbdpI";
            Token = "Bearer " + Token;
            connect.setRequestProperty("Authorization", Token);
            connect.connect();
            Log.d("VinhCNLog JsonParam: ", connect + "");
        } catch (IOException e1) {
            return "Error!";
        }
        try {
            int response_code = connect.getResponseCode();

            // kiểm tra kết nối ok
            if (response_code == HttpURLConnection.HTTP_OK) {
                // Đọc nội dung trả về
                InputStream input = connect.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else {
                return String.valueOf(response_code);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        } finally {
            connect.disconnect();
        }
    }


    /*
 write shareedPreferences and save it for all activity
*/
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

     /*
        read shareedPreferences and save it for all activity
    */

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
