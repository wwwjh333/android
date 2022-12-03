package com.jnu.exp7;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ShopLoader {
    public List<Shop> listShop = new ArrayList<>();

    public static void download(String address,okhttp3.Callback callback)
    {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }


    public void parsonJson(Response response) throws IOException
    {
        String responseData=response.body().string();
        try {
            JSONObject object = new JSONObject(responseData);//获取json数据
            JSONArray jsonArray = object.getJSONArray("shops");//获取数据集名称为obj的数据
            Log.d("jsonArray数据输出：", String.valueOf(jsonArray));
            for (int i = 0; i < jsonArray.length();i++) {
                Shop novels = Shop.sectionData(jsonArray.getJSONObject(i));//把数据存在novels集合中
                if (novels != null){
                    listShop.add(novels);
                }
            }

        } catch (JSONException e) {
            System.out.println("错误");
        }
    }
}
