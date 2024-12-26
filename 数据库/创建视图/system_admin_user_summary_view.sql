CREATE VIEW system_admin_user_summary_view AS
SELECT 
    u.id,
    u.userName,
    u.identity,
    e.name AS examinee_name,
    e.gender AS examinee_gender,
    e.birth_date AS examinee_birth_date,
    ea.admin_real_name AS enrollment_admin_name,
    ea.responsible_area AS enrollment_admin_area,
    sa.admin_name AS system_admin_name,
    sa.department AS system_admin_department
FROM
    user u
        LEFT JOIN
    examinee e ON u.id = e.examinee_id
        LEFT JOIN
    enrollment_admin ea ON u.id = ea.enrollment_admin_id
        LEFT JOIN
    system_admin sa ON u.id = sa.admin_id;