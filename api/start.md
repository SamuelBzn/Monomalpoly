---
layout: default
---

## Démarrer une partie - `POST /start`

### Requête :

~~~
$ curl -X POST <url>/game/new/<nb>/<state>
~~~

* Le paramètre `nb` permet de spécifier le nombre d'utilisateur qui vont
  jouer.
* Le paramètre `state` permet de définir l'état dans laquelle la partie
  doit se trouver au début.

### Réponse :

Par exemple, avec la requête :

~~~
$ curl -X POST <url>/game/new/4/choix_pseudo
~~~

~~~json
{
  "nbUsers": 4,
  "countNbUsers": 4,
  "state": "choix_pseudo",
  "currentPlayer": null,
  "board": [
    ...
  ]
}
~~~

### Utterances

<ul class="utterances">
  <li>lancer une partie</li>
  <li>lancer une nouvelle partie</li>
  <li>démarrer une partie</li>
  <li>démarrer une nouvelle partie</li>
</ul>
