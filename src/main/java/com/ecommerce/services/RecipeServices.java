package com.ecommerce.services;

import com.ecommerce.models.Recipe;
import com.ecommerce.models.User;
import com.ecommerce.repository.RecipeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServices {

    @Autowired
    private RecipeRepository recipeRepository;

    @Operation
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a new project",
                    content = {@Content(
                            schema = @Schema(implementation = Recipe.class)
                    )}),
            @ApiResponse(responseCode = "400", description = "Issue with Request",
                    content = {@Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )})
    })
    @Parameter(name = "no arguments required ", description = "")
    public Recipe createRecipe(Recipe recipe, User user){
        Recipe rec = new Recipe();
        rec.setTitle(recipe.getTitle());
        rec.setImage(recipe.getImage());
        rec.setDescription(recipe.getDescription());
        rec.setUser(user);
        rec.setCreatedAt(LocalDateTime.now());
        return recipeRepository.save(rec );
    }

    public Recipe findRecipeById(Long id) throws Exception{
        Optional<Recipe> opt = recipeRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("recipe not found"+id);
    }

    public String deleteRecipe (Long id) throws Exception{
        recipeRepository.deleteById(id);
        return "Recipe Deleted Successfully" + id;
    }

    public Recipe updateRecipe(Recipe recipe,Long id){
        Recipe recipe1 =recipeRepository.save(recipe);
        return recipe1;
    }

    public List<Recipe> findAllRecipe(){
        return recipeRepository.findAll();
    }

    public Recipe likeRecipe(Long recipeId,User user) throws Exception {
        Recipe recipe = findRecipeById(recipeId);
        if(recipe.getLikes().contains(user.getId())){
            recipe.getLikes().remove(user.getId());
        } else{
            recipe.getLikes().add(user.getId());
        }
        return recipeRepository.save(recipe);
    }
}
