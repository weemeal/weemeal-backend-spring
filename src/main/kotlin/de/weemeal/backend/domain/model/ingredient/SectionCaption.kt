package de.weemeal.backend.domain.model.ingredient

import java.util.UUID

data class SectionCaption(
    override var id: UUID? = UUID.randomUUID(),
    override var position: Int = 0,
    override var contentType: ContentType = ContentType.SECTION_CAPTION,
    var sectionName: String? = null
) : IngredientListContent(id, position, contentType)