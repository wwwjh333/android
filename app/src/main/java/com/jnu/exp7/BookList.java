package com.jnu.exp7;


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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BookList extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mMyAdapter;
    private  static final File  file=new File(Environment.getExternalStorageDirectory().getPath() + "/Book.txt");
    Book book1 = new Book("软件项目管理案例教程（第4版）",R.drawable.book_2);
    Book book2 = new Book("创新工程实践",R.drawable.book_no_name);
    Book book3 = new Book("信息安全数学基础（第2版）",R.drawable.book_1);
    List<Book> mBookList = new ArrayList<Book>(){{
        add(book1);
        add(book2);
        add(book3);
    }};

    static {
        try {
            if(file.exists())
                System.out.println("文件已存在");
            else{
                file.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BufferedWriter bw=null;
        BufferedReader br=null;
//        try {
//            bw=new BufferedWriter(new FileWriter(file,true));//true代表将数据写入文件末尾处，而不是文件开始处
//            System.out.println("111");
//            bw.write("\r\n");//因为文件最后一行的末尾没有回车换行符，如果不先加回车换行符，就会直接在最后一行写，不会新增一行
//
//            bw.write(book2.getTitle()+"-"+book2.getCoverResourceId());
//            bw.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            if(bw!=null) {
//                try {
//                    bw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

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
                System.out.println(list[0]);
                int x =Integer.parseInt(list[1]);
                Book book=new Book(list[0],x);
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
        setContentView(R.layout.booklist);
        mRecyclerView = findViewById(R.id.recyclerview);
        registerForContextMenu(mRecyclerView);
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
        else if(item.getItemId()==1) {
            Book books = mBookList.get(position);
            String oldName = books.getTitle();
            Intent intent = new Intent(BookList.this, com.jnu.exp7.EditBookActivity.class);
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
            holder.mTitleTv.setText(books.title);
            holder.mImg.setImageDrawable(getResources().getDrawable(books.CoverResourceId));
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
            ImageView mImg;

            public MyViewHoder(@NonNull View itemView) {
                super(itemView);
                mTitleTv = itemView.findViewById(R.id.title);
                mImg = itemView.findViewById(R.id.img);
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




