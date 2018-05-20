# web-scraping

Aplikacja została wystawiona jako API Rest. 
Metoda przekazywania parametrów to POST.
Użyty został SpringBoot, który posiada wbudowany własny serwer aplikacyjny.

Aby uruchomić z InteliJ aplikację wystarczy :
- w WebScrapingApplication uruchomić metodę main. 
- lub przechodząc do folderu target java -jar web-scraping-0.0.1-SNAPSHOT.jar


Dostępne resource:

http://localhost:8080/xml
Wymagane parametry:
url - adres np. https://www.ceneo.pl/Filmy_Blu-ray/Gatunek:Sensacyjne.htm 

http://localhost:8080/file
Wymagane parametry:
url - adres np. https://www.ceneo.pl/Filmy_Blu-ray/Gatunek:Sensacyjne.htm 
responseType - np. XML

