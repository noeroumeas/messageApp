package main.java.com.ubo.tp.message.filter;

import java.util.List;

public class FilterComponent<T extends Filterable> {
    protected FilteredElementsModel<T> filteredElementsModel;
    protected FilterController<T> filterController;
    protected SearchView<T> searchView;

    protected UnfilteredElementsModel<T> unfilteredElementsModel;
    public FilterComponent(SearchView<T> searchView){
        this.filteredElementsModel = new FilteredElementsModel<>();
        this.unfilteredElementsModel = new UnfilteredElementsModel<>();
        this.filterController = new FilterController<>(this.unfilteredElementsModel.getElements(), this.filteredElementsModel);
        this.unfilteredElementsModel.addObserver(filterController);
        this.searchView = searchView;
        searchView.addObserver(filterController);
    }

    public FilteredElementsModel<T> getFilteredElementsModel() {
        return filteredElementsModel;
    }

    public FilterController<T> getFilterController() {
        return filterController;
    }

    public UnfilteredElementsModel<T> getUnfilteredElementsModel() {
        return unfilteredElementsModel;
    }
}
