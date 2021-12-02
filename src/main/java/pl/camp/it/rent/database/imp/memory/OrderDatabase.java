package pl.camp.it.rent.database.imp.memory;


import org.springframework.stereotype.Repository;
import pl.camp.it.rent.database.IOrderDAO;
import pl.camp.it.rent.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrderDatabase implements IOrderDAO {
    private final List<Order> orders=new ArrayList<>();

    @Override
    public void addOrder(Order order){
        this.orders.add(order);
    }
    @Override
    public List<Order> getOrderByUserLogin(String login){
        List<Order> result=new ArrayList<>();
        for(Order order:this.orders){
            if(order.getUser().getLogin().equals(login)){
                result.add(order);
            }
        }
        return result;
    }
}
/*

public class OrderDatabase implements IOrderDAO {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public List<Order> getOrdersByUserLogin(String login) {
        List<Order> result = new ArrayList<>();
        for(Order order : this.orders) {
            if(order.getUser().getLogin().equals(login)) {
                result.add(order);
            }
        }
        return result;
    }
}

 */