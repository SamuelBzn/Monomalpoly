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

### Utterances

<ul class="utterances">
  <li>donner mes informations</li>
  <li>donner les informations de <span class="slot username">Mouloud</span></li>
  <li>quelles sont les informations de <span class="slot username">Jean-Michel</span> ?</li>
  <li>où en-est <span class="slot username">Jean-Mohktar</span> ?</li>
</ul>
