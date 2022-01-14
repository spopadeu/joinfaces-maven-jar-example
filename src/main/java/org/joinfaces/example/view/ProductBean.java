package org.joinfaces.example.view;

import org.joinfaces.example.entities.Product;
import org.joinfaces.example.repositories.ProductRepository;
import org.joinfaces.example.util.MessageUtil;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@Component
@ViewScoped

public class ProductBean {

    private List<Product> products;

    private Product selectedProduct;

    private List<Product> selectedProducts;

    private ProductRepository productRepository;


    @Inject
    public ProductBean(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        this.products = productRepository.findAll();
    }

    public void openNew() {
        this.selectedProduct = new Product();
    }

    public void saveProduct() {
        if (this.selectedProduct.getCode() == null) {
            this.selectedProduct.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.products.add(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        this.products.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void addToCart() {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product: " + getSelectedProduct().getName() + " added to cart"));
        this.selectedProduct = null;
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void onRowSelect(SelectEvent event) {
        Object[] msgParms = {((Product) event.getObject()).getName()};
        MessageUtil.addInfoMessage("product.selected",
                msgParms);
    }

    public void onRowUnselect(UnselectEvent event) {
        Object[] msgParms = {((Product) event.getObject()).getName()};
        MessageUtil.addInfoMessage("product.unselected",
                msgParms);
    }


}
