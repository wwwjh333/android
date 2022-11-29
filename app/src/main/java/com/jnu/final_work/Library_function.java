package com.jnu.final_work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Library_function extends AppCompatActivity implements View.OnClickListener {
    private Button addButton;//增添书籍按钮
    private Button findButton;//查询书籍按钮
    private Button alterButton;//修改书籍信息按钮
    private Button cancelButton;//删除书籍按钮
    private Button fanhuiButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_function);
        init();
        addButton.setOnClickListener(this);
        alterButton.setOnClickListener(this);
        findButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        fanhuiButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_button)
        {
            Intent intent1=new Intent(Library_function.this,Dialog_Activity.class);
            startActivity(intent1);
        }
        else if(v.getId()==R.id.find_button)
        {
            Intent intent3=new Intent(Library_function.this,find.class);
            startActivity(intent3);
        }
        else if(v.getId()==R.id.alter_button)
        {
            Intent intent2=new Intent(Library_function.this,Alter_activity.class);
            startActivity(intent2);
        }
        else if(v.getId()==R.id.cancel_button)
        {
            Intent intent4=new Intent(Library_function.this,cancel.class);
            startActivity(intent4);
        }
        else if(v.getId()==R.id.fanhui_button)
        {
            Intent intent5=new Intent(Library_function.this,BookList.class);
            startActivity(intent5);
        }

    }
    private void init(){
        addButton=(Button)findViewById(R.id.add_button);
        findButton=(Button)findViewById(R.id.find_button);
        alterButton=(Button)findViewById(R.id.alter_button);
        cancelButton=(Button)findViewById(R.id.cancel_button);
        fanhuiButton=(Button)findViewById((R.id.fanhui_button));
    }
}