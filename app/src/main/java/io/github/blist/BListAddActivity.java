package io.github.blist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/* Modified at
    Nov 29, 2019 */
public class BListAddActivity extends AppCompatActivity {

    private EditText title;
    private EditText date;
    private EditText budget;
    private BListDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_list_add);

        title = findViewById(R.id.title_edit_text);
        date = findViewById(R.id.date_edit_text);
        budget = findViewById(R.id.budget_edit_text);

        db = new BListDB(this);
    }

    public void onBackButtonClick(View v) {
        Intent i = new Intent(getBaseContext(), BListActivity.class);
        startActivity(i);
    }

    public void onAddButtonClick(View v) {
        addBList(title.getText().toString(), date.getText().toString(),
                budget.getText().toString());
        Intent i = new Intent(getBaseContext(), BListActivity.class);
        startActivity(i);
    }

    private void addBList(String title, String date, String budget) {

        String sTitle = title;
        String sDate = date;
        String sBudget = budget;

        if (!sTitle.isEmpty()) {
            try {
                db.createBList(sTitle, sDate, sBudget);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}