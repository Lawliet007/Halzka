package com.example.pc.halzka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlay, btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlay = findViewById(R.id.btnPlay);
        btnExit = findViewById(R.id.btnExit);
        btnPlay.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                goToGameActivity();
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }

    public void goToGameActivity(){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
}
