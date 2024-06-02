package de.weemeal.backend.adapter.out

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Transient

@Entity
@Table(name = "recipe")
data class RecipeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var recipeId: String,

    var name: String? = null,
    var recipeYield: Int? = null,
    var recipeInstructions: String? = null,

    @Transient
    var ingredients: List<String>? = null,
)