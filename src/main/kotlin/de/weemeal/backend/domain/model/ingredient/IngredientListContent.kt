package de.weemeal.backend.domain.model.ingredient

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.util.UUID

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "contentType"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = Ingredient::class, name = "INGREDIENT"),
    JsonSubTypes.Type(value = SectionCaption::class, name = "SECTION_CAPTION")
)
abstract class IngredientListContent(
    open val id: UUID? = UUID.randomUUID(),
    open val position: Int,
    open val contentType: ContentType
)