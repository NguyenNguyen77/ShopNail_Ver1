package com.example.admin.shopnail.View.ViewCartActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.admin.shopnail.Adapter.ViewProductAdapter;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.View.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends Activity {

    private ViewManager mViewManager = ViewManager.getInstance();
    EditText edtExtra;
    ListView listCart = null;
    List<ServicesOfShop> mList = null;
    ViewProductAdapter viewProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_view_product);
        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.VIEW_CART);

        edtExtra = findViewById(R.id.edt_Extra);
        listCart = findViewById(R.id.listView);
        List<ServicesOfShop> listService = new ArrayList<ServicesOfShop>();
        ServicesOfShop Manicure = new ServicesOfShop("aaaaaa", 17, "http//...");
        ServicesOfShop Gel_Manicure = new ServicesOfShop("bbbbbbbbb", 30, "http//...");
        ServicesOfShop Gel_Manicure_French_Tip = new ServicesOfShop("cccccccccc", 35, "http//...");
        listService.add(Manicure);
        listService.add(Gel_Manicure);
        listService.add(Gel_Manicure_French_Tip);

        viewProductAdapter = new ViewProductAdapter(this,listService);
        listCart.setAdapter(viewProductAdapter);


        edtExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtExtra.setCursorVisible(true);
            }
        });

        mViewManager.setActivity(this);
    }
}
