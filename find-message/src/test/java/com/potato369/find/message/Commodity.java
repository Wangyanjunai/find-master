package com.potato369.find.message;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Commodity {

    private static final List<Commodity> commodityList = new ArrayList<>();

    static {
        Commodity commodity01 = new Commodity("苹果", new BigDecimal(12.00), 202);
        Commodity commodity02 = new Commodity("香蕉", new BigDecimal(10.00), 500);
        Commodity commodity03 = new Commodity("地瓜", new BigDecimal(4.00), 10000);
        Commodity commodity04 = new Commodity("西红柿", new BigDecimal(7.00), 1314);
        Commodity commodity05 = new Commodity("葡萄", new BigDecimal(20.00), 235);
        Commodity commodity06 = new Commodity("榴莲", new BigDecimal(50.00), 158);
        Commodity commodity07 = new Commodity("西瓜", new BigDecimal(5.00), 2342);
        commodityList.add(commodity01);
        commodityList.add(commodity02);
        commodityList.add(commodity03);
        commodityList.add(commodity04);
        commodityList.add(commodity05);
        commodityList.add(commodity06);
        commodityList.add(commodity07);
    }

    //商品名
    private String commodityName;
    //商品单价
    private BigDecimal commodityPrice;
    //销量
    private Integer commoditySales;

    public Commodity() {
    }

    public Commodity(String commodityName, BigDecimal commodityPrice, Integer commoditySales) {
        this.commodityName = commodityName;
        this.commodityPrice = commodityPrice;
        this.commoditySales = commoditySales;
    }

    public static void main(String[] args) {
        commodityList.stream().sorted(Comparator.comparing(Commodity::getCommoditySales).reversed()).limit(3).collect(Collectors.toList()).stream().forEach(System.out::println);
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getCommoditySales() {
        return commoditySales;
    }

    public void setCommoditySales(Integer commoditySales) {
        this.commoditySales = commoditySales;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "commodityName='" + commodityName + '\'' +
                ", commodityPrice=" + commodityPrice +
                ", commoditySales=" + commoditySales +
                '}';
    }
}

