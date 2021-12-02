package pl.camp.it.rent.database;

import pl.camp.it.rent.model.OrderPosition;

import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderID);
    List<OrderPosition> getOrderPositionByOrderId(int orderId);


}
