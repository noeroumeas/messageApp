package main.java.com.ubo.tp.message.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterController<T extends Filterable> implements SearchViewObserver, FilterElementsModelObserver<T> {
    protected FilteredElementsModel<T> filteredElements;
    protected String filter;
    protected List<T> elements;
    public FilterController(List<T> elements, FilteredElementsModel<T> filteredElements) {
        this.filteredElements = filteredElements;
        this.elements = elements;
        this.filter = "";
        refreshViewList();
    }

    protected void refreshViewList(){
        List<T> filteredElements = new ArrayList<>();
        for(T e : this.elements){
            if(e.contains(this.filter)) {
                filteredElements.add(e);
            }
        }
        this.filteredElements.setElements(filteredElements);
    }

    @Override
    public void filterChanged(String filter) {
        this.filter = filter;
        refreshViewList();
    }

    @Override
    public void elementsChanged(List<T> users) {
        this.elements = new ArrayList<>(users);
        refreshViewList();
    }

    @Override
    public void elementAdded(T u) {
        this.elements.add(u);
        refreshViewList();
    }

    @Override
    public void elementRemoved(T u) {
        this.elements.remove(u);
        refreshViewList();
    }
}
