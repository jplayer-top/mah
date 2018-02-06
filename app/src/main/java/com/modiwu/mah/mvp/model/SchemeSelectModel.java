package com.modiwu.mah.mvp.model;

import com.modiwu.mah.mvp.MahServer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import top.jplayer.baseprolibrary.net.IoMainSchedule;
import top.jplayer.baseprolibrary.net.RetrofitManager;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.model
 */

public class SchemeSelectModel {

    public Observable<List<String>> requestStyleData() {

        return RetrofitManager.init().create(MahServer.class)
                .getStyleSelectBean()
                .map(selectBean -> selectBean.rows)
                .map(rowsBeans -> {
                    List<String> list = new ArrayList<>();
                    Observable.fromIterable(rowsBeans).subscribe(rowsBean -> list.add(rowsBean.cat_value));
                    return list;
                }).compose(new IoMainSchedule<>());

    }

    public Observable<List<String>> requestLocalData(String city_code) {

        return RetrofitManager.init().create(MahServer.class)
                .getLocalSelectBean(city_code)
                .map(selectBean -> selectBean.rows)
                .map(rowsBeans -> {
                    List<String> list = new ArrayList<>();
                    Observable.fromIterable(rowsBeans).subscribe(rowsBean -> list.add(rowsBean.area_name));
                    return list;
                }).compose(new IoMainSchedule<>());

    }

    public Observable<List<String>> requestTypeData() {
        return RetrofitManager.init().create(MahServer.class)
                .getTypeSelectBean()
                .map(selectBean -> selectBean.rows)
                .map(rowsBeans -> {
                    List<String> list = new ArrayList<>();
                    Observable.fromIterable(rowsBeans).subscribe(rowsBean -> list.add(rowsBean.cat_value));
                    return list;
                }).compose(new IoMainSchedule<>());

    }

    public Observable<List<String>> requestFloorData() {
        return RetrofitManager.init().create(MahServer.class)
                .getFloorSelectBean()
                .map(selectBean -> selectBean.rows)
                .map(rowsBeans -> {
                    List<String> list = new ArrayList<>();
                    Observable.fromIterable(rowsBeans).subscribe(rowsBean -> list.add(rowsBean.building_name));
                    return list;
                }).compose(new IoMainSchedule<>());

    }
}
