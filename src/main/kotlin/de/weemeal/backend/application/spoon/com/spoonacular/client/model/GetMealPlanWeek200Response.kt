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

import com.spoonacular.client.model.GetMealPlanWeek200ResponseDaysInner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 *
 * @param days
 */


data class GetMealPlanWeek200Response(

    @Json(name = "days")
    val days: kotlin.collections.Set<GetMealPlanWeek200ResponseDaysInner>

) {


}

