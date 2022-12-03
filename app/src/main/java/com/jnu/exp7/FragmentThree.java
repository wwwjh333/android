package com.jnu.exp7;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class FragmentThree extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three,container);
        MapView mMapView;
        mMapView = view.findViewById(R.id.bmapView);
        BaiduMap mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        LatLng cenpt =  new LatLng(22.255925, 113.541112);

        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                //要移动的点
                .target(cenpt)
                //放大地图到20倍
                .zoom(18)
                .build();

        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);

        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);


        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.ic_launcher);
        //构建MarkerOption，用于在地图上添加Marker，地图上的自定义的标签和
        OverlayOptions option = new MarkerOptions()
                .position(cenpt)
                .icon(bitmap);
        mBaiduMap.addOverlay(option);



        return view;
    }


}

