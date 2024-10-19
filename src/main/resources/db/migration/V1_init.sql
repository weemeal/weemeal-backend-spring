-- 1. Initiale Testdaten für die Rezepttabelle einfügen (5 Rezepte)
INSERT INTO recipe (recipe_id, name, recipe_yield, recipe_instructions)
VALUES ('d35a1a29-7d87-4f2d-bae1-63b243eaef9b', 'Spaghetti Bolognese', 4,
        'Kochen Sie die Spaghetti und bereiten Sie die Bolognesesauce zu.'),
       ('4a17f3c4-228e-4a3f-87d7-97ad2be4a9b4', 'Caesar Salad', 2,
        'Mischen Sie das Dressing und kombinieren Sie es mit Salat, Croutons und Hähnchen.'),
       ('a7c9b260-fec4-4f30-bb6e-d5e0eae57b18', 'Pancakes', 4,
        'Mischen Sie alle Zutaten und backen Sie die Pancakes in der Pfanne aus.'),
       ('aa748349-56c2-4a79-bfa3-d39272be4419', 'Chili con Carne', 6,
        'Braten Sie Hackfleisch an und geben Sie Bohnen, Tomaten und Chili hinzu.'),
       ('b217ed52-f827-4329-8ae9-c92f3a798daf', 'Lasagne', 4,
        'Schichten Sie Bolognese und Bechamelsauce zwischen Lasagneplatten und backen Sie alles.');

-- 2. Initiale Testdaten für Zutaten und Sektionen in der Tabelle ingredient_list_content einfügen
-- Spaghetti Bolognese (d35a1a29-7d87-4f2d-bae1-63b243eaef9b)
INSERT INTO ingredient_list_content (content_id, ingredient_name, unit, amount, section_name, content_type, position,
                                     recipe_id)
VALUES ('62b4bb8f-3838-4a1e-9ec0-bbb6bb4c6244', 'Spaghetti', 'g', 400, NULL, 'INGREDIENT', 1,
        'd35a1a29-7d87-4f2d-bae1-63b243eaef9b'),
       ('ad2b4a18-8aa7-4f9c-9376-d5a2769cbd09', 'Rinderhackfleisch', 'g', 500, NULL, 'INGREDIENT', 2,
        'd35a1a29-7d87-4f2d-bae1-63b243eaef9b'),
       ('7d0f6395-373f-4c14-b687-6712674a5f41', 'Tomatensauce', 'ml', 250, NULL, 'INGREDIENT', 3,
        'd35a1a29-7d87-4f2d-bae1-63b243eaef9b'),
       ('4b81f99a-9138-4aeb-bb93-d80d6f1dd342', NULL, NULL, NULL, 'Sauce Zubereitung', 'SECTION_CAPTION', 4,
        'd35a1a29-7d87-4f2d-bae1-63b243eaef9b'),
       ('a18d7b58-b5c6-4a4c-bfa5-4b3477d191d3', 'Zwiebel', 'Stück', 1, NULL, 'INGREDIENT', 5,
        'd35a1a29-7d87-4f2d-bae1-63b243eaef9b'),
       ('f4892a3c-88f8-4ef2-a99b-42e2e5c05313', 'Knoblauchzehe', 'Stück', 2, NULL, 'INGREDIENT', 6,
        'd35a1a29-7d87-4f2d-bae1-63b243eaef9b');

-- Caesar Salad (4a17f3c4-228e-4a3f-87d7-97ad2be4a9b4)
INSERT INTO ingredient_list_content (content_id, ingredient_name, unit, amount, section_name, content_type, position,
                                     recipe_id)
VALUES ('a16c6c11-bc52-4e38-8449-4cb3caa54094', 'Römersalat', NULL, 1, NULL, 'INGREDIENT', 1,
        '4a17f3c4-228e-4a3f-87d7-97ad2be4a9b4'),
       ('5b7f8cc5-6c92-45b3-9e72-55a5a2c7cb3a', 'Croutons', 'g', 50, NULL, 'INGREDIENT', 2,
        '4a17f3c4-228e-4a3f-87d7-97ad2be4a9b4'),
       ('5f7b65de-9c29-4a15-8f5f-f3e4347fdf3a', 'Parmesan', 'g', 30, NULL, 'INGREDIENT', 3,
        '4a17f3c4-228e-4a3f-87d7-97ad2be4a9b4'),
       ('587c0e68-d519-4c9e-8233-f1c2e1f9c76c', 'Hähnchenbrust', 'g', 200, NULL, 'INGREDIENT', 4,
        '4a17f3c4-228e-4a3f-87d7-97ad2be4a9b4'),
       ('ff48de3e-493a-4c0d-9610-8c292d54f4cc', NULL, NULL, NULL, 'Salat Zubereitung', 'SECTION_CAPTION', 5,
        '4a17f3c4-228e-4a3f-87d7-97ad2be4a9b4'),
       ('1742fbc1-4e34-4edb-8426-eecf4df046a3', 'Caesar Dressing', 'ml', 100, NULL, 'INGREDIENT', 6,
        '4a17f3c4-228e-4a3f-87d7-97ad2be4a9b4');

-- Pancakes (a7c9b260-fec4-4f30-bb6e-d5e0eae57b18)
INSERT INTO ingredient_list_content (content_id, ingredient_name, unit, amount, section_name, content_type, position,
                                     recipe_id)
VALUES ('f31c3e0f-fb15-487b-9c44-f5c0b2c6b614', 'Mehl', 'g', 300, NULL, 'INGREDIENT', 1,
        'a7c9b260-fec4-4f30-bb6e-d5e0eae57b18'),
       ('d91e8e58-2f57-46f1-b875-e7a1fbb30592', 'Milch', 'ml', 250, NULL, 'INGREDIENT', 2,
        'a7c9b260-fec4-4f30-bb6e-d5e0eae57b18'),
       ('7fc47795-35d5-4cf7-935d-96f5f1cb9a54', 'Ei', 'Stück', 2, NULL, 'INGREDIENT', 3,
        'a7c9b260-fec4-4f30-bb6e-d5e0eae57b18'),
       ('3091d6c8-b847-4fb3-9bb9-45d1bbdf6d25', NULL, NULL, NULL, 'Zubereitung der Pancakes', 'SECTION_CAPTION', 4,
        'a7c9b260-fec4-4f30-bb6e-d5e0eae57b18');

-- Chili con Carne (aa748349-56c2-4a79-bfa3-d39272be4419)
INSERT INTO ingredient_list_content (content_id, ingredient_name, unit, amount, section_name, content_type, position,
                                     recipe_id)
VALUES ('d7d82e71-2553-402f-939f-4a218ab97380', 'Hackfleisch', 'g', 500, NULL, 'INGREDIENT', 1,
        'aa748349-56c2-4a79-bfa3-d39272be4419'),
       ('f741be1d-9175-4f3f-96bb-1a204e8f196b', 'Kidneybohnen', 'g', 240, NULL, 'INGREDIENT', 2,
        'aa748349-56c2-4a79-bfa3-d39272be4419'),
       ('c29f05ab-cf60-4d85-9b3f-0e0604eb21f3', 'Tomaten', 'g', 400, NULL, 'INGREDIENT', 3,
        'aa748349-56c2-4a79-bfa3-d39272be4419'),
       ('e6b0578d-c132-4a28-93fa-08f2430f0f2e', NULL, NULL, NULL, 'Zubereitung Chili', 'SECTION_CAPTION', 4,
        'aa748349-56c2-4a79-bfa3-d39272be4419');

-- Lasagne (b217ed52-f827-4329-8ae9-c92f3a798daf)
INSERT INTO ingredient_list_content (content_id, ingredient_name, unit, amount, section_name, content_type, position,
                                     recipe_id)
VALUES ('bb7f0604-68a2-4377-9236-4e520f476d42', 'Lasagneplatten', 'Stück', 12, NULL, 'INGREDIENT', 1,
        'b217ed52-f827-4329-8ae9-c92f3a798daf'),
       ('13a835e4-6baf-451b-9241-2b5fbd843dee', 'Hackfleisch', 'g', 500, NULL, 'INGREDIENT', 2,
        'b217ed52-f827-4329-8ae9-c92f3a798daf'),
       ('82b04b17-e0eb-43b1-9001-05f8329d50db', 'Tomaten', 'g', 400, NULL, 'INGREDIENT', 3,
        'b217ed52-f827-4329-8ae9-c92f3a798daf'),
       ('efc7e6f4-196a-4f65-9bde-d51234f8f46e', NULL, NULL, NULL, 'Zubereitung der Lasagne', 'SECTION_CAPTION', 4,
        'b217ed52-f827-4329-8ae9-c92f3a798daf');
