package returnevolved.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import returnevolved.dto.JsonResponse;
import returnevolved.model.Recipe;
import returnevolved.service.RecipeService;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/api/recipes")
    public JsonResponse<List<Recipe>> getAllRecipes() {
        return new JsonResponse<>(recipeService.getAll());
    }

}
