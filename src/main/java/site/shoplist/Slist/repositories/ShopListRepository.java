package site.shoplist.Slist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import site.shoplist.Slist.models.ShoppingList;

public interface ShopListRepository extends JpaRepository<ShoppingList, Integer> {
}
