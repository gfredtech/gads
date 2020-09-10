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
import me.gfred.gads.adapter.HoursAdapter;
import me.gfred.gads.model.HoursEntry;

public class HoursFragment extends Fragment {

    RecyclerView recyclerView;
    HoursAdapter adapter;

    public HoursFragment() { }

    public static HoursFragment newInstance(List<HoursEntry> entries) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("entries", new ArrayList<>(entries));
        HoursFragment h = new HoursFragment();
        h.setArguments(args);
        return h;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hours, container, false);

        recyclerView = view.findViewById(R.id.rv_hours);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HoursAdapter(getContext(), getArguments().getParcelableArrayList("entries"));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
