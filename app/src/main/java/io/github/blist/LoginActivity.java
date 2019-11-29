package io.github.blist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/* Modified at
    Nov 29, 2019 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onSignUpTextButtonClick(View v) {
        Intent i = new Intent(getBaseContext(), SignUpActivity.class);
        startActivity(i);
    }
}