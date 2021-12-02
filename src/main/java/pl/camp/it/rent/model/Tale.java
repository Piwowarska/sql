package pl.camp.it.rent.model;

public class Tale {
    private int id;
    private String title;
    private String cast;
    private String tag;
    private int quantity;
    private int cena;

    public Tale(int id,String title, String cast, String tag, int quantity, int cena) {
        this.id=id;
        this.title = title;
        this.cast = cast;
        this.tag = tag;
        this.quantity = quantity;
        this.cena = cena;
    }


    public Tale() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getQuantity() {
        return quantity;
    }

      public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }
}
