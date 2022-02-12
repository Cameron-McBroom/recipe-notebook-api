package returnevolved.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import returnevolved.dto.RecipeDto;
import returnevolved.model.Recipe;
import returnevolved.model.User;
import returnevolved.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /** Checks whether a user is allowed to access a specific recipe */
    public boolean userCanAccessRecipe(User user, Recipe recipe) {
        //Check if the user owns the recipe
        if (recipe.getUser().equals(user)) {
            return true;
        }
        // Check if it has been shared with other user
        if (recipe.getSharedUsers().contains(user)) {
            return true;
        }

        return false;
    }

    /** Checks whether a user has permission to update an existing recipe
     * Currently only the creating user has permission but in the future other users might be able to
     * */
    public boolean userCanUpdateRecipe(User user, Recipe recipe) {
        return recipe.getUser().equals(user);
    }

    /** Gets all public recipes in the database */
    public List<Recipe> getAll() {
        return recipeRepository.findAllByPublicRecipeTrue();
    }

    public List<Recipe> getRecipesForUser(String username) {
       return recipeRepository.findByUser_Username(username);
    }

    public Optional<Recipe> getRecipeById(UUID id) {
        return recipeRepository.findById(id);
    }

    public Recipe saveRecipe(Recipe recipeToSave) {
        return recipeRepository.save(recipeToSave);
    }



}
