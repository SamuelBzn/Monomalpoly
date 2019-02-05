---
layout: default
---

## Lancer de dé - `GET /dice`

### Requête :

~~~
$ curl <url>/dice
~~~

### Réponse :

Dans le cas d'une propriété libre :

~~~json
{
  "isDouble": false,
  "value": 7,
  "message": "Vous avez fait 7. Voulez-vous acheter cette case ?",
  "actions": ["Acheter cette propriété"]
}
~~~

Dans le cas d'une propriété possédée :

~~~json
{
  "isDouble": true,
  "value": 8,
  "message": "Vous avez fait 8 avec un double.",
  "actions": [
    "Acheter une maison",
    "Acheter un hôtel",
    "Revendre"
  ]
}
~~~

Dans le cas d'une case adverse avec assez d'argent :

~~~json
{
  "isDouble": false,
  "value": 10,
  "message": "Vous avez fait 10. Vous verser 450 euros à Mouloud.",
  "actions": []
}
~~~

Dans le cas d'une case adverse avec bankrupt :

~~~json
{
  "isDouble": false,
  "value": 4,
  "message": "Vous avez fait 4 mais vous n'avez plus assez d'argent pour payer votre dette. Souhaitez-vous que j'énumère les actions ?",
  "actions": [
    "Revendre une propriété"
  ]
}
~~~
