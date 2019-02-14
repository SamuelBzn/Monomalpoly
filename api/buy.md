---
layout: default
---

## Acheter

### Requête


Dans le cas d'une propriété

~~~
$ curl -X POST <url>/buy/property
~~~

Dans le cas d'une maison :

~~~
$ curl -X POST <url>/buy/property/house
~~~

Pour faire avancer la partie mais spécifier le refus d'achat :

~~~
$ curl -X POST <url>/buy/refuse
~~~

### Réponse

#### Pour les maisons et hôtels

Dans le cas d'une propriété :

~~~json
{
  "message": "Vous avez acheté cette propriété. Au tour de Jean-Michel."
}
~~~

Dans le cas d'une maison :

~~~json
{
  "messsage": "Vous avez acheté une maison pour cette propriété. Au tour de Patrick."
}
~~~

Dans le cas d'un refus :

~~~json
{
  "message": "Vous n'avez rien acheté. Au tour de Mouloud."
}
~~~

### Utterances pour les propriétés

<ul class="utterances">
  <li>Oui je veux acheter le terrain</li>
  <li>Je veux acheter le terrain</li>
  <li>Je ne veux pas acheter le terrain</li>
  <li>Non je ne veux pas acheter le terrain</li>
</ul>

### Utterances pour les maisons

<ul class="utterances">
  <li>Oui je veux améliorer</li>
  <li>Non je ne veux pas améliorer</li>
</ul>
