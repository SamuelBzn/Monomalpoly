---
layout: default
permalink: /api/
---

Cette page donne accés aux *endpoints* disponibles pour les actions
répertoriés ainsi que les utterances qui permettent de donner accés
à ces actions.

Slots disponibles pour les utterances :

<table>
  <thead>
    <tr>
      <th>Slot</th>
      <th>Description</th>
    </tr>
  </thead>

  <tbody>
    <tr>
      <td>
        <span class="slot buyable">Buyable</span>
      </td>
      <td>
        Slot représentant un élément pouvant être acheté.
      </td>
    </tr>
    <tr>
      <td><span class="slot username">Username</span></td>
      <td>
        Slot représentant le nom d'un joueur.
      </td>
    </tr>
  </tbody>
</table>

## Lancement de la partie

* [Démarrer une partie](start.html)
* [Ajouter un joueur](add-player.html)
* [Déterminer le joueur qui commence](who-start.html)

## Actions au cours de la partie

* [Lancer les dés](dice.html)
* [Achat](buy.html)

## Autres

* [Solde du joueur courant](consult.html)
* [Remettre à zéro](reset.html)
