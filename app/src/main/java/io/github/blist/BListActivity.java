package io.github.blist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.tabs.TabLayout;

/* Modified at
    Nov 29, 2019 */
public class BListActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Toolbar toolbar;
    private BListDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_list);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new BListDB(this);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add) {
            Intent i = new Intent(getBaseContext(), BListAddActivity.class);
            startActivity(i);
        }

        if (id == R.id.remove_all) {
            try {
                db.deleteAllBList();
                recreate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager(ViewPager viewPager) {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ToDoFragment(), "To do");
        viewPagerAdapter.addFragment(new CompletedFragment(), "Completed");
        viewPager.setAdapter(viewPagerAdapter);

    }

}
