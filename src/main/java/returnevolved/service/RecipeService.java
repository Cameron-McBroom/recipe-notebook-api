package returnevolved.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import returnevolved.model.Recipe;
import returnevolved.repository.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

}
