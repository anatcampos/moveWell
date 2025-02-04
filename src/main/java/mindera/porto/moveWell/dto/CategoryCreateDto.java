package mindera.porto.moveWell.dto;

import jakarta.validation.constraints.Pattern;

public class CategoryCreateDto {

    @Pattern(regexp = "(?i)joelho|ombro|tornozelo|lombar|coxa", message = "Invalid area.")
    private String area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
