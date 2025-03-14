CREATE TABLE final_product
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    type             VARCHAR(255),
    name             VARCHAR(255),
    description      VARCHAR(255)                            NOT NULL,
    measurement_unit VARCHAR(255),
    quantity         DOUBLE PRECISION                        NOT NULL,
    expiration_date  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_finalproduct PRIMARY KEY (id)
);

CREATE TABLE ingredient
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name             VARCHAR(255),
    expiration_date  TIMESTAMP WITHOUT TIME ZONE,
    quantity         DOUBLE PRECISION                        NOT NULL,
    measurement_unit VARCHAR(255),
    type             VARCHAR(255),
    supplier_id      BIGINT                                  NOT NULL,
    CONSTRAINT pk_ingredient PRIMARY KEY (id)
);

CREATE TABLE ingredient_for_step
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    ingredient_id     BIGINT,
    recipe_process_id BIGINT,
    quantity_needed   DOUBLE PRECISION                        NOT NULL,
    CONSTRAINT pk_ingredientforstep PRIMARY KEY (id)
);

CREATE TABLE recipe
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    type        VARCHAR(255),
    CONSTRAINT pk_recipe PRIMARY KEY (id)
);

CREATE TABLE recipe_process
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    recipe_id    BIGINT                                  NOT NULL,
    step_number  INTEGER                                 NOT NULL,
    name         VARCHAR(255),
    description  VARCHAR(255),
    duration     BIGINT                                  NOT NULL,
    work_station VARCHAR(255),
    typeof_work  VARCHAR(255),
    CONSTRAINT pk_recipeprocess PRIMARY KEY (id)
);

CREATE TABLE supplier
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(255),
    type  VARCHAR(255),
    email VARCHAR(255),
    CONSTRAINT pk_supplier PRIMARY KEY (id)
);

CREATE TABLE tool
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_tool PRIMARY KEY (id)
);

CREATE TABLE tool_for_step
(
    recipe_process_id BIGINT NOT NULL,
    tool_id           BIGINT NOT NULL,
    CONSTRAINT pk_toolforstep PRIMARY KEY (recipe_process_id, tool_id)
);

ALTER TABLE recipe_process
    ADD CONSTRAINT uc_33a932dbac0febaa5b1b79217 UNIQUE (recipe_id, step_number);

ALTER TABLE ingredient_for_step
    ADD CONSTRAINT uc_a2445ff75668f8b6eecc11f56 UNIQUE (ingredient_id);

ALTER TABLE ingredient_for_step
    ADD CONSTRAINT FK_INGREDIENTFORSTEP_ON_INGREDIENT FOREIGN KEY (ingredient_id) REFERENCES ingredient (id);

ALTER TABLE ingredient_for_step
    ADD CONSTRAINT FK_INGREDIENTFORSTEP_ON_RECIPEPROCESS FOREIGN KEY (recipe_process_id) REFERENCES recipe_process (id);

ALTER TABLE ingredient
    ADD CONSTRAINT FK_INGREDIENT_ON_SUPPLIER FOREIGN KEY (supplier_id) REFERENCES supplier (id);

ALTER TABLE recipe_process
    ADD CONSTRAINT FK_RECIPEPROCESS_ON_RECIPEID FOREIGN KEY (recipe_id) REFERENCES recipe (id);

ALTER TABLE tool_for_step
    ADD CONSTRAINT fk_tooforste_on_recipe_process FOREIGN KEY (recipe_process_id) REFERENCES recipe_process (id);

ALTER TABLE tool_for_step
    ADD CONSTRAINT fk_tooforste_on_tool FOREIGN KEY (tool_id) REFERENCES tool (id);