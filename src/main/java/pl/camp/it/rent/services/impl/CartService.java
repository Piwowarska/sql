package pl.camp.it.rent.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.rent.database.ITaleDAO;
import pl.camp.it.rent.database.imp.memory.TaleDatabase;
import pl.camp.it.rent.model.OrderPosition;
import pl.camp.it.rent.model.Tale;
import pl.camp.it.rent.services.ICartService;
import pl.camp.it.rent.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    ITaleDAO taleDatabase;

    @Resource
    SessionObject sessionObject;

    @Override
    public void addToCart(String title){
        Optional<Tale> bookTale1=taleDatabase.getBookByTitle(title);
        if(!bookTale1.isPresent()){
            return;
        }
for(OrderPosition orderPosition:sessionObject.getCart().getPositionTales()){
    if(orderPosition.getTale().getTitle().equals(title)){
    orderPosition.increaseQuantity();
    return;
    }

        }
OrderPosition orderPosition=new OrderPosition();
orderPosition.setTale(bookTale1.get());
orderPosition.setPossitionQuantity(1);
this.sessionObject.getCart().getPositionTales().add(orderPosition);



    }
}
