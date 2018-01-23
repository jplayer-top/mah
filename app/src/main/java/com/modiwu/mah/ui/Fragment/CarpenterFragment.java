package com.modiwu.mah.ui.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.modiwu.mah.R;
import com.modiwu.mah.base.BaseFragment;
import com.modiwu.mah.ui.adapter.CarpenterAdapter;
import com.modiwu.mah.ui.adapter.DockerAdapter;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;
import top.jplayer.baseprolibrary.listener.NetNavigationBarListener;

/**
 * Created by Obl on 2018/1/19.
 * com.modiwu.mah.ui.Fragment
 */

public class CarpenterFragment extends BaseFragment {
    protected RecyclerView mRecyclerView1;
    protected RecyclerView mRecyclerView2;

    @Override
    public int initLayout() {
        return R.layout.fragment_carpenter;
    }

    @Override
    protected void initData(View rootView) {
        mMultipleStatusView = rootView.findViewById(R.id.multiplestatusview);
        final NavigationTabBar navigationTabBar = rootView.findViewById(R.id.ntb);
        mRecyclerView1 = rootView.findViewById(R.id.recyclerView1);
        mRecyclerView2 = rootView.findViewById(R.id.recyclerView2);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.pic_06);
        list.add(R.drawable.pic_06);
        list.add(R.drawable.pic_06);
        list.add(R.drawable.pic_06);
        list.add(R.drawable.pic_06);
        initRecyclerView1(list);
        initRecyclerView2(list);
        bottomBar(navigationTabBar);
    }

    private void initRecyclerView2(ArrayList<Integer> list) {
        mRecyclerView2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        DockerAdapter adapter = new DockerAdapter(list);
        adapter.addHeaderView(View.inflate(getContext(), R.layout.adapter_home_body_toshop, null));
        mRecyclerView2.setAdapter(adapter);
    }

    private void initRecyclerView1(ArrayList<Integer> list) {
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView1.setAdapter(new CarpenterAdapter(list));
    }

    /**
     * 设置底部栏
     */
    private void bottomBar(NavigationTabBar navigationTabBar) {

        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        String[] titleArrs = new String[]{"匠", "器"};
        int[] drawArrs = new int[]{R.drawable.main_home, R.drawable.main_scheme, R.drawable.main_charpenter, R.drawable.main_me};
        for (int i = 0; i < titleArrs.length; i++) {
            models.add(new NavigationTabBar.Model.Builder(
                    getResources().getDrawable(drawArrs[i]),
                    getResources().getColor(top.jplayer.baseprolibrary.R.color.black))
                    .title(titleArrs[i]).build());
        }
        navigationTabBar.setModels(models);
        navigationTabBar.setOnTabBarSelectedIndexListener(new NetNavigationBarListener() {
            @Override
            public void onceSelected(NavigationTabBar.Model model, int index) {
                mRecyclerView1.setVisibility(index == 1 ? View.GONE : View.VISIBLE);
                mRecyclerView2.setVisibility(index == 0 ? View.GONE : View.VISIBLE);
            }
        });
        navigationTabBar.setModelIndex(0, true);
    }


}
