package com.junga.cupofsoju;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.junga.cupofsoju.Adapter.RecycleDeco;
import com.junga.cupofsoju.Adapter.SearchAdapter;
import com.junga.cupofsoju.Item.StoreItem;

import java.util.ArrayList;

public class SearchListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    ImageView search;
    FrameLayout flResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        search = findViewById(R.id.search_search);
        flResult = findViewById(R.id.search_result);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flResult.setLayoutParams(new TableLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 0, 1f));
            }
        });


        ArrayList<StoreItem> stordeInfo = new ArrayList<>();
        stordeInfo.add(new StoreItem("", "테스트","테스트","10000000"));
        stordeInfo.add(new StoreItem("", "테스트","테스트","10000000"));
        stordeInfo.add(new StoreItem("", "테스트","테스트","10000000"));
        stordeInfo.add(new StoreItem("", "테스트","테스트","10000000"));


        SearchAdapter myAdapter = new SearchAdapter(stordeInfo,this);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.addItemDecoration(new RecycleDeco(10));

    }
}
