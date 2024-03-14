package com.ubo.tp.message.filter;

/**
 * Classe abstraite d'un Filtre
 * @param <T> type du modele filtré
 * @param <K> Type de ce à quoi le modele sera comparé
 */
public abstract class Filter<T, K> {
    protected K filterElement;
    protected Filter(K filterElement){
        this.filterElement = filterElement;
    }
    public abstract boolean isFiltered(T element);

    public void setFilterElement(K filterElement) {
        this.filterElement = filterElement;
    }
}
