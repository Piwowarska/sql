package pl.camp.it.rent.services;

import jdk.jshell.spi.ExecutionControl;
import pl.camp.it.rent.model.Order;
import pl.camp.it.rent.model.view.Address;

import java.util.List;

public interface IOrderService {
    void confirmOrder(Address address) throws ExecutionControl.NotImplementedException;
    List<Order> getOrdersForCurrentUser();
}
