package de.weemeal.backend.application

import de.darthkali.weemeal.spoonacular.api.DefaultApi
import de.darthkali.weemeal.spoonacular.api.IngredientsApi
import de.darthkali.weemeal.spoonacular.api.MealPlanningApi
import de.darthkali.weemeal.spoonacular.api.MenuItemsApi
import de.darthkali.weemeal.spoonacular.api.MiscApi
import de.darthkali.weemeal.spoonacular.api.ProductsApi
import de.darthkali.weemeal.spoonacular.api.RecipesApi
import de.darthkali.weemeal.spoonacular.api.WineApi
import de.weemeal.backend.application.config.ApiKeyInterceptor
import io.github.cdimascio.dotenv.dotenv
import okhttp3.OkHttpClient

class SpoonacularApi {
    var defaultApi: DefaultApi
    var ingredientsApi: IngredientsApi
    var mealPlanningApi: MealPlanningApi
    var menuItemsApi: MenuItemsApi
    var miscApi: MiscApi
    var productsApi: ProductsApi
    var recipesApi: RecipesApi
    var wineApi: WineApi

    init {
        val apiKey = System.getenv("SPOONACULAR_API_KEY") ?: dotenv()["SPOONACULAR_API_KEY"]

        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .build()

        defaultApi = DefaultApi(client = client)
        ingredientsApi = IngredientsApi(client = client)
        mealPlanningApi = MealPlanningApi(client = client)
        menuItemsApi = MenuItemsApi(client = client)
        miscApi = MiscApi(client = client)
        productsApi = ProductsApi(client = client)
        recipesApi = RecipesApi(client = client)
        wineApi = WineApi(client = client)
    }
}