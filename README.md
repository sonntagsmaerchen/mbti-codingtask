## MBTI Coding-Task

Bearbeitunszeit: ca. 5-6 Stunden

### Voraussetzungen zum Bauen:

JDK Java11+, Maven (mit Zugriff auf Repository welches alle Dependencies enthält zb. MavenCentral)

### Nutzung:

- Entweder mit Maven Bauen und Starten oder über mitgelieferte .bat File im */binary* Verzeichnis starten
- Standardport 8080, sollte frei sein oder in bat File/App Properties geändert werden
- Mit einem HttpClient (Curl, Postman, IDE-eigene Clients etc.) mit dem Service sprechen

Die Applikation bietet eine REST API mit einem Endpunkt */interval-merge* der den Input per POST entgegen nimmt.

Der JSON Body für den Input aus der Aufgabe liegt im Verzeichnis: *src/test/resources*
Einige invalide Inputs werden geprüft (wie zb. Intervalle mit Ende vor Start) und der Body der Antwort enthält dann den
entsprechenden Grund und die Felder.

### Verbesserungen / Weiterentwicklung:

- mehr/bessere Validierungen (zb. für invaliden Typen als Input intervalList: "abc")
- Nutzen von OpenAPI falls die API größer werden soll (contract first erleichtert Übersichtlichkeit)
- Nutzen des Spring Conversion Services oder anderen Mapping Frameworks für die Converter
- weitere Input Möglichkeiten zB. über Command Line Args oder Auslesen von Input txt Files

### Robustheit bei sehr großen Eingaben:

Mehr <u>Validierungen</u> des Inputs würden natürlich auch die Robustheit erhöhen, sodass fehlerhafte Eingaben
auf jeden Fall erkannt werden. Die Tests können um <u>parametrisierte Testfälle</u> erweitert werden um große
Datenmengen zu testen.

Wenn die Ergebnisse <u>persistiert werden sollen</u> kann dies in einem transaktionalen Kontext stattfinden, sodass
bei Fehlern ein entsprechender Rollback stattfinden kann. In diesem Fall kann auch das Fault API Objekt um eine Typ
Property erweitert werden, womit dem Client signalisiert wird ob es möglich ist die Anfrage noch einmal abzusenden.

Falls die Datenmengen zu groß werden um sie in <u>vernünftiger Zeit zu verarbeiten</u>, kann man die Datenmenge vor der
Verarbeitung
splitten und die Teilmengen nacheinander zusammenfügen, da die Reihenfolge für den Algorithmus keine Bedeutung hat.
In diesem Fall bietet sich die angebotene REST API auch nicht mehr an da der Client meist auf eine direkte Antwort
wartet, hier sollte zu asynchroner Kommunikation gewechselt werden (anderes REST Design, AMQP etc.).
