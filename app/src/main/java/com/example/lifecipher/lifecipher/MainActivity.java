package com.example.lifecipher.lifecipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by zhaiyi on 16/9/5.
 */
public class MainActivity extends AppCompatActivity {

    private static final int MAX_BYTE = 128;
    private EditText keyView;
    private EditText valueView;
    private Button calButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        keyView = (EditText) findViewById(R.id.keyView);
        valueView = (EditText) findViewById(R.id.valueView);
        calButton = (Button) findViewById(R.id.cal_button);

        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = getIntent().getStringExtra("password");
                String key = keyView.getText().toString();
                try {
                    //在不知道密码的情况下无法破译
                    byte[] enBytes = Cipher.cipher(key + password);
                    StringBuffer result = new StringBuffer();
                    if (enBytes.length >= 6) {
                        for (int i = 0; i < 6; i++) {
                            int x = (int) enBytes[i] + MAX_BYTE;
                            int r = (int) (x / 25.6);
                            result.append(r);
                        }
                        valueView.setText(result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
