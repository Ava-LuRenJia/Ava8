CREATE VIEW exam_room_examinee_view AS
SELECT 
    e.examinee_id,
    e.name,
    e.gender,
    e.contact_number,
    era.exam_room_number,
    era.seat_number
FROM
    examinee e
        JOIN
    exam_room_allocation era ON e.examinee_id = era.examinee_id;