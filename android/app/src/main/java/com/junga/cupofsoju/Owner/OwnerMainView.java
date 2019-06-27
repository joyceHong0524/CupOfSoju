package com.junga.cupofsoju.Owner;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.junga.cupofsoju.R;
import com.junga.cupofsoju.model.StoreData;
import kotlin.jvm.internal.markers.KMutableMap;

import java.util.ArrayList;

public class OwnerMainView extends AppCompatActivity {
    TextView tvCount;
    TextView tvCashback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        tvCount = findViewById(R.id.owner_main_count);
        tvCashback = findViewById(R.id.owner_main_cashback);

        final String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        db.collection("Store")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                StoreData sd = document.toObject(StoreData.class);
                                if(sd.getEmail().equals(email)){
                                    tvCount.setText(sd.getSoju_count());
                                    tvCashback.setText(sd.getSoju_count()*800);
                                    break;
                                }

                            }
                        }
                    }
                });


    }
}
