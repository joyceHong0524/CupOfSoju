package com.junga.cupofsoju.Adapter;

<<<<<<< .merge_file_BJnVph
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
=======

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
>>>>>>> .merge_file_9Q0zKb
import com.junga.cupofsoju.R;
import com.junga.cupofsoju.model.StoreData;

import java.util.ArrayList;

<<<<<<< .merge_file_BJnVph
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

=======
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context  context;
    ArrayList<Drawable> drawables = new ArrayList<>();
    public static class SearchViewHodler extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvName;
        TextView tvAddress;
        TextView tvCall;

        SearchViewHodler(View view) {
            super(view);
            ivPicture = view.findViewById(R.id.item_image);
            tvName = view.findViewById(R.id.item_name);
            tvAddress =  view.findViewById(R.id.item_address);
            tvCall =  view.findViewById(R.id.item_call);

        }
    }

    private ArrayList<StoreData> storeInfo;

    public SearchAdapter(ArrayList<StoreData> storeInfo, Context context) {
        this.storeInfo = storeInfo;
        this.context = context;

        drawables.add(ContextCompat.getDrawable(context,R.drawable.store1));
        drawables.add(ContextCompat.getDrawable(context,R.drawable.store2));
        drawables.add(ContextCompat.getDrawable(context,R.drawable.store3));
        drawables.add(ContextCompat.getDrawable(context,R.drawable.store4));
        drawables.add(ContextCompat.getDrawable(context,R.drawable.store5));

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);

        return new SearchViewHodler(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        SearchViewHodler myViewHolder = (SearchViewHodler) holder;



        Glide.with(context).load(drawables.get(position%5)).optionalCircleCrop().into(myViewHolder.ivPicture);
        myViewHolder.tvName.setText(storeInfo.get(position).getName());
        myViewHolder.tvAddress.setText(storeInfo.get(position).getLocation());
        myViewHolder.tvCall.setText(storeInfo.get(position).getPhone());

//        myViewHolder.ivPicture.setBackground(new ShapeDrawable(new OvalShape()));
//        myViewHolder.ivPicture.setClipToOutline(true);

    }

    @Override
    public int getItemCount() {
        return storeInfo.size();
    }

    public  void resetting(ArrayList<StoreData>newlist){
        storeInfo = newlist;
        notifyDataSetChanged();
    }
    public void clear(){
        storeInfo.clear();
        notifyDataSetChanged();
>>>>>>> .merge_file_9Q0zKb
    }
}
