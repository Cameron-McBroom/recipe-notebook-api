package returnevolved.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import returnevolved.dto.RecipeDto;
import returnevolved.exception.CustomException;
import returnevolved.model.Recipe;
import returnevolved.service.RecipeService;
import returnevolved.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RecipeController(RecipeService recipeService, UserService userService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        var recipes = recipeService.getAll()
                .stream()
                .map(recipe -> modelMapper.map(recipe, RecipeDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(recipes);
    }


    @GetMapping("/mylist")
    public ResponseEntity<List<RecipeDto>> getRecipesForUser(Authentication authentication) {
        var user = (UserDetails) authentication.getPrincipal();

        var recipes = recipeService
                .getRecipesForUser(user.getUsername())
                .stream()
                .map(recipe -> modelMapper.map(recipe, RecipeDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/:id")
    public ResponseEntity<RecipeDto> getRecipeById(Authentication authentication, @PathVariable UUID id) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        var user = userService.search(userDetails.getUsername());

        var recipe = recipeService.getRecipeById(id).orElseThrow(() -> {
            throw CustomException
                    .status(HttpStatus.NOT_FOUND)
                    .error("recipe-not-found")
                    .message("No recipe was found with the id: " + id)
                    .build();
        });

        if (!recipeService.userCanAccessRecipe(user, recipe)) {
            throw CustomException
                    .status(HttpStatus.UNAUTHORIZED)
                    .error("cannot-access-recipe")
                    .message("You are not permitted to view this recipe. The recipe must be public or shared with you.")
                    .build();
        }

        return ResponseEntity.ok(modelMapper.map(recipe, RecipeDto.class));
    }

    @PostMapping
    public ResponseEntity<RecipeDto> upsertRecipe(Authentication authentication, @RequestBody RecipeDto recipeDto) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        var user = userService.search(userDetails.getUsername());

        Recipe recipe = modelMapper.map(recipeDto, Recipe.class);

        recipe.setUser(user);

        return ResponseEntity.ok(modelMapper.map(recipeService.saveRecipe(recipe), RecipeDto.class));
    }






}
