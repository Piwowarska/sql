package pl.camp.it.rent.model;

import pl.camp.it.rent.model.view.Address;

import java.util.List;

public class Order {
    private int id;
    private List<OrderPosition> orderPositions;
    private Address address;
    private User user;
    private Status status;

    public Order(int id, List<OrderPosition> orderPositions, Address address, User user, Status status) {
        this.id = id;
        this.orderPositions = orderPositions;
        this.address = address;
        this.user = user;
        this.status = status;
    }

    public Order() {
    }
    public double calculateSum(){
        double result=0.0;
        for(OrderPosition orderPosition:this.orderPositions){
            result +=orderPosition.getPossitionQuantity()*orderPosition.getTale().getCena();
        }
        return Math.round(result*100)/100.0;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum Status{
        NEW,
        REALIZATION,
        DELIVERY,
        DONE
    }
}

