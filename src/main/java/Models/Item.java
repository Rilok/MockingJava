package Models;

public class Item {
    private int id;
    private String nazwa;
    private double wartosc;

    public Item(int id, String nazwa, double wartosc){
        this.id = id;
        this.nazwa = nazwa;
        this.wartosc = wartosc;
    }


    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public int getId() {
        return id;
    }
}
