package me.gfred.gads.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import me.gfred.gads.fragment.HoursFragment;
import me.gfred.gads.R;
import me.gfred.gads.adapter.SectionsPagerAdapter;
import me.gfred.gads.fragment.SkillIQFragment;
import me.gfred.gads.model.HoursEntry;
import me.gfred.gads.model.IQEntry;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = ListActivity.class.getSimpleName();

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        List<IQEntry> iqEntryList = getIntent().getParcelableArrayListExtra("SKILL_IQ");
        List<HoursEntry> hoursEntryList = getIntent().getParcelableArrayListExtra("HOURS");

        assert iqEntryList != null;
        Log.d(TAG, iqEntryList.get(0).getName());
        assert hoursEntryList != null;
        Log.d(TAG, hoursEntryList.get(0).getName());

        Pair<List<HoursEntry>, List<IQEntry>> items = new Pair<>(hoursEntryList, iqEntryList);

        viewPager = findViewById(R.id.view_pager);
        setViewPager(viewPager, items);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager, Pair<List<HoursEntry>, List<IQEntry>> items) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HoursFragment.newInstance(items.first), "LEARNING HOURS");
        adapter.addFragment(SkillIQFragment.newInstance(items.second), "SKILL IQ");
        viewPager.setAdapter(adapter);
    }

    public void submitProject(View view) {
        Intent intent = new Intent(ListActivity.this, SubmitActivity.class);
        startActivity(intent);
    }
}