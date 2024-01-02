package io.vishal.dto;

public class EmployeeDTO {
    private String name;
    private boolean married;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, boolean married) {
        this.name = name;
        this.married = married;
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
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", married=" + married +
                '}';
    }
}
