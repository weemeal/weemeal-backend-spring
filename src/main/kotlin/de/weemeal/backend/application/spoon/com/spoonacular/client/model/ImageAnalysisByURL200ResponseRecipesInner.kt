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


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 *
 * @param id
 * @param title
 * @param imageType
 * @param url
 */


data class ImageAnalysisByURL200ResponseRecipesInner(

    @Json(name = "id")
    val id: kotlin.Int,

    @Json(name = "title")
    val title: kotlin.String,

    @Json(name = "imageType")
    val imageType: kotlin.String,

    @Json(name = "url")
    val url: kotlin.String

) {


}

