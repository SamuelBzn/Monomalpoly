---
layout: default
---

## Informations sur un joueur - `GET /player`

Requête :

~~~
$ curl <url>/player --data 'player=Mouloud'
~~~

Réponse :

~~~json
{
  "balance": 5000,
  "properties": 5,
  "houses": 3,
  "hotels": 1
}
~~~
