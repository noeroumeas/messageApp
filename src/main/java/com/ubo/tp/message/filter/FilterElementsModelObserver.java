package main.java.com.ubo.tp.message.filter;

import java.util.List;

public interface FilterElementsModelObserver<T> {
    public void elementsChanged(List<T> users);
    public void elementAdded(T u);

    public void elementRemoved(T u);
}
