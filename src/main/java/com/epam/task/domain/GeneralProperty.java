package com.epam.task.domain;

public class GeneralProperty {
    private double totalCost;
    private double totalSize;

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(double totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "GeneralProperty{" +
                "totalCost=" + totalCost +
                ", totalSize=" + totalSize +
                '}';
    }

    public GeneralProperty(double totalCost, double totalSize) {
        this.totalCost = totalCost;
        this.totalSize = totalSize;
    }
}
