package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import dev.java10x.MagicFridgeAI.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private final FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    //Post
    @PostMapping("/create")
    public ResponseEntity<FoodItem> create(@RequestBody FoodItem foodItem){
        FoodItem saved = service.save(foodItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //Get
    @GetMapping("/list")
    public ResponseEntity<List<FoodItem>> list(){
        List<FoodItem> items = service.list();
        return ResponseEntity.ok(items);
    }

    @PutMapping("/list/{id}")
    public ResponseEntity<FoodItem> listById(@PathVariable Long id){
        Optional<FoodItem> item = service.listById(id);
        return item.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Update


    @PutMapping("/refresh/{id}")
    public ResponseEntity<FoodItem> update(@RequestBody FoodItem foodItem, @PathVariable Long id){
        return service.listById(id).map(itemExist -> {
            foodItem.setId(itemExist.getId());
            FoodItem atualizado = service.update(id, foodItem);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (service.listById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}