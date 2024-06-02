package de.weemeal.backend.adapter.out

import org.springframework.data.repository.CrudRepository

interface RecipeRepository : CrudRepository<RecipeEntity, String>