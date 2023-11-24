package site.shoplist.Slist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import site.shoplist.Slist.models.Pharmacy;

public interface PharmRepository extends JpaRepository<Pharmacy, Integer> {
}
