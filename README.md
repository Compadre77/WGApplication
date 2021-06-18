## About WG Application

Um die Applikation richtig starten zu können werden beim ersten mal ausführen Benutzer erstellt, und Sample daten gespeichert. 
Das Die ID's der Benutzer jeweils generiert werden stimmen sie nicht mit den ID's überein, die von den anderen Entitäten verendet werden.
Falls Sie also beim starten der Applikation fehler bekommen ist es notwendig die ID's bei Folgenden Tables anzupassen auf die generierte User'IDs:

* Table:TO_DO_USERIDS - Die UserIDs müssen angepasst werden
* Table:TODO - Die CurrentAssignees müssen angepasst werden
* Table:BILL_USERIDS - Die UserIDs müssen angepasst werden
* Table:BILL - Die Creators müssen angepasst werden