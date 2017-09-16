package com.example.eight.scannews.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.eight.scannews.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener
        //implements NavigationView.OnNavigationItemSelectedListener
{
    ActionBar actionBar;
    // NavigationView navigationView;
    private LinearLayout cleanCache;
    private LinearLayout about;
    private LinearLayout feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

/*
        navigationView = (NavigationView) findViewById(R.id.nav_setting);
        navigationView.setNavigationItemSelectedListener(this);
*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        cleanCache = (LinearLayout) findViewById(R.id.clean_cache);
        cleanCache.setOnClickListener(this);
        about = (LinearLayout) findViewById(R.id.about);
        about.setOnClickListener(this);
        feedback = (LinearLayout) findViewById(R.id.feedback);
        feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clean_cache:
                Snackbar.make(cleanCache, "已清除缓存", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Snackbar.make(about, "敬请期待", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.feedback:
                Snackbar.make(feedback, "敬请期待", Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }




/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clean_cache:
                Snackbar.make(navigationView, "已清除缓存", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,
                        new AboutFragment()).commit();
                break;
            case R.id.feedback:
                Snackbar.make(navigationView, "敬请期待", Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
*/
}
