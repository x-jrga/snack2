CREATE PROCEDURE Mix_Update_Other (
IN v_MixId INTEGER,
IN v_Model LONGVARCHAR,
IN v_Note LONGVARCHAR
)
MODIFIES SQL DATA BEGIN ATOMIC
UPDATE
Mix
SET
Model = v_Model,
Note = v_Note
WHERE
MixId = v_MixId;
END;
/