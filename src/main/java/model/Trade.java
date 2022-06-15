package model;

import joinery.DataFrame;

public class Trade {

    private long time;
    private double price;

    public Trade(long time, double price) {
        super();
        this.time = time;
        this.price = price;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Trade [time=" + time + ", price=" + price + "]";
    }
}
