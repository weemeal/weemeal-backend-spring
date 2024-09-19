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

import com.spoonacular.client.model.SearchGroceryProductsByUPC200ResponseNutritionCaloricBreakdown
import com.spoonacular.client.model.SearchGroceryProductsByUPC200ResponseNutritionNutrientsInner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 *
 * @param nutrients
 * @param caloricBreakdown
 */


data class SearchGroceryProductsByUPC200ResponseNutrition(

    @Json(name = "nutrients")
    val nutrients: kotlin.collections.Set<SearchGroceryProductsByUPC200ResponseNutritionNutrientsInner>,

    @Json(name = "caloricBreakdown")
    val caloricBreakdown: SearchGroceryProductsByUPC200ResponseNutritionCaloricBreakdown

) {


}

