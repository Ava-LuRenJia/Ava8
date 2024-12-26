CREATE VIEW enrollment_admin_info_view AS
SELECT 
    ea.enrollment_admin_id,
    ea.admin_real_name,
    ea.responsible_area,
    ei.enrollment_info_id,
    ei.exam_name,
    ei.start_time,
    ei.end_time,
    ei.exam_date,
    ei.admission_line
FROM
    enrollment_admin ea
        JOIN
    enrollment_info ei ON ea.enrollment_admin_id = ei.enrollment_admin_id;