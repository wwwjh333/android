package com.jnu.exp7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditBookActivity  extends AppCompatActivity implements View.OnClickListener{
    private EditText BookName;
    private Button yes;
    private Button no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookactivity);
        setTitle("修改书籍");
        init();
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.yes_button)
        {
            String bookName = BookName.getText().toString().trim();
            Intent intent=new Intent(EditBookActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.no_button)
        {
            Intent intent=new Intent(EditBookActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    private void init() {
        BookName= findViewById(R.id.EditBook);
        yes=findViewById(R.id.yes_button);
        no=findViewById(R.id.no_button);
    }
}
