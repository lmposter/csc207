package interface_adapter.orders;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.orders.OrderInputBoundary;
import use_case.orders.OrderInputData;
import use_case.orders.OrderInteractor;

/**
 * The LoginController class is responsible for handling user interface actions related to the login functionality.
 * It communicates with the use case through the LoginInputBoundary and processes user input.
 */
public class OrderController {

    private final OrderInputBoundary orderInteractor;

    /**
     * Constructs a LoginController with the provided LoginInputBoundary.
     *
     * @param orderInteractor The login use case interactor responsible for handling login logic.
     */
    public OrderController(OrderInputBoundary orderInteractor) {
        this.orderInteractor = orderInteractor;
    }
    public void switchPage(){
        orderInteractor.switchPage();
    }/**
     * Executes the login action triggered by the user interface.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public void execute(String username, String password) {
        // Create LoginInputData from user input
        OrderInputData orderInputData = new OrderInputData(username);

        // Execute the login process through the use case interactor
        orderInteractor.execute(orderInputData);
    }
}