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
 * @param original
 * @param glycemicIndex
 * @param glycemicLoad
 */


data class ComputeGlycemicLoad200ResponseIngredientsInner(

    @Json(name = "id")
    val id: kotlin.Int,

    @Json(name = "original")
    val original: kotlin.String,

    @Json(name = "glycemicIndex")
    val glycemicIndex: java.math.BigDecimal,

    @Json(name = "glycemicLoad")
    val glycemicLoad: java.math.BigDecimal

) {


}

