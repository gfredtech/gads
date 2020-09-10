package me.gfred.gads.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.gfred.gads.R;
import me.gfred.gads.adapter.SkillAdapter;
import me.gfred.gads.model.IQEntry;

public class SkillIQFragment extends Fragment {

    RecyclerView recyclerView;
    SkillAdapter adapter;

    public SkillIQFragment() {
    }

    public static SkillIQFragment newInstance(List<IQEntry> entries) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("entries", new ArrayList<>(entries));
        SkillIQFragment s = new SkillIQFragment();
        s.setArguments(args);
        return s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill, container, false);

        recyclerView = view.findViewById(R.id.rv_skill);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SkillAdapter(getContext(), getArguments().getParcelableArrayList("entries"));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
