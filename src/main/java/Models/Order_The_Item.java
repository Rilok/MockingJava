package Models;

public class Order_The_Item {
    private int zamowienieId;
    private int przedmiotId;

    public Order_The_Item(int zamowienieId, int przedmiotId){
        this.zamowienieId = zamowienieId;
        this.przedmiotId = przedmiotId;
    }


    public int getZamowienieId() {
        return zamowienieId;
    }

    public void setZamowienieId(int zamowienieId) {
        this.zamowienieId = zamowienieId;
    }

    public int getPrzedmiotId() {
        return przedmiotId;
    }

    public void setPrzedmiotId(int przedmiotId) {
        this.przedmiotId = przedmiotId;
    }
}
