package ir.hamedandroid.volly_library_wia_developers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

import ir.hamedandroid.volly_library_wia_developers.R;

public class MainActivity extends AppCompatActivity {
    ImageView imgStringRequest, imgJsonRequest, imgImageRequest, imgCustomRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgStringRequest = findViewById(R.id.imgStringRequest);
        imgJsonRequest = findViewById(R.id.imgJsonRequest);
        imgImageRequest = findViewById(R.id.imgImageRequest);
        imgCustomRequest = findViewById(R.id.imgCustomRequest);


        imgStringRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StringRequestActivity.class);
                startActivity(intent);
            }
        });

        imgJsonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JsonRequestActivity.class);
                startActivity(intent);
            }
        });

        imgImageRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageRequestActivity.class);
                startActivity(intent);
            }
        });

        imgCustomRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomRequestActivity.class);
                startActivity(intent);
            }
        });

    }
}
