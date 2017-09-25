package com.example.eight.scannews.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eight.scannews.R;
import com.example.eight.scannews.utils.ChannelsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChannelManageActivity extends AppCompatActivity {

    private RecyclerView selectedChannels;
    private RecyclerView unselectedChannels;
    private ActionBar actionBar;

    private List<String> selectedList;
    private List<String> unselectedList;
    private ChannelsAdapter selectedAdapter;
    private ChannelsAdapter unselectedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_manage);
        actionBar = getSupportActionBar();
        actionBar.setTitle("频道管理");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manager_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.select_ok:
                SharedPreferences sp = getSharedPreferences("SETTING", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("selectedList");
                editor.putInt("selectedList", selectedList.size());
                if (selectedList.size() != 0) {
                    for (int i = 0; i < selectedList.size(); i++) {
                        editor.remove("selectedList" + i);
                        editor.putString("selectedList" + i, selectedList.get(i));
                    }
                }
                editor.remove("unselectedList");
                editor.putInt("unselectedList", unselectedList.size());
                if (unselectedList.size() != 0) {
                    for (int i = 0; i < unselectedList.size(); i++) {
                        editor.remove("unselectedList" + i);
                        editor.putString("unselectedList" + i, unselectedList.get(i));
                    }
                }
                editor.apply();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        selectedList = new ArrayList<>();
        unselectedList = new ArrayList<>();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("SETTING", MODE_PRIVATE);
        if (sp.getInt("selectedList", 0) == 0) {
            selectedList = NewsTabPageFragment.getNewsTab();
        } else {
            for (int i = 0; i < sp.getInt("selectedList", 0); i++) {
                selectedList.add(sp.getString("selectedList" + i, null));
            }
        }
        if (sp.getInt("unselectedList", 0) == 0) {
            //unselectedList = null;
        } else {
            for (int i = 0; i < sp.getInt("unselectedList", 0); i++) {
                unselectedList.add(sp.getString("unselectedList" + i, null));
            }
        }
        Log.e("-------->", "initData: " + selectedList);
        Log.e("---->--->", "initData: " + unselectedList);
    }

    private void initView() {
        selectedChannels = (RecyclerView) findViewById(R.id.selected_channels);
        unselectedChannels = (RecyclerView) findViewById(R.id.unselected_channels);
        initSelectedChannels();
        initUnselectedChannels();
    }

    private void initSelectedChannels() {
        GridLayoutManager manager = new GridLayoutManager(selectedChannels.getContext(), 3);
        selectedChannels.setLayoutManager(manager);

        selectedAdapter = new ChannelsAdapter(ChannelManageActivity.this);
        selectedAdapter.getData(selectedList);
        selectedChannels.setAdapter(selectedAdapter);

        selectedAdapter.setItemClickListener(new ChannelsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("-------->", "onItemClick: " + selectedList.get(position));
                unselectedList.add(selectedList.get(position));
                unselectedAdapter.getData(unselectedList);
                unselectedAdapter.notifyDataSetChanged();

                selectedList.remove(position);
                selectedAdapter.getData(selectedList);
                selectedAdapter.notifyDataSetChanged();

            }
        });
    }

    private void initUnselectedChannels() {
        GridLayoutManager manager = new GridLayoutManager(unselectedChannels.getContext(), 3);
        unselectedChannels.setLayoutManager(manager);

        unselectedAdapter = new ChannelsAdapter(ChannelManageActivity.this);
        unselectedAdapter.getData(unselectedList);
        unselectedChannels.setAdapter(unselectedAdapter);

        unselectedAdapter.setItemClickListener(new ChannelsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("-------->", "onItemClick: " + unselectedList.get(position));
                selectedList.add(unselectedList.get(position));
                selectedAdapter.getData(selectedList);
                selectedAdapter.notifyDataSetChanged();

                unselectedList.remove(position);
                unselectedAdapter.getData(unselectedList);
                unselectedAdapter.notifyDataSetChanged();

            }
        });
    }
}
