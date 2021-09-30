package com.epam.task.domain;

public class SoftwareProperty {
    private String name;
    private String softwareType;
    private String version;
    private double size;
    private double cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoftwareType() {
        return softwareType;
    }

    public void setSoftwareType(String softwareType) {
        this.softwareType = softwareType;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "SoftwareProperty{" +
                "name='" + name + '\'' +
                ", softwareType='" + softwareType + '\'' +
                ", version='" + version + '\'' +
                ", size=" + size +
                ", cost=" + cost +
                '}';
    }
}
