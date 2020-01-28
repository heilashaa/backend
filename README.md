# Задание курсовой работы
## Author: [Alexandr Heilash](https://www.linkedin.com/in/alexandr-heilash-6b0055173/)  
### Java: Java, Spring, MySQL, опционально Angular или React


1. Требуется разработать сайт для проведения краудфандинговых кампаний.
2. Неаутентифицированным пользователи доступен только режим read-only (доступен поиск, недоступно создание кампаний, недоступны комментарии, лайки и рейтинги).
3. Аутентифицированные пользователи имеют доступ ко всему, кроме админки.
4. Админка позволяет управлять пользователями (просматривать, блокировать, удалять, назначать других админами). Администратор видит каждую страницу пользователя и каждую кампанию как ее создатель (например, может отредактировать или создать от имени пользователя с его страницы новую кампанию).
5. Требуется поддерживать вход через социальные сети (не менее двух).
6. На каждой странице доступен полнотекстовый поиск по сайту (результаты поиска - всегда кампании, например, если текст найден в новости или комментарии, что должно быть возможно, то выводится ссылка на кампанию).
7. У каждого пользователя есть его личная страница, на которой он видит список оплаченных "бонусов", управляет списком своих кампаний (таблица с фильтраций и сортировками, возможность создать/удалить/редактировать кампанию/открыть в режиме чтения), поля с информаций о пользователе (in-place editing) и (опционально) "медальки".
8. Каждая кампания состоит из: название, список "бонусов", краткое описание с поддержкой форматирования markdown, "тематика" (из фиксированного набора, например, "Electronics", "Education" и проч.), тэги (вводится несколько тэгов, необходимо автодополнение - когда пользователь начинает вводить тэг, выпадает список с вариантами слов, которые уже вводились ранее на сайте), видео (с Youtube), галерия изображений (хранение в облаке), целевая сумма денег (все суммы в "у.е.") и дата окончанию. Помимо этого, у кампании есть "новости" - лента с заголовком и блоком текста с поддержкой форматирования markdown и одной опциональной картинкой (хранение в облаке). По новостям автомагически формируется навигация в виде календаря рядом с описанием. На странице кампании можно добавлять/удалять/открывать на редактирование новости и "бонусы" ("переведешь 5 у.е. получишь фуболку с логотипом", "передевешь 100 у.е. и получишь ..."). Каждый бонус имеет название, сумму и описание.
9. Все картинки сохраняются в облаке, все картинки загружаются драг-н-дропом.
10. На главной странице отображаются: последние обновленные кампании, кампании с самыми большими рейтингами, облако тэгов.
11. При открытие кампании в режиме чтения (или другими пользователями) в конце отображаются комментарии. Комментарии линейные, нельзя комментировать комментариий, новый добавляется только "в хвост". Необходимо реализовать автоматическую подгрузку комментариев - если у меня открыта страница с комментариями и кто-то другой добавляет новый, он у меня автомагически появляется (возможна задержка в 2-5 секунд).
12. Напротив бонуса должна быть функция "Поддержать", которая переводит соотв. сумму на кампанию.
13. Каждый пользовать может проставить "рейтинг" (от 1 до 5 звездочек) кампании (не более одного от одного пользователя на кампанию) - средний рейтинг отображется у кампании рядом с названием. Под любым из комментариев пользователь может поставить лайк или дизлайк (не более одного на комментарий от одного пользователя).
14. Сайт позволяет пользователям перевести деньги (интеграция с пейментом не обязательна) на кампанию. Общая сумма отображается и отслеживается прогресс/прлцент заверешения.
15. На личной странице пользователь видит, какие ему бонусы положены.
16. Сайт должен поддерживать два языка: русский и английский (пользователь выбирает и выбор сохраняется). Сайт должен поддерживать два оформления (темы): светлое и темное (пользователь выбирает и выбор сохраняется).
17. Обязательно: Bootstrap (или любой другой CSS-фреймворк), поддержка разных разрешений (в том числе телефон), ORM для доступа к данным (Hibernate, ActiveRecord, другое), движок для полнотекстового поиск (или средствами базы, или отдельный движок — НЕ ПОЛНОЕ СКАНИРОВАНИЕ селектами).
18 Требования со звездочкой (лишь опционально, на 10/10, после реализации остальных требований):
* Рабочая интеграция с пейментом.
* Приложение должно поддерживать "медальки" - по достижению какого-то результата на странице пользователя отображается "медалька" (маленькие версии отображаются везде на сайте после имени пользователя, а само имя всегда является ссылкой на страницу пользователя). Например: "успешно провел 3 кампании", "собрал 10 лайков", "получил за кампанию рейтинг 4+ с количеством отзывов 10+" и проч. (не менее разных 4 медалей).
19. ВАЖНО: не копируйте из кодо-помоек всякую фигню. Лучше сделайте меньше, но сами разбиритесь, чтобы уметь ответить как на лету что-то поменять/добавить. Кураторы принимают огромное количество однотипных проектов и видели, вероятно, большинство выложенной фигни на тему.
