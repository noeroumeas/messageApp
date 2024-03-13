package com.ubo.tp.message.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe controleur du filtre
 * @param <T> type du modele filtré
 * @param <K> Type de ce à quoi le modele sera comparé
 */
public class FilterController<T, K> implements SearchViewObserver<K>, FilterElementsModelObserver<T> {
    protected FilteredElementsModel<T> filteredElements;
    protected Filter<T, K> filter;
    protected List<T> elements;

    public FilterController(FilteredElementsModel<T> filteredElements, Filter<T, K> filter) {
        this.filteredElements = filteredElements;
        this.elements = new ArrayList<>();
        this.filter = filter;
        refreshViewList();
    }

    protected void refreshViewList(){
        List<T> filteredElements = new ArrayList<>();
        for(T e : this.elements){
            if(!this.filter.isFiltered(e)) {
                filteredElements.add(e);
            }
        }
        this.filteredElements.setElements(filteredElements);
    }

    @Override
    public void filterChanged(K filter) {
        this.filter.setFilterElement(filter);
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
