package com.junga.cupofsoju;


//Copyright 2019 Hanjanha Jo
//
//
//Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
//file except in compliance with the License. You may obtain a copy of the License at
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software distributed
//under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
//express or implied. See the License for the specific language governing permissions and limitations under the License.
//


import android.os.Bundle;
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
        final SearchAdapter myAdapter = new SearchAdapter(storeInfo, this);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ivSearch = findViewById(R.id.search_search);
        flResult = findViewById(R.id.search_result);
        etSearchText = findViewById(R.id.search_edit);
        tvResultText = findViewById(R.id.search_text);

//        ivSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flResult.setLayoutParams(new TableLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 0, 1f));
//                tvResultText.setText("\"" + etSearchText.getText().toString() + "\" 검색결과");
//                myAdapter.clear();
//                storeInfo.clear();
//                db.collection("Store")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                        StoreData sd = document.toObject(StoreData.class);
//                                        if (sd.getLocation().contains(etSearchText.getText().toString())) {
//                                            storeInfo.add(sd);
//                                            myAdapter.notifyDataSetChanged();
//                                        }
//                                        myAdapter.resetting(storeInfo);
//                                    }
//                                }
//                            }
//                        });
//
//            }
//        });


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

    @Override
    public void onBackPressed() {

        finish();
    }
}
