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

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class CustomScannerActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener {

    protected final String TAG = "CustomScannerActivity";

    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private ImageButton btnSetting,btnSwitchFlash;
    private Boolean switchFlashlightButtonCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scanner);

        switchFlashlightButtonCheck = true;

//        btnSetting = findViewById(R.id.btn_setting);
        btnSwitchFlash = findViewById(R.id.switch_flashlight);
        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);

//
        if (!hasFlash()) {
            btnSwitchFlash.setVisibility(View.GONE);
        }
        barcodeScannerView.setTorchListener(this);
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

        btnSwitchFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchFlashlightButtonCheck) {
                    barcodeScannerView.setTorchOn();
                } else {
                    barcodeScannerView.setTorchOff();
                }
            }
        });
    }


    protected String createTag() {
        return TAG;
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }



    /**
     * TorchListener
     */
    @Override
    public void onTorchOn() {
        btnSwitchFlash.setImageResource(R.drawable.ic_flash_on_black_24dp);
        switchFlashlightButtonCheck = false;
    }

    @Override
    public void onTorchOff() {
        btnSwitchFlash.setImageResource(R.drawable.ic_flash_off_black_24dp);
        switchFlashlightButtonCheck = true;
    }
}


