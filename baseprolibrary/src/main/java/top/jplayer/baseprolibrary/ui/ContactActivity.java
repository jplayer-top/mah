package top.jplayer.baseprolibrary.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.ModelContactCity;
import top.jplayer.baseprolibrary.ui.adapter.ContactCityAdapter;
import top.jplayer.baseprolibrary.utils.ComparatorLetter;
import top.jplayer.baseprolibrary.widgets.sidebar.PinnedHeaderDecoration;
import top.jplayer.baseprolibrary.widgets.sidebar.WaveSideBarView;


public class ContactActivity extends SuperBaseActivity {

    RecyclerView mRecyclerView;
    WaveSideBarView mSideBarView;

    private ContactCityAdapter mAdapterContactCity;

    @Override
    public void initSuperData(FrameLayout mFlRootView) {
        mFlRootView.addView(View.inflate(this, R.layout.activity_contact, null));
        initView();
    }

    private void initView() {
        mSideBarView = findViewById(R.id.side_view);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, new PinnedHeaderDecoration.PinnedHeaderCreator() {
            @Override
            public boolean create(RecyclerView parent, int adapterPosition) {
                return true;
            }
        });
        mRecyclerView.addItemDecoration(decoration);


        new Thread(new Runnable() {
            @Override
            public void run() {
                Type listType = new TypeToken<ArrayList<ModelContactCity>>() {
                }.getType();
                Gson gson = new Gson();
                final List<ModelContactCity> list = gson.fromJson(ModelContactCity.DATA, listType);
                Collections.sort(list, new ComparatorLetter());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapterContactCity = new ContactCityAdapter(list);
                        mRecyclerView.setAdapter(mAdapterContactCity);
                    }
                });
            }
        }).start();

        mSideBarView.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                int pos = mAdapterContactCity.getLetterPosition(letter);

                if (pos != -1) {
                    mRecyclerView.scrollToPosition(pos);
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(pos, 0);
                }
            }
        });
    }
}
