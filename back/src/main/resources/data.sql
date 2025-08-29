-- MACHINE 설비
INSERT INTO MACHINE (machine_name, type) VALUES
('프레스', 1),
('용접', 2),
('도장', 3),
('조립', 4);

-- PRODUCT 제품
INSERT INTO PRODUCT (product_name) VALUES
('철판'),
('차체'),
('도어'),
('본넷'),
('범퍼');

-- WORKER 작업자
INSERT INTO WORKER (worker_name) VALUES
('홍길동'),
('김철수'),
('이영희'),
('박민수');

-- WORKSHEET 작업지시서
INSERT INTO WORKSHEET (amount, work_date, create_datetime, machine_id, product_id, worker_id) VALUES
(120, '2025-08-25', '2025-08-24 08:00:00', 1, 1, 1), -- 프레스: 철판, 홍길동
(90,  '2025-08-25', '2025-08-24 09:00:00', 2, 2, 2), -- 용접: 차체, 김철수
(60,  '2025-08-26', '2025-08-25 10:00:00', 2, 3, 3), -- 용접: 도어, 이영희
(75,  '2025-08-26', '2025-08-25 11:00:00', 3, 4, 4), -- 도장: 본넷, 박민수
(55,  '2025-08-27', '2025-08-26 08:30:00', 3, 5, 1), -- 도장: 범퍼, 홍길동
(45,  '2025-08-27', '2025-08-26 09:30:00', 4, 2, 2); -- 조립: 차체, 김철수

-- RESULT 작업 실적
INSERT INTO RESULT (worksheet_id, is_passed, complete_datetime) VALUES
(1, FALSE,  NOW()),
(2, FALSE,  NOW()),
(2, TRUE,  NOW()),
(3, FALSE,  NOW()),
(3, TRUE,  NOW()),
(3, TRUE,  NOW()),
(4, FALSE,  NOW()),
(4, TRUE,  NOW()),
(4, TRUE,  NOW()),
(4, TRUE,  NOW());