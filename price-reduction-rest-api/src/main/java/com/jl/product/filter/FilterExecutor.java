package com.jl.product.filter;

import java.util.List;

import com.jl.product.vo.Product;

public class FilterExecutor {

	private List<IProductFilter> filterchain;

	private List<Product> snapshotList;

	public List<Product> getSnapshotList() {
		return snapshotList;
	}

	public void setSnapshotList(List<Product> snapshotList) {
		this.snapshotList = snapshotList;
	}

	public FilterExecutor(List<IProductFilter> filterchain) {
		this.filterchain = filterchain;
	}

	public List<Product> executeFilters(List<Product> products, PriceLabelType labelType, ProductSortBy sortBy) {
		setSnapshotList(products);

		for (IProductFilter filter : filterchain) {
			snapshotList = filter.meetCriteria(getSnapshotList(), labelType, sortBy);
			setSnapshotList(snapshotList);
		}
		return snapshotList;
	}

}
