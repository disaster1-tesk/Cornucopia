package com.disaster.mode.behavior.chainofresposibility;

/**
 * @ClassName PurchaseRequst
 * @Description TODO
 * @Author Disaster
 * @Date 2021/7/3 16:38
 * @Version 1.0
 **/
public class PurchaseRequest {
    private int price;
    private String type;
    private int weight;

    public PurchaseRequest(int price, String type, int weight) {
        this.price = price;
        this.type = type;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "price=" + price +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
