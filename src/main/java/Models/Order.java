package Models;

public class Order {
    private int id;
    private int Klient_id;

    public Order(int id, int Klient_id){
        this.id = id;
        this.Klient_id = Klient_id;
    }

    public int getKlient_id() {
        return Klient_id;
    }

    public void setKlient_id(int klient_id) {
        Klient_id = klient_id;
    }

    public int getId() {
        return id;
    }
}
