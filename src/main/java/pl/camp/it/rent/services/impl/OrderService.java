package pl.camp.it.rent.services.impl;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.rent.database.IOrderDAO;
import pl.camp.it.rent.database.ITaleDAO;
import pl.camp.it.rent.database.imp.memory.OrderDatabase;
import pl.camp.it.rent.database.imp.memory.TaleDatabase;
import pl.camp.it.rent.model.*;
import pl.camp.it.rent.model.view.Address;
import pl.camp.it.rent.services.IOrderService;
import pl.camp.it.rent.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    ITaleDAO taleDatabase;

    @Autowired
    IOrderDAO orderDatabase;

    @Override
    public void confirmOrder(Address address) throws ExecutionControl.NotImplementedException {
        Cart cart=this.sessionObject.getCart();
        List<OrderPosition> orderPositions=cart.getPositionTales();

        for( OrderPosition orderPosition:orderPositions ) {
            Optional<Tale> taleBox = this.taleDatabase.getBookByTitle(orderPosition.getTale().getTitle());
            if (!taleBox.isPresent()) {
                return;
            }
            if (orderPosition.getPossitionQuantity() > taleBox.get().getQuantity()) {
                return;
            }
        }
            Order order=new Order();
            //Random random=new Random();
            //order.setId(random.nextInt(1000000));
            order.setOrderPositions(orderPositions);
            order.setAddress(address);
            order.setUser(this.sessionObject.getUser());//TODO nie ma skad wziac usera
            order.setStatus(Order.Status.NEW);
             this.orderDatabase.addOrder(order);


        for(OrderPosition orderPosition1:orderPositions){
           Optional<Tale> taleBox = this.taleDatabase.getBookByTitle(orderPosition1.getTale().getTitle());
           Tale tale=taleBox.get();
           tale.setQuantity(tale.getQuantity()-orderPosition1.getPossitionQuantity());
           this.taleDatabase.updateTale(tale);
        }
        cart.setPositionTales(new ArrayList<>());
        }

        @Override
        public List<Order> getOrdersForCurrentUser(){
        return this.orderDatabase.getOrderByUserLogin(this.sessionObject.getUser().getLogin());

        }
    }

