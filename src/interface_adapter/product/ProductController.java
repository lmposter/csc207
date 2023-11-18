package interface_adapter.product;

import use_case.productDetails.ProductInputBoundary;
import use_case.productDetails.ProductInputData;

public class ProductController {

    final ProductInputBoundary productUseCaseInteractor;
    public ProductController(ProductInputBoundary productUseCaseInteractor) {
        this.productUseCaseInteractor = productUseCaseInteractor;
    }

    public void execute(String pdID) {
        ProductInputData productInputData = new ProductInputData(pdID);
        productUseCaseInteractor.execute(productInputData);
    }
}
