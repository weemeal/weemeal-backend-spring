/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.spoonacular.client.model

import com.spoonacular.client.model.GuessNutritionByDishName200ResponseCalories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 *
 * @param calories
 * @param carbs
 * @param fat
 * @param protein
 * @param recipesUsed
 */


data class GuessNutritionByDishName200Response(

    @Json(name = "calories")
    val calories: GuessNutritionByDishName200ResponseCalories,

    @Json(name = "carbs")
    val carbs: GuessNutritionByDishName200ResponseCalories,

    @Json(name = "fat")
    val fat: GuessNutritionByDishName200ResponseCalories,

    @Json(name = "protein")
    val protein: GuessNutritionByDishName200ResponseCalories,

    @Json(name = "recipesUsed")
    val recipesUsed: kotlin.Int

) {


}

