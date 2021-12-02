package pl.camp.it.rent.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
private  List<OrderPosition> positionTales =new ArrayList<>();

    public List<OrderPosition> getPositionTales() {
        return positionTales;
    }

    public void setPositionTales(List<OrderPosition> positionTales) {
        this.positionTales = positionTales;
    }
    public double clculateSum(){
        double result=0.0;
        for(OrderPosition orderPosition:this.positionTales){
            result+=orderPosition.getPossitionQuantity()*orderPosition.getTale().getCena();
        }

        return Math.round(result*100)/100.0;
    }
}

