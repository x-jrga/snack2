CREATE PROCEDURE NutrientRatio_Select (
IN v_MixId INTEGER
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
DECLARE result CURSOR
FOR
SELECT
a.MixId,
a.Nutrient_Id_1,
a.Nutrient_Id_2,
a.RelationshipId,
b.Name as NutrientA,
c.Name as NutrientB,
a.A,
a.B
FROM
NutrientRatio a, Nutrient b, Nutrient c
WHERE
a.MixId = v_MixId
AND
a.Nutrient_Id_1 = b.NutrientId
AND
a.Nutrient_Id_2 = c.NutrientId;
--
OPEN result;
--
END;
/
