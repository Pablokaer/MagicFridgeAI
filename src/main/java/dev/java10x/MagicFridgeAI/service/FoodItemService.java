package dev.java10x.MagicFridgeAI.service;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private final FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItem save(FoodItem foodItem){
        return repository.save(foodItem);
    }

    public List<FoodItem> list(){
        return repository.findAll();
    }

    public Optional<FoodItem> listById(Long id){
        return repository.findById(id);
    }


    public FoodItem update(Long id, FoodItem foodItem){
        Optional<FoodItem> foodExist = repository.findById(id);
        if (foodExist.isPresent()) {
            FoodItem update = foodExist.get();
            update.setName(foodItem.getName());
            update.setExpirationDate(foodItem.getExpirationDate());
            update.setQuantity(foodItem.getQuantity());
            update.setCategory(foodItem.getCategory());

            return repository.save(update);
        }
        return null;
    }


    public void  delete (Long id){
        repository.deleteById(id);
    }
}
