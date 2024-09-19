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

import com.spoonacular.client.model.MenuItemServings
import com.spoonacular.client.model.SearchGroceryProductsByUPC200ResponseNutrition

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 *
 * @param id
 * @param title
 * @param restaurantChain
 * @param price
 * @param spoonacularScore
 * @param nutrition
 * @param badges
 * @param breadcrumbs
 * @param generatedText
 * @param imageType
 * @param likes
 * @param servings
 */


data class MenuItem(

    @Json(name = "id")
    val id: kotlin.Int,

    @Json(name = "title")
    val title: kotlin.String,

    @Json(name = "restaurantChain")
    val restaurantChain: kotlin.String,

    @Json(name = "price")
    val price: java.math.BigDecimal?,

    @Json(name = "spoonacularScore")
    val spoonacularScore: java.math.BigDecimal?,

    @Json(name = "nutrition")
    val nutrition: SearchGroceryProductsByUPC200ResponseNutrition? = null,

    @Json(name = "badges")
    val badges: kotlin.collections.List<kotlin.String>? = null,

    @Json(name = "breadcrumbs")
    val breadcrumbs: kotlin.collections.List<kotlin.String>? = null,

    @Json(name = "generatedText")
    val generatedText: kotlin.String? = null,

    @Json(name = "imageType")
    val imageType: kotlin.String? = null,

    @Json(name = "likes")
    val likes: kotlin.Int? = null,

    @Json(name = "servings")
    val servings: MenuItemServings? = null

) {


}

