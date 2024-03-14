package com.ubo.tp.message.filter;

public interface SearchViewObserver<K> {
    public void filterChanged(K filter);
}
