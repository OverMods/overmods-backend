-- test data fill migration

-- user
INSERT INTO "overmods"."user"
    (username, email, password, color, avatar,
    role, site_rating, registered_at, updated_at,
    password_changed, banned, deleted)
VALUES
    ('admin', 'admin@overmods.org', '$2a$10$3E/ANd9vZMvcji07znpPw.EKHNITKN9T.bUWq4GqrO6uu9oXD1EDq', NULL, NULL,
        'ADMIN', NULL, '2024-05-09 16:09:49.695315', '2024-05-09 16:09:49.695315', '2024-05-09 16:09:49.695315',
        FALSE, FALSE),
    ('modder', 'modder@overmods.org', '$2a$10$3E/ANd9vZMvcji07znpPw.EKHNITKN9T.bUWq4GqrO6uu9oXD1EDq', NULL, NULL,
        'MODDER', NULL, '2024-05-09 16:09:49.695315', '2024-05-09 16:09:49.695315', '2024-05-09 16:09:49.695315',
        FALSE, FALSE);

-- game
INSERT INTO "overmods"."game"
    (title, short_title, logo)
VALUES
    ('Apex Legends','apex-legends','apex_icon.jpg'),
    ('Cyberpunk 2077','cyberpunk-2077','cyberpunk_2077_icon.webp'),
    ('Metro 2033','metro-2033','metro_2033_icon.png'),
    ('Minecraft','minecraft','minecraft_icon.png'),
    ('Overwatch 2','overwatch-2','overwatch_icon.png'),
    ('Red dead redemption 2','red-dead-redemption-2','rdr2_icon.png'),
    ('Sims 4','sims-4','sims_4_icon.png'),
    ('The witcher 3: Wild hunt','witcher-3-wild-hunt','witcher_3_icon.jpg');

-- mod
INSERT INTO "overmods"."mod"
    (game,title,logo,author,author_title,rating,uploaded_at,description,
     game_version,mod_version,instruction,downloaded,file,file_size,deleted)
VALUES
    (4,'test Mod','_wtuEAOmS.png',2,NULL,4.6667,'2023-06-21 15:12:51','# Test description\n## test\n*test*\n**test**\n`test`',
        '1.0','1.0','# Test instruction\n',5,'7Q6KS2PMV.zip',16777368,FALSE),
    (8,'test Mod 2','_wtuEAOmS.png',2,NULL,3.3333,'2023-06-29 03:22:14','test description 2',
        '1.4','1.0','test instruction 2',0,'7Q6KS2PMV.zip',16777368,FALSE),
    (4,'test Mod 3','umXe9zmkA.png',2,NULL,1.5,'2023-06-28 21:24:08','test description 3',
        '1.5.2','1.0','test instruction 3',0,'Vh1kcbLiY.zip',16777368,FALSE);

-- mod_comment
INSERT INTO "overmods"."mod_comment"
    (mod,"user",commented_at,comment,deleted)
VALUES
    (1,2,'2023-06-24 11:55:35','test comment for test mod',FALSE),
    (1,2,'2023-06-29 03:11:23','test 1',FALSE),
    (1,2,'2023-06-29 03:11:41','test 2',FALSE),
    (1,2,'2023-06-29 03:15:54','test 3',FALSE),
    (1,2,'2023-07-02 04:40:07','test 4',FALSE),
    (1,2,'2023-07-02 04:41:17','test 5',FALSE),
    (1,2,'2023-07-02 04:43:05','test 6',FALSE),
    (1,2,'2023-07-02 04:43:51','test 7',FALSE),
    (1,2,'2023-07-02 04:44:52','test 8',FALSE),
    (1,2,'2023-07-02 04:45:48','test 9',FALSE),
    (1,2,'2023-07-02 04:46:41','test 10',TRUE),
    (1,2,'2023-07-02 04:49:55','test 11',FALSE),
    (1,2,'2023-07-02 04:50:29','test 12',FALSE);

-- mod_rating
INSERT INTO "overmods"."mod_rating"
    (mod,"user",rating)
VALUES
    (1,1,5),
    (1,2,4),
    (2,1,4),
    (2,2,4),
    (3,1,1),
    (3,2,5);
