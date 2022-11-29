package com.jnu.final_work;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BookList extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter;
    private static final File sdpath= Environment.getExternalStorageDirectory();
    private static final File  file=new File(sdpath,"Library.txt");
    private Button functionButton;
    Book book1 = new Book("1","软件项目管理案例教程（第4版）","15","高手");
    Book book2 = new Book("2","创新工程实践","15","高手");
    Book book3 = new Book("3","信息安全数学基础（第2版）","15","高手");
    List<Book>  mBookList = new ArrayList<Book>(){{
        add(book1);
        add(book2);
        add(book3);
    }};


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.function_button)
        {
            Intent intent1=new Intent(BookList.this,Library_function.class);
            startActivity(intent1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(file));
            String readLine = "";
            int count =0;
            while((readLine = br.readLine()) != null){
                String[] list =readLine.split("-");
                if(list[0].equals(""))
                {
                    continue;
                }
                Book book=new Book(list[0],list[1],list[3],list[2]);
                mBookList.add(book);
                count++;
                if(count==10)
                {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(br!=null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        setContentView(R.layout.activity_booklist);
        mRecyclerView = findViewById(R.id.recyclerview);
        registerForContextMenu(mRecyclerView);
        functionButton =(Button)findViewById(R.id.function_button);
        functionButton.setOnClickListener(this);
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(BookList.this);
        mRecyclerView.setLayoutManager(layoutManager);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position;
        position = mMyAdapter.getContextMenuPosition();
        if (item.getItemId() == 0){
            mMyAdapter.delete(position);
            mRecyclerView.setAdapter(mMyAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(BookList.this);
            mRecyclerView.setLayoutManager(layoutManager);
        }
        return super.onContextItemSelected(item);
    }



    public  class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHoder> {

        private int position;
        public int getContextMenuPosition() { return position; }
        public void setContextMenuPosition(int position) { this.position = position; }


        @NonNull
        @Override
        public MyAdapter.MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(BookList.this, R.layout.item_list, null);
            return new MyViewHoder(view);
        }

        @Override
        public void onViewRecycled(MyAdapter.MyViewHoder holder) {
            holder.itemView.setOnLongClickListener(null);
            super.onViewRecycled(holder);
        }


        @Override
        public void onBindViewHolder(final MyAdapter.MyViewHoder holder, int position) {
            Book books = mBookList.get(position);
            holder.mTitleTv.setText(books.getName());
            holder.mID.setText(books.getID());
            holder.mAutor.setText(books.getAuthor());
            holder.mPrice.setText(books.getPrice());
            holder.itemView.setOnLongClickListener(v -> {
                setContextMenuPosition(holder.getLayoutPosition());
                return false;
            });
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }

        public void delete(int position)
        {
            mBookList.remove(position);
        }


        class MyViewHoder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
            TextView mTitleTv;
            TextView mAutor;
            TextView mID;
            TextView mPrice;

            public MyViewHoder(@NonNull View itemView) {
                super(itemView);
                mTitleTv = itemView.findViewById(R.id.title);
                mAutor = itemView.findViewById(R.id.author);
                mID = itemView.findViewById(R.id.ID);
                mPrice = itemView.findViewById(R.id.price);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("menu");
                menu.add(ContextMenu.NONE, 0, ContextMenu.NONE, "删除");
            }

        }
    }



}
