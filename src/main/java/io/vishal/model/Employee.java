package io.vishal.model;

public class Employee {
    private long id;
    private String name;
    private boolean married;

    public Employee() {
    }

    public Employee(long id, String name, boolean married) {
        this.id = id;
        this.name = name;
        this.married = married;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", married=" + married +
                '}';
    }
}
