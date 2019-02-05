---
layout: default
---

## Ajouter un joueur - `POST /add_player`

Requête :

~~~
$ curl -X POST <url>/add_player --data 'name="Mouloud"'
~~~

Réponse :

* Succès : `status 200`
* Erreur :

  ~~~json
  {
    "erreur": "Impossible de créer le joueur"
  }
  ~~~

### Utterances

<ul class="utterances">
  <li>rajouter un joueur</li>
  <li>ajouter un joueur</li>
</ul>
