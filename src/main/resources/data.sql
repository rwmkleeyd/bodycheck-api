-- 기본 회원
INSERT INTO users (id, email, password, nickname, role)
VALUES (1, 'demo@demo.com', '$2a$10$7L..hashed.bcrypt.value...', 'Demo', 'USER')
    ON DUPLICATE KEY UPDATE email=email;

-- 예시 Board
INSERT INTO boards (id, name, description, created_at, updated_at)
VALUES (1, '운동일지', '오늘 운동 기록 공유', NOW(), NOW())
    ON DUPLICATE KEY UPDATE name=name;

-- 예시 Post
INSERT INTO posts (id, board_id, author_id, title, content, created_at, updated_at)
VALUES (1, 1, 1, '벤치프레스 무게 공유', '오늘 80kg 5x5 성공!', NOW(), NOW())
    ON DUPLICATE KEY UPDATE title=title;
