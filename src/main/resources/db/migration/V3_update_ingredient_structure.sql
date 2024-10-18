-- 1. Erstellen einer neuen Tabelle für `ingredient_list_content`
CREATE TABLE ingredient_list_content
(
    content_id      UUID PRIMARY KEY,
    ingredient_name VARCHAR(255),
    unit            VARCHAR(50),
    amount          FLOAT,
    section_name    VARCHAR(255),
    content_type    VARCHAR(50) NOT NULL,
    position        INT         NOT NULL,
    recipe_id       UUID,
    CONSTRAINT fk_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id) ON DELETE CASCADE
);

-- 2. Migrieren der Daten von der `ingredient`-Tabelle zu `ingredient_list_content`
INSERT INTO ingredient_list_content (content_id, ingredient_name, unit, amount, content_type, position, recipe_id)
SELECT ingredient_id, ingredient_name, unit, amount, 'INGREDIENT' AS content_type, position, recipe_id
FROM ingredient;

-- 3. Löschen der alten `ingredient`-Tabelle
DROP TABLE ingredient;
