![Lines of code](https://img.shields.io/tokei/lines/github/Mi-Rage/BulletinBoard) ![GitHub language count](https://img.shields.io/github/languages/count/Mi-Rage/BulletinBoard) ![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/Mi-Rage/BulletinBoard)
# BulletinBoard
 Веб приложение "Доска объявлений". 
 
 В проекте используется БД Posrgres. Для начала работы необходимо создать базу MyDatabase с пользователем, согласно данным в application.properties и вручную прописать в ней пользователя с правами администратора: 
 ```
 insert into users (username, password, enabled) values ('admin', '{noop}adminpass', true);
 ```
 ```
 insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
 ```
 На текущий момент приложение позволяет:
 * Просматривать список последних объявлений и переходить в них.
 * Осуществлять поиск по ключевым словам в заголовках или тексте объявлений.
 * Зарегистрированным пользователям добавлять объявления в базу.
 * Просматривать информацию о создателе объявления (все объявления этого пользователя и дату его регистрации).
 * Просматривать все объявления из выбранной категории.
 * Получить доступ к админке пользователю с правами администратора.
 * Просмотреть в админке список всех пользоватлей и их ролей.
 
