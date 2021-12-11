package returnevolved.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID id;

    private String recipeTitle;

    @Column(length = 1000)
    private String recipeStory;

    @OneToMany
    private Set<RecipeNote> notes;

    @OneToMany
    private Set<Ingredient> ingredients;

    @OneToMany
    private List<InstructionStep> instructionSteps;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    private User user;

    protected Recipe() {
    }

    private Recipe(Builder builder) {
        recipeTitle = builder.recipeTitle;
        recipeStory = builder.recipeStory;
        ingredients = builder.ingredients;
        notes = builder.notes;
        difficulty = builder.difficulty;
        categories = builder.categories;
    }

    public UUID getId() {
        return id;
    }

    public String getRecipeStory() {
        return recipeStory;
    }

    public void setRecipeStory(String recipeStory) {
        this.recipeStory = recipeStory;
    }

    public Set<RecipeNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<RecipeNote> notes) {
        this.notes = notes;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public List<InstructionStep> getInstructionSteps() {
        return instructionSteps;
    }

    public User getUser() {
        return user;
    }

    public static RecipeTitle builder() {
        return new Builder();
    }

    public interface RecipeOwner {
        RecipeTitle recipeOwner(User user);
    }

    public interface RecipeTitle {
        Build recipeTitle(String title);
    }

    public interface Build {
        Build categories(List<Category> categories);
        Build difficulty(Difficulty difficulty);
        Build recipeStory(String story);
        Build ingredients(Set<Ingredient> ingredients);
        Recipe build();
    }

    private static class Builder implements RecipeOwner, RecipeTitle, Build {

        private User user;
        private String recipeTitle;
        private String recipeStory;
        private Set<RecipeNote> notes;
        private Set<Ingredient> ingredients;
        private Difficulty difficulty;
        private List<Category> categories;


        @Override
        public RecipeTitle recipeOwner(User user) {
            this.user = user;
            return this;
        }

        @Override
        public Build recipeTitle(String title) {
            this.recipeTitle = title;
            return this;
        }

        @Override
        public Build categories(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        @Override
        public Build difficulty(Difficulty difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        @Override
        public Build recipeStory(String story) {
            this.recipeStory = story;
            return this;
        }

        @Override
        public Build ingredients(Set<Ingredient> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        @Override
        public Recipe build() {
            return new Recipe(this);
        }
    }

}
