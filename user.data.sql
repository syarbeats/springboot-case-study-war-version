
please replace password field with your Bcrypt encoding result for user123
using the following syntax:

String encoded=new BCryptPasswordEncoder().encode("user123");
String adminPassword=new BCryptPasswordEncoder().encode("admin123");

insert into user(id, username, password, enabled) values(2, 'user', '$2a$10$fV.0oNiKTmVja3.VoyLrg.XpHZJUzEwfjlQruq//rouovq7MJ3Dbe', true); 
