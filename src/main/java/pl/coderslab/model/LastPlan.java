package pl.coderslab.model;

public class LastPlan {
    private String day_name;
    private String meal_name;
    private String recipe_name;
    private String recipe_description;
public LastPlan(){

}
    public LastPlan(String day_name, String meal_name, String recipe_name, String recipe_description) {
        this.day_name = day_name;
        this.meal_name = meal_name;
        this.recipe_name = recipe_name;
        this.recipe_description = recipe_description;
    }

    @Override
    public String toString() {
        return "LastPlan{" +
                "day_name='" + day_name + '\'' +
                ", meal_name='" + meal_name + '\'' +
                ", recipe_name='" + recipe_name + '\'' +
                ", recipe_description='" + recipe_description + '\'' +
                '}';
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public void setRecipe_description(String recipe_description) {
        this.recipe_description = recipe_description;
    }

    public String getDay_name() {
        return day_name;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public String getRecipe_description() {
        return recipe_description;
    }
}
