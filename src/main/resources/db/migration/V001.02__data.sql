INSERT INTO "overmods"."user" (username, email, password, color, avatar,
                               role, site_rating, registered_at, updated_at,
                               password_changed, banned, deleted)
VALUES
    ('admin', 'admin@overmods.org', '$2a$10$3E/ANd9vZMvcji07znpPw.EKHNITKN9T.bUWq4GqrO6uu9oXD1EDq', NULL, NULL,
     'ADMIN', NULL, '2024-05-09 16:09:49.695315', '2024-05-09 16:09:49.695315', '2024-05-09 16:09:49.695315',
     FALSE, FALSE);

INSERT INTO "overmods"."game" (title, short_title, logo)
VALUES
    ('Apex Legends','apex-legends','apex_icon.jpg'),
    ('Cyberpunk 2077','cyberpunk-2077','cyberpunk_2077_icon.webp'),
    ('Metro 2033','metro-2033','metro_2033_icon.png'),
    ('Minecraft','minecraft','minecraft_icon.png'),
    ('Overwatch 2','overwatch-2','overwatch_icon.png'),
    ('Red dead redemption 2','red-dead-redemption-2','rdr2_icon.png'),
    ('Sims 4','sims-4','sims_4_icon.png'),
    ('The witcher 3: Wild hunt','witcher-3-wild-hunt','witcher_3_icon.jpg');