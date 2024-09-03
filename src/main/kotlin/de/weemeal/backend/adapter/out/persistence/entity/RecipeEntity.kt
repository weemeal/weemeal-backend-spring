package de.weemeal.backend.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

//https://schema.org/Recipe
@Entity
@Table(name = "recipe")
data class RecipeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var recipeId: UUID?,
    var name: String? = null,
    var recipeYield: Int? = null,
    var recipeInstructions: String? = null,
    var ingredients: List<String>? = null,
)
