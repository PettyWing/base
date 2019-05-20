package com.komect.base;

import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyusheng on 2019/4/3.
 */

public abstract class BaseExpandAdapter<Bean> extends BaseExpandableListAdapter {

    protected final ArrayList<Bean> data = new ArrayList<>();

    public void setData(List<Bean> data) {
        this.data.clear();
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void setData(int index, Bean data) {
        if (data != null) {
            this.data.set(index, data);
        }
        notifyDataSetChanged();
    }

    /**
     * @param data
     */
    public void addData(List<Bean> data) {
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * @param bean
     */
    public void addDataOnHead(Bean bean) {
        this.data.add(0, bean);
        notifyDataSetChanged();
    }

    /**
     * @param index
     */
    public void removeData(int index) {
        this.data.remove(index);
        notifyDataSetChanged();
    }

    /**
     * @return
     */
    public List<Bean> getData() {
        return this.data;
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    public boolean isLastChild(int groupPosition, int childPosition) {
        return (getChildrenCount(groupPosition) - 1) == childPosition;
    }
}
