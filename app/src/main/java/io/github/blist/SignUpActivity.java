package io.github.blist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/* Modified at
    Dec 5, 2019 */
public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
    }

    public void onSignUpButtonClick(View v) {
        Intent i = new Intent(getBaseContext(), BListActivity.class);
        startActivity(i);
    }

    public void onLoginTextButtonClick(View v) {
        Intent i = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(i);
    }
}