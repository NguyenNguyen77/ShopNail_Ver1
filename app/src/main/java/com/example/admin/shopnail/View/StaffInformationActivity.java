package com.example.admin.shopnail.View;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.shopnail.R;

public class StaffInformationActivity extends Activity implements View.OnClickListener {
    private ImageView imgAvatar;
    private Button btnChangeAvatar;
    private Button btnChangePassword;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_information);

        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.STAFF_INFO);

        btnChangeAvatar = (Button) findViewById(R.id.btn_change_avatar);
        btnChangePassword = (Button) findViewById(R.id.btn_change_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        imgAvatar = (ImageView) findViewById(R.id.img_avatar);


        btnChangeAvatar.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
        btnBack.setOnClickListener(this);

//        Display display = getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
//                getResources(),R.drawable.default_avatar),size.x,size.y,true);
//        imgAvatar.setImageBitmap(bmp);


    }

    @Override
    public void onClick(View view) {

    }
}
