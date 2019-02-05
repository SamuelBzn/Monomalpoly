---
layout: default
---

## Démarrer une partie - `POST /start`

~~~
$ curl -X POST <url>/start
~~~

* Succès : `status 200`
* Erreur :

  ~~~json
  {
    "error": "Erreur lors de la création de la partie"
  }
  ~~~
