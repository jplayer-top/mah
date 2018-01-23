package top.jplayer.baseprolibrary.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2018/1/22.
 * top.jplayer.baseprolibrary.ui.adapter
 */

public class AdapterGradLayout extends DelegateAdapter.Adapter<AdapterGradLayout.MyViewholder> {
    private LayoutHelper helper;
    private Context context;

    public AdapterGradLayout(Context context, LayoutHelper helper) {
        this.helper = helper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }


    @Override
    public AdapterGradLayout.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdapterGradLayout.MyViewholder(LayoutInflater.from(context).inflate(R.layout.layout_test, parent, false));
    }

    @Override
    public void onBindViewHolder(AdapterGradLayout.MyViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    protected void onBindViewHolderWithOffset(AdapterGradLayout.MyViewholder holder, int position, int offsetTotal) {
        holder.text.setText(Integer.toString(offsetTotal));
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {

        private TextView text;

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MyViewholder(View itemView) {
            super(itemView);
            createdTimes++;
            text = itemView.findViewById(R.id.tvNum);
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}
