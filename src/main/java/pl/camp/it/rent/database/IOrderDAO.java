package pl.camp.it.rent.database;

import pl.camp.it.rent.model.Order;

import java.util.List;

public interface IOrderDAO {
    //List<Order> getOrders();
    void addOrder(Order order);
    List<Order> getOrderByUserLogin(String login);
}
