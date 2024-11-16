package com.example.DartsInventory.repositories;

import com.example.DartsInventory.entities.Dart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DartsRepository extends JpaRepository<Dart, Integer> {
}
