package com.lengchingmony.thearchitectureapp;

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

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<CategoryItem> categoryItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Initialize the list and adapter
        categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(R.drawable.reedge, "ReEdge Architecture"));
        categoryItemList.add(new CategoryItem(R.drawable.sorsor, "Sorsor Architects"));
        categoryItemList.add(new CategoryItem(R.drawable.kurt, "KURT A&D"));
        categoryItemList.add(new CategoryItem(R.drawable.inner, "Innerspace Design Studio"));
        adapter = new CategoryAdapter(getContext(), categoryItemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
