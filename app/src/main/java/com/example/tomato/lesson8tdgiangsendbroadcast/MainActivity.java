package com.example.tomato.lesson8tdgiangsendbroadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATA_A = "Xin Chào A";
    public static final String DATA_B = "Xin Chào Sub B";
    Button btnOpenA, btnOpenB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        btnOpenB.setOnClickListener(this);
        btnOpenA.setOnClickListener(this);
    }

    private void initView() {
        btnOpenA = findViewById(R.id.btn_open_a);
        btnOpenB = findViewById(R.id.btn_open_b);
    }

    private void openApplicationA() {
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putString("hello_A", DATA_A);
        intent.putExtras(bundle);
        intent.setAction("com.example.tomato.lesson8tdgiangsendbroadcast.CALL_A");

        sendBroadcast(intent);

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.tomato.lesson8tdgiangapplicationa");
        if (launchIntent != null) {
            startActivity(launchIntent);//null pointer check in case package name was not found
        }
    }

    private void openApplicationB() {
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putString("hello_B", DATA_B);
        intent.putExtras(bundle);
        intent.setAction("com.example.tomato.lesson8tdgiangsendbroadcast.CALL_SUB_B");

        sendBroadcast(intent);

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.tomato.lesson8tdgiangapplicationb");
        if (launchIntent != null) {
            startActivity(launchIntent);//null pointer check in case package name was not found
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_a:
                openApplicationA();
                break;
            case R.id.btn_open_b:
                openApplicationB();
                break;
            default:
                break;
        }
    }
}
