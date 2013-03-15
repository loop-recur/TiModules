package com.looprecur.aqlibandroid;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.content.Intent;

import com.autoniq.vinscanner.Scanner;

public class ScanBarcodeActivity extends Scanner {
    private Button torch_button;
    private int lastTorch;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int mainLayoutId = getResources().getIdentifier("main", "layout", getPackageName());

        setContentView(mainLayoutId);

        int torchButtonId = getResources().getIdentifier("button_torch", "id", getPackageName());

        torch_button = (Button) findViewById(torchButtonId);
        torch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTorch(lastTorch != TORCH_ON);
            }
        });
    }

    @Override
    protected void onVinScanned(String vin) {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200L);
        Intent data = new Intent();
        data.putExtra("vin", vin);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    protected void onTorchStatusChanged(int status) {
        lastTorch = status;
        if (status == TORCH_DISABLED) {
            torch_button.setVisibility(View.INVISIBLE);
        } else {
            torch_button.setVisibility(View.VISIBLE);
            torch_button.setText(status == TORCH_ON ? "OFF" : "ON");
        }
    }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data);
  }

}
