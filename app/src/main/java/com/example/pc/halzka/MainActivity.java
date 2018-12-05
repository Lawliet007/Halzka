package com.example.pc.halzka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlayHuman, btnExit, btnPlayComputer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlayHuman = findViewById(R.id.btnPlayHuman);
        btnExit = findViewById(R.id.btnExit);
        btnPlayHuman.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnPlayComputer = findViewById(R.id.btnPlayComputer);
        btnPlayComputer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlayHuman:
                goToGameActivity(1);
                break;
            case R.id.btnPlayComputer:
                goToGameActivity(2);
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }

    public void goToGameActivity(Integer code){
//        Intent intent = new Intent(this,GameActivityNew.class);
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("code",code);
        startActivity(intent);
    }
}
