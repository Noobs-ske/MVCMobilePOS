package com.example.mvcmobilepos;

import java.util.ArrayList;

import com.example.mvcmobilepos.ModelCart;
import com.example.mvcmobilepos.ModelProducts;

public class Controller {

	private ArrayList<ModelProducts> myProducts = new ArrayList<ModelProducts>();
	private ModelCart myCart = new ModelCart();

	public ModelProducts getProducts(int pPosition) {

		return myProducts.get(pPosition);
	}

	public void setProducts(ModelProducts Products) {

		myProducts.add(Products);

	}

	public ModelCart getCart() {

		return myCart;

	}

	public int getProductsArraylistSize() {

		return myProducts.size();
	}

}
