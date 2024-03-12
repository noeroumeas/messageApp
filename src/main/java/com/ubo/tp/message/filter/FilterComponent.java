package main.java.com.ubo.tp.message.filter;


public class FilterComponent<T, K> {
    protected FilteredElementsModel<T> filteredElementsModel;
    protected FilterController<T, K> filterController;
    protected SearchView<T> searchView;

    protected UnfilteredElementsModel<T> unfilteredElementsModel;
    public FilterComponent(SearchView<T> searchView, Filter<T, K> filter){
        this.filteredElementsModel = new FilteredElementsModel<>();
        this.unfilteredElementsModel = new UnfilteredElementsModel<>();
        this.filterController = new FilterController<>(this.filteredElementsModel, filter);
        this.unfilteredElementsModel.addObserver(filterController);
        this.searchView = searchView;
        searchView.addObserver(filterController);
    }

    public FilteredElementsModel<T> getFilteredElementsModel() {
        return filteredElementsModel;
    }

    public FilterController<T, K> getFilterController() {
        return filterController;
    }

    public UnfilteredElementsModel<T> getUnfilteredElementsModel() {
        return unfilteredElementsModel;
    }
}
