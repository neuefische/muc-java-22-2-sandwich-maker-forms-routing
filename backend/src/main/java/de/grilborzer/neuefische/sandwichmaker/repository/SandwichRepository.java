package de.grilborzer.neuefische.sandwichmaker.repository;

import de.grilborzer.neuefische.sandwichmaker.model.Sandwich;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SandwichRepository extends MongoRepository<Sandwich, String> { }
