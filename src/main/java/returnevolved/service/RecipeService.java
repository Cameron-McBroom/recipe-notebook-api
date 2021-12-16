package returnevolved.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import returnevolved.dto.RecipeDto;
import returnevolved.model.Recipe;
import returnevolved.repository.RecipeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public List<Recipe> getRecipesForUser(String username) {
       return recipeRepository.findByUser_Username(username);
    }

    public Recipe saveRecipe(Recipe recipeToSave) {
        return recipeRepository.save(recipeToSave);
    }



}
