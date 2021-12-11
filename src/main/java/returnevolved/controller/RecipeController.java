package returnevolved.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import returnevolved.dto.JsonResponse;
import returnevolved.dto.RecipeDto;
import returnevolved.model.Recipe;
import returnevolved.service.RecipeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/api/recipes")
    public JsonResponse<List<RecipeDto>> getAllRecipes() {
        var recipes = recipeService.getAll()
                .stream()
                .map(RecipeDto::make)
                .collect(Collectors.toList());

        return new JsonResponse<>(recipes);
    }


    @GetMapping("/api/recipes/user/:id")
    public JsonResponse<List<Recipe>> getRecipesForUser(Authentication authentication) {
        // Need to verify that the requesting user has permission
        var user = (UserDetails) authentication.getPrincipal();

        var recipes = recipeService.getRecipesForUser(user.getUsername());
    }




}
