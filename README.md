# zadaniePGS
wypożyczalnia sprzętu

Po uruchomieniu dostępne są opcje logowania i rejestracji:
	logowanie : http://localhost:8080/ lub http://localhost:8080/login
	rejestracja : http://localhost:8080/signup
Powyższe opcje dostępne są dla każdego. 
Próbując dostać sie do innych opcji bez logowania zostaniemy przekierowani na stronę logowania.
Opcje dostępne po zalogowaniu:
	wyświetlenie wszystkich użytkowników: http://localhost:8080/users dostępne tylko z konta administratora.
	wyświetlenie szczegółów pojedyńczego użytkownika: http://localhost:8080/users/ID Każdy User może zobaczyć tylko szczegóły dla siebie.
	wyświetlanie wypożyczonych przedmiotów /users/id/items 
	wyszukiwanie po emailu http://localhost:8080/users/search/byemail/email Dostępne tylko dla administratora.
	wyszukiwanie po nazwisku http://localhost:8080/users/search/byemail/email Dostępne tylko dla administratora.
	wyświetlanie wszystkich przedmiotów : http://localhost:8080/items 
	wyświetlanie szczegółów przedmiotu : http://localhost:8080/items/id
	wypożyczanie przedmiotu http://localhost:8080/rent/idPrzedmiotu Wypożycza aktualnie zalogowanemu użytkownikowi przedmiot;
	
	zaimplementowana możliwość dodawania użytkownika poza rejestracją POST http://localhost:8080/users z odpowiednim body. (Tylko w celach ćwiczenia można potem usunąć)
	Update zmiana danych PUT http://localhost:8080/users/ID z odpowiednim body.
	Usuwanie użytkownika DELETE http://localhost:8080/users/ID.
	
Dostępne konto administratora:
Login: admin@wp.pl
Password: qweqwe123
Konto urzytkownika
Login:
Tkow@interia.pl
Moca@interia.pl
Password: qweqwe123

TODO:
Dopasować uprawnienia dla usera i admina. Na chwilę obecną tylko kilka funkcji jest oddzielone.
Naprawić możliwe bugi.
Dodać czytelny interfejs do przeglądania API.
Ulepszyć walidatory.
?


	
	