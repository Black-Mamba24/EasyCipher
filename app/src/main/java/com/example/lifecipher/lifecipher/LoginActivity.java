package com.example.lifecipher.lifecipher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by zhaiyi on 16/9/5.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText passView;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        passView = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Properties properties = new Properties();
                String p_password = null;

                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(getResources().getAssets().open("my.properties"));
                    properties.load(inputStreamReader);
                    p_password = properties.getProperty("password");

                    String password = passView.getText().toString();
                    String enPassword = Cipher.toString(Cipher.cipher(password));

                    if (enPassword.equals(p_password)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("password", enPassword);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
