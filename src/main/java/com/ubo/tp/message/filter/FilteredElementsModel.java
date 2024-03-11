package main.java.com.ubo.tp.message.filter;

import java.util.ArrayList;
import java.util.List;

public class FilteredElementsModel<T> {
    protected List<FilterElementsModelObserver<T>> observers;
    protected List<T> elements;

    public FilteredElementsModel(){
        this.observers = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    public void add(T e){
        this.elements.add(e);
        notifyElementAdded(e);
    }

    public List<T> getElements(){
        return new ArrayList<>(this.elements);
    }

    public void setElements(List<T> elements){
        this.elements = new ArrayList<>(elements);
        this.notifyElementsChanged();
    }

    public void addObserver(FilterElementsModelObserver<T> observer){
        this.observers.add(observer);
    }

    protected void notifyElementAdded(T e){
        for(FilterElementsModelObserver<T> o : this.observers){
            o.elementAdded(e);
        }
    }

    protected void notifyElementsChanged(){
        for(FilterElementsModelObserver<T> o : this.observers){
            o.elementsChanged(new ArrayList<>(this.elements));
        }
    }

}
