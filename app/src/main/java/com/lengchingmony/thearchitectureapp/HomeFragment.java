package com.lengchingmony.thearchitectureapp;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private MyAdapter adapter;
    private ProductByCategory adapter2;
    private List<MyDataModel> dataList;
    private List<MyDataModel> categoryList;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Handler handler;
    private Runnable runnable;
    private int currentPage = 0;

    private ImageView moreButton;
    private ImageView moreButton2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize ViewPager2 and TabLayout
        viewPager = view.findViewById(R.id.viewPager);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);

        List<Integer> imageList = initializeImageList();
        viewPagerAdapter = new ViewPagerAdapter(getContext(), imageList);
        viewPager.setAdapter(viewPagerAdapter);

        // Attach TabLayout to ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {}).attach();

        // Set up auto-slide for ViewPager2
        setupAutoSlide();

        // Initialize RecyclerViews
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Initialize data lists
        initializeData();

        // Set up adapters
        setupAdapters();

        // Initialize and set OnClickListener for "See More" buttons
        moreButton = view.findViewById(R.id.moreButton);
        moreButton2 = view.findViewById(R.id.moreButton2);

        moreButton.setOnClickListener(v -> {
            // Replace the current fragment with the LatestFragment
            getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LatestFragment())
                .addToBackStack(null)
                .commit();
        });

        moreButton2.setOnClickListener(v -> {
            // Replace the current fragment with the PopularFragment
            getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new PopularFragment())
                .addToBackStack(null)
                .commit();
        });

        return view;
    }

    private List<Integer> initializeImageList() {
        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.h1);
        imageList.add(R.drawable.h2);
        imageList.add(R.drawable.h4);
        imageList.add(R.drawable.lib3);
        return imageList;
    }

    private void setupAutoSlide() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == viewPagerAdapter.getItemCount()) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 3000); // Change slide every 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    private void initializeData() {
        // Sample data for first RecyclerView
        dataList = new ArrayList<>();
        List<Integer> imageIds1 = new ArrayList<>();
        imageIds1.add(R.drawable.s1);
        imageIds1.add(R.drawable.s2);
        dataList.add(new MyDataModel("Sustainable Practice", "Meet your favorite architect", imageIds1, 12.4F));

        List<Integer> imageIds2 = new ArrayList<>();
        imageIds2.add(R.drawable.s2);
        dataList.add(new MyDataModel("Public Toilet", "Reimagination of Phnom Penh", imageIds2, 12.4F));

        List<Integer> imageIds3 = new ArrayList<>();
        imageIds3.add(R.drawable.s3);
        dataList.add(new MyDataModel("Affordable Housing", "The urban thinking of the house", imageIds3, 20.20F));

        List<Integer> imageIds4 = new ArrayList<>();
        imageIds4.add(R.drawable.h1);
        dataList.add(new MyDataModel("Title 4", "You can download free Car PNG images with transparent backgrounds from the largest collection on Pngtree. With these Car PNG images, ", imageIds4, 20.20F));

        List<Integer> imageIds5 = new ArrayList<>();
        imageIds5.add(R.drawable.h1);
        dataList.add(new MyDataModel("Title 5", "Description 5", imageIds5, 20.20F));

        // Sample data for second RecyclerView
        categoryList = new ArrayList<>();
        List<Integer> imageIdsCategory1 = new ArrayList<>();
        imageIdsCategory1.add(R.drawable.h1);
        categoryList.add(new MyDataModel("Explosion House", "The design of Explosion House started with the idea of an imaginative ideas toward a superhero called “Wonder Woman”. Since she'd be in Cambodia fighting crime, the design needed to reflect her Martial Combat skills and adapt to her existing lifestyle", imageIdsCategory1, 20.20F));

        List<Integer> imageIdsCategory2 = new ArrayList<>();
        imageIdsCategory2.add(R.drawable.lib5);
        categoryList.add(new MyDataModel("Community Center", "     WEAL COMMUNITY CENTER is a concept influenced by the content of field,\" which signify \"វាល\" in Cambodian language.", imageIdsCategory2, 20.20F));

        List<Integer> imageIdsCategory3 = new ArrayList<>();
        imageIdsCategory3.add(R.drawable.lib4);
        categoryList.add(new MyDataModel("Odi Super car", "You can download free Car PNG images with transparent backgrounds from the largest collection on Pngtree. With these Car PNG images, ", imageIdsCategory3, 20.20F));

        List<Integer> imageIdsCategory4 = new ArrayList<>();
        imageIdsCategory4.add(R.drawable.lib3);
        categoryList.add(new MyDataModel("Category 4", "Description D", imageIdsCategory4, 20.20F));

        List<Integer> imageIdsCategory5 = new ArrayList<>();
        imageIdsCategory5.add(R.drawable.h1);
        categoryList.add(new MyDataModel("Category 5", "Description E", imageIdsCategory5, 20.20F));
    }

    private void setupAdapters() {
        adapter = new MyAdapter(getContext(), dataList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {
            MyDataModel dataModel = dataList.get(position);
            openProductDetail(dataModel);
        });

        adapter2 = new ProductByCategory(getContext(), categoryList);  // Pass context here
        recyclerView2.setAdapter(adapter2);
        adapter2.setOnItemClickListener(position -> {
            MyDataModel dataModel = categoryList.get(position);
            openProductDetail(dataModel);
        });
    }

    private void openProductDetail(MyDataModel dataModel) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", dataModel.getTitle());
        bundle.putString("description", dataModel.getDescription());
        bundle.putFloat("price", dataModel.getPrice());
        bundle.putIntegerArrayList("imageList", new ArrayList<>(dataModel.getImageResIds()));
        fragment.setArguments(bundle);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable); // Stop the auto-slide when the view is destroyed
    }
}
