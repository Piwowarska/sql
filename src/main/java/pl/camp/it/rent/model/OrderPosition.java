package pl.camp.it.rent.model;

import java.awt.print.Book;

public class OrderPosition {
    private int id;
    private Tale tale;
    private int possitionQuantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tale getTale() {
        return tale;
    }

    public void setTale(Tale tale) {
        this.tale = tale;
    }

    public int getPossitionQuantity() {
        return possitionQuantity;
    }

    public void setPossitionQuantity(int possitionQuantity) {
        this.possitionQuantity = possitionQuantity;
    }
    public void increaseQuantity(){
        this.possitionQuantity++;
    }
}
