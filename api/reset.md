---
layout: default
---

## Remise à zéro - `POST /reset`

Permet de remettre à zéro la base de données. Principalement utile pour
débugger le code.

### Requête :

~~~
$ curl <url>/reset
~~~

### Réponse :

~~~json
{
  "response": "base vidée sans erreur"
}
~~~

<div class="alert info">
  <p>
    L'effet de cette action est automatiquement invoqué lorsqu'une
    nouvelle partie est créée.
  </p>
</div>
