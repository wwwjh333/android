package com.jnu.exp7;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter;
    Book book1 = new Book("图书1","1");
    Book book2 = new Book("图书2","2");
    Book book3 = new Book("图书3","3");
    Book book4 = new Book("图书4","4");
    Book book5 = new Book("图书5","5");
    Book book6 = new Book("图书6","6");
    List<Book> mBookList = new ArrayList<Book>(){{
        add(book1);
        add(book2);
        add(book3);
        add(book4);
        add(book5);
        add(book6);
    }};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        registerForContextMenu(mRecyclerView);
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position;
        position = mMyAdapter.getContextMenuPosition();
        if (item.getItemId() == 0){
            mMyAdapter.delete(position);
            mRecyclerView.setAdapter(mMyAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerView.setLayoutManager(layoutManager);
       }
        else if(item.getItemId()==1) {
            Intent intent = new Intent(MainActivity.this, com.jnu.exp7.EditBookActivity.class);
            startActivity(intent);
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
            View view = View.inflate(MainActivity.this, R.layout.item_list, null);
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
            holder.mTitleTv.setText(books.title);
            holder.mTitleContent.setText(books.content);
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

        public void edit(int position,String bookName){
            mBookList.get(position).setTitle(bookName);
        }

        class MyViewHoder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
            TextView mTitleTv;
            TextView mTitleContent;

            public MyViewHoder(@NonNull View itemView) {
                super(itemView);
                mTitleTv = itemView.findViewById(R.id.title);
                mTitleContent = itemView.findViewById(R.id.content);
                itemView.setOnCreateContextMenuListener(this);
            }

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("menu");
                menu.add(ContextMenu.NONE, 0, ContextMenu.NONE, "删除");
                menu.add(ContextMenu.NONE, 1, ContextMenu.NONE, "修改");
            }

        }
    }



}




