package returnevolved;

import org.springframework.stereotype.Component;
import returnevolved.model.Difficulty;
import returnevolved.model.Recipe;
import returnevolved.model.Role;
import returnevolved.model.User;
import returnevolved.service.RecipeService;
import returnevolved.service.UserService;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DatabaseRunner implements CommandLineRunner {


    private UserService userService;
    private RecipeService recipeService;

    public DatabaseRunner(UserService userService, RecipeService recipeService) {
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @Override
    public void run(String... params) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@email.com");
        admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

        userService.signup(admin);

        User client = new User();
        client.setUsername("client");
        client.setPassword("client");
        client.setEmail("client@email.com");
        client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

        Recipe chickenCurry = Recipe.builder()
                .recipeTitle("ChickenCurry")
                .recipeStory("This was a nice curry that my father used to make when I was a kid.")
                .difficulty(Difficulty.MODERATE)
                .build();

        recipeService.saveRecipe(chickenCurry);


        userService.signup(client);
    }

}
