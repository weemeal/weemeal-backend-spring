ALTER TABLE ingredient
    ADD COLUMN position INTEGER DEFAULT 0 NOT NULL;

WITH numbered_ingredients AS (SELECT ingredient_id,
                                     ROW_NUMBER() OVER (PARTITION BY recipe_id ORDER BY ingredient_id) as position
                              FROM ingredient)
UPDATE ingredient
SET position = numbered_ingredients.position
FROM numbered_ingredients
WHERE ingredient.ingredient_id = numbered_ingredients.ingredient_id;
