package main.java.com.ubo.tp.message.filter;

public abstract class Filter<T, K> {

    protected K filterElement;
    public Filter(K filterElement){
        this.filterElement = filterElement;
    }
    public abstract boolean isFiltered(T element);

    public void setFilterElement(K filterElement) {
        this.filterElement = filterElement;
    }
}
