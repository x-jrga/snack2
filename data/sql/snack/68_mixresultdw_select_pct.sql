CREATE PROCEDURE MixResultDW_Select_Pct (
--
IN v_MixId INTEGER
--
)
MODIFIES SQL DATA DYNAMIC RESULT SETS 1 BEGIN ATOMIC
--
DECLARE result CURSOR
FOR
--
SELECT ROUND(SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93),1) AS calories,
       ROUND(SUM(fat*9) / SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93)*100,1) AS fat,
       ROUND(SUM(carbsdigestible*4) / SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93)*100,1) AS carbs,
       ROUND(SUM(protein*4) / SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93)*100,1) AS protein,
       ROUND(SUM(alcohol*6.93) / SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93)*100,1) AS alcohol,
       --Food quotient (FQ) calculated using the equation of Black et al
       --FQ for alcohol is 0.667
       ROUND(
       SUM(carbsdigestible*4) / SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93)*1.00 + 
       SUM(fat*9) / SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93)*0.71 + 
       SUM(protein*4) / SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93)*0.81 +
       SUM(alcohol*6.93) / SUM(fat*9 + carbsdigestible*4 + protein*4 + alcohol*6.93)*0.667
       ,2) AS fq
FROM mixresultdw
WHERE mixid = v_MixId;
--
OPEN result;
--
END;
/