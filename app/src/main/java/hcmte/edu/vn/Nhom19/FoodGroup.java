package hcmte.edu.vn.Nhom19;

import java.util.List;

public class FoodGroup {
    private String name;
    private List<Food> listFood;

    public FoodGroup(String name, List<Food> listFood) {
        this.name = name;
        this.listFood = listFood;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getListFood() {
        return this.listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }

}
