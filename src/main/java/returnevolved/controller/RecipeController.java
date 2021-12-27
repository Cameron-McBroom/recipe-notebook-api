package returnevolved.controller;

import io.swagger.models.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import returnevolved.dto.RecipeDto;
import returnevolved.model.Recipe;
import returnevolved.service.RecipeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecipeController {

    private final RecipeService recipeService;
    private final ModelMapper modelMapper;

    public RecipeController(RecipeService recipeService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/recipes")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        var recipes = recipeService.getAll()
                .stream()
                .map(recipe -> modelMapper.map(recipe, RecipeDto.class))
                .collect(Collectors.toList());


        return ResponseEntity.ok(recipes);
    }


    @GetMapping("/api/recipes/user/:id")
    public ResponseEntity<List<Recipe>> getRecipesForUser(Authentication authentication) {
        // Need to verify that the requesting user has permission
        var user = (UserDetails) authentication.getPrincipal();

        var recipes = recipeService.getRecipesForUser(user.getUsername());

        return ResponseEntity.ok(recipes);
    }




}
