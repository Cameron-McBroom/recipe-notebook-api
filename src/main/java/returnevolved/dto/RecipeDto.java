package returnevolved.dto;

import returnevolved.model.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class RecipeDto {
    private UUID id;
    private String recipeTitle;
    private Set<RecipeNote> recipeNotes;
    private Set<Ingredient> ingredients;
    private List<InstructionStep> instructionSteps;
    private Difficulty difficulty;
    private List<Category> categories;

    private RecipeDto() {}

    public static RecipeDto make(Recipe recipe) {
        var dto = new RecipeDto();
        dto.id = recipe.getId();
        dto.recipeTitle = recipe.getRecipeTitle();
        dto.recipeNotes = recipe.getNotes();
        dto.ingredients = recipe.getIngredients();
        dto.instructionSteps = recipe.getInstructionSteps();
        dto.difficulty = recipe.getDifficulty();
        dto.categories = recipe.getCategories();
        return dto;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public Set<RecipeNote> getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(Set<RecipeNote> recipeNotes) {
        this.recipeNotes = recipeNotes;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<InstructionStep> getInstructionSteps() {
        return instructionSteps;
    }

    public void setInstructionSteps(List<InstructionStep> instructionSteps) {
        this.instructionSteps = instructionSteps;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
