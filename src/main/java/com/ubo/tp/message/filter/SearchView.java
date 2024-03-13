package com.ubo.tp.message.filter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SearchView<T,K> extends JPanel {
    protected List<SearchViewObserver<K>> observers;
    public SearchView(LayoutManager layout){
        super(layout);
        this.observers = new ArrayList<>();
    }
    protected void notifyFilterChange(K filter){
        for(SearchViewObserver<K> o : this.observers){
            o.filterChanged(filter);
        }
    }

    public void addObserver(SearchViewObserver<K> o){
        this.observers.add(o);
    }
}
