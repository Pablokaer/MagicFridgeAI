package dev.java10x.MagicFridgeAI.repository;

import dev.java10x.MagicFridgeAI.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}
