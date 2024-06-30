package com.lengchingmony.thearchitectureapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductByCategory  adapter;
    private List<MyDataModel> categoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_latest, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Initialize data lists
        initializeData();

        // Set up adapters
        setupAdapters();

        return view;
    }

    private void initializeData() {
        categoryList = new ArrayList<>();

        // Sample data for RecyclerView
        List<Integer> imageIds1 = new ArrayList<>();
        imageIds1.add(R.drawable.h1);
        imageIds1.add(R.drawable.super_car);
        categoryList.add(new MyDataModel("Explosion House", "The design of Explosion House started with the idea of an imaginative ideas toward a superhero called “Wonder Woman”. Since she'd be in Cambodia fighting crime, the design needed to reflect her Martial Combat skills and adapt to her existing lifestyle. The house is shaped to maximize her view in one direction, for uninterrupted focus and a consistent feeling no matter which room she's in. Despite the blocky exterior, the inside is surprisingly open, especially the main living area, which combines everything from cooking to relaxing.", imageIds1, 12.4F));

        List<Integer> imageIds2 = new ArrayList<>();
        imageIds2.add(R.drawable.lib2);
        categoryList.add(new MyDataModel("Serenity Library", "Download over 100 car png pictures for free on Unsplash. Browse high-quality photos of vehicles, wheels, roads, and more for commercial use.", imageIds2, 12.4F));

        List<Integer> imageIds3 = new ArrayList<>();
        imageIds3.add(R.drawable.lib4);
        categoryList.add(new MyDataModel("Private Reading Space", "Download over 190000 Cars PNG images for your design ideas from Pngtree, the largest collection of Cars PNG resources.", imageIds3, 20.20F));

        List<Integer> imageIds4 = new ArrayList<>();
        imageIds4.add(R.drawable.lib5);
        categoryList.add(new MyDataModel("Commnuity Center", "You can download free Car PNG images with transparent backgrounds from the largest collection on Pngtree. With these Car PNG images, ", imageIds4, 20.20F));

        List<Integer> imageIds5 = new ArrayList<>();
        imageIds5.add(R.drawable.h1);
        categoryList.add(new MyDataModel("Title 5", "Description 5", imageIds5, 20.20F));
    }

    private void setupAdapters() {
        adapter = new ProductByCategory(getContext(), categoryList);
        recyclerView.setAdapter(adapter);

        // Handle item click
        adapter.setOnItemClickListener(position -> {
            MyDataModel dataModel = categoryList.get(position);
            openProductDetail(dataModel);
        });
    }

    private void openProductDetail(MyDataModel dataModel) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        ProductDetailFragment fragment = new ProductDetailFragment();

        // Pass data to ProductDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("title", dataModel.getTitle());
        bundle.putString("description", dataModel.getDescription());
        bundle.putFloat("price", dataModel.getPrice());
        bundle.putIntegerArrayList("imageList", new ArrayList<>(dataModel.getImageResIds()));
        fragment.setArguments(bundle);

        // Replace fragment and add to back stack
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
