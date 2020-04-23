package org.example.springfoodieapp.services;

import org.example.springfoodieapp.domain.Recipe;
import org.example.springfoodieapp.repositories.RecipeRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long id, MultipartFile file) {
        Recipe recipe = recipeRepository.findById(id).get();

        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i=0;
            for(byte b: file.getBytes()) {
                byteObjects[i++] = b;
            }
            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("image received");
    }
}
