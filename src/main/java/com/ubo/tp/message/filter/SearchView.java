package main.java.com.ubo.tp.message.filter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SearchView<T> extends JPanel {
    protected List<SearchViewObserver> observers;
    public SearchView(LayoutManager layout){
        super(layout);
        this.observers = new ArrayList<>();
    }
    protected void notifyFilterChange(String filter){
        for(SearchViewObserver o : this.observers){
            o.filterChanged(filter);
        }
    }

    public void addObserver(SearchViewObserver o){
        this.observers.add(o);
    }
}
