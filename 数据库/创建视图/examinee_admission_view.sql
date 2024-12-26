CREATE VIEW examinee_admission_view AS
SELECT 
    e.examinee_id,
    e.name,
    a.is_admitted,
    a.admission_time
FROM
    examinee e
        JOIN
    admission_info a ON e.examinee_id = a.examinee_id;