package com.ecommerce.controllers;

import com.ecommerce.models.Recipe;
import com.ecommerce.models.User;
import com.ecommerce.services.RecipeServices;
import com.ecommerce.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {


    @Autowired
    private UserServices userServices;


    @Autowired
    private RecipeServices recipeServices;

    @PostMapping("/api/create-recipe/{userId}")
    public Recipe createRecipe(@RequestBody Recipe recipe, @PathVariable Long userId){
        User user = userServices.findUserById(userId);
        Recipe recipe1 = recipeServices.createRecipe(recipe,user);
        return recipe1;
    }

    @PutMapping ("/api/update-recipe/{id}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id){
        Recipe recipe1 = recipeServices.updateRecipe(recipe,id);
        return recipe1;
    }


    @GetMapping("/api/recipes")
    public List<Recipe> getAllRecipes(){
        return recipeServices.findAllRecipe();
    }

    @DeleteMapping("/api/delete-recipe/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
        recipeServices.deleteRecipe(recipeId);
        return "Recipe Deleted Successfully" + recipeId;
    }

    @PutMapping ("/api/update-recipe/{id}/user/{userId}")
    public Recipe likeRecipe(@PathVariable Long recipeId, @PathVariable Long userId) throws Exception {
        User user = userServices.findUserById(userId);
        Recipe recipe1 = recipeServices.likeRecipe(recipeId,user);
        return recipe1;
    }

}
