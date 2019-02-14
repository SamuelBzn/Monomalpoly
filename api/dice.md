---
layout: default
---

## Lancer de dé - `GET /dice`

### Requête :

~~~
$ curl <url>/dice
~~~

### Réponse :

Par exemple :

~~~json
{
  "message": "Vous avez fait 7. Vous tombez sur une carte chance, ...",
  "isDouble": false
}
~~~

Le champ `message` contient toutes les informations dont l'utilisateur
a besoin pour son tour et la propriété `isDouble` permet de savoir si
l'utilisateur a fait un double ou non en jetant les dés.

### Utterances

<ul class="utterances">
  <li>lancer les dés</li>
  <li>jeter les dés</li>
</ul>
