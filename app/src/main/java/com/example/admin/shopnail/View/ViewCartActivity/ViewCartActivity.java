package com.example.admin.shopnail.View.ViewCartActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.shopnail.Adapter.ViewProductAdapter;
import com.example.admin.shopnail.Manager.BaseMethod;
import com.example.admin.shopnail.Model.ServicesOfShop;
import com.example.admin.shopnail.Presenter.ViewProductPresenter.ViewProductPresenter;
import com.example.admin.shopnail.R;
import com.example.admin.shopnail.View.NailActionBarGenerator;
import com.example.admin.shopnail.Manager.ViewManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.example.admin.shopnail.Manager.KeyManager.ARRAY_PRODUCT;
import static com.example.admin.shopnail.Manager.KeyManager.CLIENT_NAME;
import static com.example.admin.shopnail.Manager.KeyManager.CUSTOMER_PHONE_NUMBER;

public class ViewCartActivity extends Activity implements CartView, View.OnClickListener {

    private ViewManager mViewManager = ViewManager.getInstance();
    EditText edtExtra;
    ListView listCart = null;
    List<ServicesOfShop> mList = null;
    ViewProductAdapter viewProductAdapter;
    ViewProductPresenter mViewProductPresenter = new ViewProductPresenter(this, this);
    private Button mBtnConfirm;
    private Button mBtnBack;
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvDate;
    private  TextView tvTotalPrice;
    private ImageView imgAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_for_view_product);
        new NailActionBarGenerator().generate(this,
                NailActionBarGenerator.BarType.VIEW_CART);
        initView();
        mViewProductPresenter.showProductChoosed(getIntent().getStringExtra(ARRAY_PRODUCT));
//        List<ServicesOfShop> listService = mViewProductPresenter.getListProduct();
//        viewProductAdapter = new ViewProductAdapter(this, listService);
//        listCart.setAdapter(viewProductAdapter);
        edtExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtExtra.setCursorVisible(true);
            }
        });
        mViewManager.setActivity(this);
    }

    private void initView() {
        edtExtra = findViewById(R.id.edt_Extra);
        listCart = findViewById(R.id.listView);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        mBtnBack = (Button) findViewById(R.id.btn_go_back);
        tvName = findViewById(R.id.txt_name_customer);
        tvName.setText(BaseMethod.getDefaults(CLIENT_NAME, this));
        tvPhone = findViewById(R.id.txt_phone_customer);
        tvPhone.setText(BaseMethod.getDefaults(CUSTOMER_PHONE_NUMBER, this));
        tvDate = findViewById(R.id.txt_date_customer);
        tvTotalPrice = findViewById(R.id.txt_total);
        imgAdd = findViewById(R.id.img_add);
        imgAdd.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        mBtnBack.setOnClickListener(this);
        tvDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getActionBar().setIcon(R.drawable.ic_menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_menu_for_staff).setVisible(true);
        menu.findItem(R.id.action_my_customer).setVisible(true);
        menu.findItem(R.id.action_manage_staff).setVisible(true);
        menu.findItem(R.id.action_customer_service_history).setVisible(true);
        menu.findItem(R.id.action_staff_info).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_for_staff:
                mViewManager.setView(ViewManager.VIEW_KEY.MENU_FOR_STAFF);
                return true;
            case R.id.action_my_customer:
                mViewManager.setView(ViewManager.VIEW_KEY.MY_CUSTOMER);
                return true;
            case R.id.action_manage_staff:
                mViewManager.setView(ViewManager.VIEW_KEY.MANAGE_STAFF);
                return true;
            case R.id.action_customer_service_history:
                mViewManager.setView(ViewManager.VIEW_KEY.CUSTOMER_SERVICE_HISTORY);
                return true;
            case R.id.action_staff_info:
                mViewManager.setView(ViewManager.VIEW_KEY.STAFF_INFO);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mViewManager.handleBackScreen();
        mViewManager.finishActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                mViewProductPresenter.sendData();
                break;
            case R.id.btn_go_back:
                mViewManager.handleBackScreen();
                mViewManager.finishActivity(this);
                break;
            case R.id.img_add:
                addExtra();
                break;
            default:
                break;
        }
    }

    private void addExtra() {
        tvTotalPrice.setText(String.valueOf(getTotal() + getExtraPrice()));
    }

    @Override
    public void setAdapterProductChoosed(ViewProductAdapter viewProductAdapter) {
        listCart.setAdapter(viewProductAdapter);
    }

    @Override
    public void setTotalExpectExtra(int totalPrice) {
        tvTotalPrice.setText(String.valueOf(totalPrice));
    }

    @Override
    public int getTotal() {
        return Integer.parseInt(tvTotalPrice.getText().toString());
    }

    @Override
    public int getExtraPrice() {
        return !edtExtra.getText().toString().equals("") ? Integer.parseInt(edtExtra.getText().toString()) : 0;
    }

    @Override
    public String getDateOrder() {
        return tvDate.getText().toString();
    }
}
