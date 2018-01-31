package top.jplayer.baseprolibrary.ui;

import android.support.v7.widget.DividerItemDecoration;
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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.ModelContactCity;
import top.jplayer.baseprolibrary.ui.adapter.ContactCityAdapter;
import top.jplayer.baseprolibrary.utils.ComparatorLetter;
import top.jplayer.baseprolibrary.widgets.sidebar.PinnedHeaderDecoration;
import top.jplayer.baseprolibrary.widgets.sidebar.WaveSideBarView;

/**
 * 城市（联系人）列表
 */
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
        mSideBarView = contentView.findViewById(R.id.side_view);
        mRecyclerView = contentView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        final PinnedHeaderDecoration decoration = new PinnedHeaderDecoration();
        decoration.registerTypePinnedHeader(1, (parent, adapterPosition) -> true);
        mRecyclerView.addItemDecoration(decoration);

        Observable.just(1).subscribeOn(Schedulers.io())
                .map(o -> {
                    Type listType = new TypeToken<ArrayList<ModelContactCity>>() {
                    }.getType();
                    Gson gson = new Gson();
                    final List<ModelContactCity> list = gson.fromJson(ModelContactCity.DATA, listType);
                    Collections.sort(list, new ComparatorLetter());
                    return list;
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(modelContactCities -> {
                    mAdapterContactCity = new ContactCityAdapter(modelContactCities);
                    mRecyclerView.setAdapter(mAdapterContactCity);
                });

        mSideBarView.setOnTouchLetterChangeListener(letter -> {
            int pos = mAdapterContactCity.getLetterPosition(letter);

            if (pos != -1) {
                mRecyclerView.scrollToPosition(pos);
                LinearLayoutManager mLayoutManager =
                        (LinearLayoutManager) mRecyclerView.getLayoutManager();
                mLayoutManager.scrollToPositionWithOffset(pos, 0);
            }
        });
    }
}
