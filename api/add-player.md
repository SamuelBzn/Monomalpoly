---
layout: default
---

## Ajouter un joueur - `POST /player/add/<name>`

### Requête :

~~~
$ curl -X POST <url>/player/add/<name>
~~~

* La paramètre `name` permet de spécifier la valeur du nom de l'utilisateur
  à ajouter. Ce paramètre doit être URL-encodé ; cette action est à la charge
  du développeur réalisant la requête.

### Réponse :

Par exemple, avec la requête :

~~~
$ curl -X POST <url>/player/add/Mouloud
~~~

~~~json
{
  "id": 43,
  "name": "Mouloud",
  "balance": 1000,
  "position": 0,
  "inJail": false,
  "nbToursToGo": 0,
  "properties": null,
  "capital": 1000
}
~~~

### Utterances

<ul class="utterances">
  <li>Mon pseudo est <span class="slot username">Username</span></li>
  <li>Mon nom est <span class="slot username">Username</span></li>
</ul>
