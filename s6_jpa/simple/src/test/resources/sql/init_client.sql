insert into post (id, title) values (1, 'Collections in Java');
insert into post (id, title) values (2, 'Concurrency in Java');
insert into post (id, title) values (3, 'IO VS NIO');
insert into post (id, title) values (4, 'Memes about Java');

insert into image (id, url, post_id) values (1, 'https://media.geeksforgeeks.org/wp-content/cdn-uploads/20200811210521/Collection-Framework-1.png', 1);
insert into image (id, url, post_id) values (2, 'https://static.javatpoint.com/images/java-collection-hierarchy.png', 1);
insert into image (id, url, post_id) values (3, 'https://upload.wikimedia.org/wikipedia/commons/a/ab/Java.util.Collection_hierarchy.svg', 1);
insert into image (id, url, post_id) values (4, 'https://miro.medium.com/v2/resize:fit:2000/1*3ETouv3NvjVqSGIOk0HEBw.png', 1);
insert into image (id, url, post_id) values (5, 'https://static.insales-cdn.com/images/products/1/7126/282409942/44611314.jpg', 2);
insert into image (id, url, post_id) values (6, 'https://media.licdn.com/dms/image/D4D12AQF7N8o637P98A/article-cover_image-shrink_720_1280/0/1659556383374?e=2147483647&v=beta&t=eobdYA0keZdDGdb1qwS8el58kWgS5yrEVAbmK5xO1Ms', 2);
insert into image (id, url, post_id) values (7, 'https://media.geeksforgeeks.org/wp-content/uploads/20200916182759/ConcurrentMapinJava.png', 2);
insert into image (id, url, post_id) values (8, 'https://miro.medium.com/v2/resize:fit:1358/1*LLO_5-Px3kq29E7oMfBNGg.png', 2);
insert into image (id, url, post_id) values (9, 'https://cdn.javarush.com/images/article/7f63efc7-40de-426e-80f0-50a194df2b90/800.webp', 3);
insert into image (id, url, post_id) values (10, 'https://habrastorage.org/files/1f1/35b/d83/1f135bd8390444fb83552aa4fb899fbf.png', 3);
insert into image (id, url, post_id) values (11, 'https://miro.medium.com/v2/resize:fit:587/1*vLDjHD6o_sxhSR79X0iWkg.png', 3);
insert into image (id, url, post_id) values (12, 'https://www.youtube.com/watch?v=dQw4w9WgXcQ', 4);

insert into comment (id, message, post_id) values (1, 'Cool', 1);
insert into comment (id, message, post_id) values (2, 'Nice', 1);
insert into comment (id, message, post_id) values (3, 'Great', 1);
insert into comment (id, message, post_id) values (4, 'Do you believe in God?', 1);

insert into comment (id, message, post_id) values (5, 'Cool', 2);
insert into comment (id, message, post_id) values (6, 'Nice', 2);
insert into comment (id, message, post_id) values (7, 'Great', 2);
insert into comment (id, message, post_id) values (8, 'Do you believe in God?', 2);

insert into comment (id, message, post_id) values (9, 'Cool', 3);
insert into comment (id, message, post_id) values (10, 'Nice', 3);
insert into comment (id, message, post_id) values (11, 'Great', 3);
insert into comment (id, message, post_id) values (12, 'Do you believe in God?', 3);

insert into comment (id, message, post_id) values (13, 'Cool', 4);
insert into comment (id, message, post_id) values (14, 'Nice', 4);
insert into comment (id, message, post_id) values (15, 'Great', 4);
insert into comment (id, message, post_id) values (16, 'Do you believe in God?', 4);

ALTER SEQUENCE comment_seq RESTART WITH 20;

