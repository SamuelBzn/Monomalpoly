---
layout: default
---

## Acheter - `POST /buy/<type>`

### Requête

Dans le cas d'une maison :

~~~
$ curl -X POST <url>/buy/house
~~~

Dans le cas d'un hôtel :

~~~
$ curl -X POST <url>/buy/hotel
~~~

Dans le cas d'une propriété

~~~
$ curl -X POST <url>/buy/property
~~~

### Réponse

#### Pour les maisons et hôtels

Dans le cas d'une maison pouvant être achetée (nombre maximal non dépassé) :

~~~json
{
  "message": "Vous venez d'acheter une maison ; cette propriété en possède désormais 3."
}
~~~

Dans le cas d'une maison ne pouvant pas être achetée :

~~~json
{
  "message": "Vous ne pouvez plus acheter de maison pour cette propriété"
}
~~~

Dans le cas d'un hôtel pouvant être acheté :

~~~json
{
  "messsage": "Vous venez d'acheter un hôtel ; cette propriété en possède désormais 2"
}
~~~

Dans le cas d'un hôtel ne pouvant pas être acheté :

~~~json
{
  "message": "Vous ne pouvez plus acheter d'hôtels pour cette propriété"
}
~~~

#### Pour les propriétés

Dans le cas d'une propriété qui n'est possédée par personne :

~~~json
{
  "message": "Vous venez d'acheter cette propriété. Il vous reste 500 euros."
}
~~~

Dans le cas d'une propriété déjà possédée :

~~~json
{
  "message": "Vous ne pouvez pas acheter cette propriété."
}
~~~
