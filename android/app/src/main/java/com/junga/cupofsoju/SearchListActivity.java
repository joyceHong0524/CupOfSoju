package com.junga.cupofsoju;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.junga.cupofsoju.Adapter.RecycleDeco;
import com.junga.cupofsoju.Adapter.SearchAdapter;
import com.junga.cupofsoju.model.StoreData;

import java.util.ArrayList;

public class SearchListActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    ImageView ivSearch;
    FrameLayout flResult;
    EditText etSearchText;
    TextView tvResultText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlist);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final ArrayList<StoreData> storeInfo = new ArrayList<>();
        final SearchAdapter myAdapter = new SearchAdapter(storeInfo,this);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ivSearch = findViewById(R.id.search_search);
        flResult = findViewById(R.id.search_result);
        etSearchText =  findViewById(R.id.search_edit);
        tvResultText = findViewById(R.id.search_text);

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flResult.setLayoutParams(new TableLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 0, 1f));
                tvResultText.setText("\""+ etSearchText.getText().toString()+"\" 검색결과");
                myAdapter.clear();
                storeInfo.clear();
                db.collection("Store")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        StoreData sd = document.toObject(StoreData.class);
                                        if(sd.getLocation().contains(etSearchText.getText().toString())){
                                            storeInfo.add(sd);
                                            myAdapter.notifyDataSetChanged();
                                        }
                                        myAdapter.resetting(storeInfo);
                                    }
                                }
                            }
                        });

            }
        });




        db.collection("Store")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                storeInfo.add(document.toObject(StoreData.class));
                                myAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });


        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.addItemDecoration(new RecycleDeco(10));

    }
}
