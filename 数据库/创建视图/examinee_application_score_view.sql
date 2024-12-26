CREATE VIEW examinee_application_score_view AS
SELECT 
    a.application_id,
    a.examinee_id,
    a.application_time,
    a.exam_type,
    s.subject,
    s.score_value,
    s.exam_time
FROM
    application_info a
        JOIN
    score s ON a.examinee_id = s.examinee_id;