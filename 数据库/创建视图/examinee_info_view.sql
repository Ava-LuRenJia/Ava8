CREATE VIEW examinee_info_view AS
SELECT
    id AS examinee_id,
    userName,
    identity,
    password
FROM
    user
WHERE
    identity = '0';