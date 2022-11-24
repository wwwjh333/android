package com.jnu.exp7;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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
    MyAdapter mMyAdapter ;
    Boolean up = false;
    List<Book> mBookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        registerForContextMenu(mRecyclerView);
        // 构造一些数据
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.title = "图书名称" + i;
            book.content = "图书内容" + i;
            mBookList.add(book);
        }
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onPause() {
        super.onPause();
        up = true;//不可见的时候将刷新开启
    }

    @Override
    public void onResume() {
        super.onResume();
        if (up) {
            up = false;//刷新一次即可，不需要一直刷新
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position;
        position =mMyAdapter.getContextMenuPosition();
        switch (item.getItemId()) {
            case 0:
                mMyAdapter.delete(position);
            case 1:
                break;
            default:
                break;
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
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    setContextMenuPosition(holder.getLayoutPosition());
                    return false;
                }
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




