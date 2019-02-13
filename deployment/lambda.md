---
layout: default
---

## Déploiement de la fonction Lambda

Pour faire le lien entre la Skill Alexa et le [back-end Spring](spring.html),
une fonction Lambda est invoquée par la Skill dès que l'utilisateur tente
de réaliser une action.

Pour déployer la fonction Lambda, il vou faudra :

* Java 8 (avec JDK)
* Gradle (version 4.8 ou supérieur)

### Compilation

Dans un premier temps, si ce n'est pas déjà fait, il faut clôner le dépôt
Git :

~~~
$ git clone https://github.com/SamuelBzn/Monomalpoly
~~~

Il faut ensuite éditer le fichier `MonomalpolyRequestStreamHandler.java` dans
le dossier `Lambda/src` et y inclure l'identifiant de votre Skill. Pour le
trouver, il vous faut :

* Aller sur https://developer.amazon.com/edw/home.html#/
* Cliquer sur "Edit" sur le projet concerné
* Cliquer sur "Endpoints" (dans la barre gauche ou en bas à droite)
* Copier l'identifiant à côté de "Your Skill ID"

Il faut ensuite coller cet identifiant dans le fichier mentionné ci-dessus
et la placer comme suit :

~~~java
static {
    supportedApplicationIds.add("amzn1.ask.skill.ada94....");
}
~~~

Il faut ensuite changer l'URL qui pointe vers le backend dans le fichier
`MonopolySpeechlet.java` :

~~~java
public static final String API_URL = "http://votre.site.com:8080";
~~~

Ensuite, en allant dans le dossier du dépôt Git avec `cd`, il faut compiler
la fonction Lamdba :

~~~
$ gradle fatJar -p Lambda
~~~

### Déploiement

Il faut tout d'abord créer une fonction Lambda dans la console développeur
AWS.

Un fichier JAR contenant la fonction Lambda va être produit en lançant
la commande mentionnée au dessus. Ce fichier est placé dans le dossier
`build/libs/` et se nomme `Lambda-all.jar`.

Vous pouvez soit uploader ce fichier sur AWS Lambda par le biais de la
console développeur ou utiliser l'outil `aws` en ligne de commande :

~~~
$ aws lambda update-function-code --function-name monomalpoly --zip-file fileb://./Lambda/build/libs/Lambda-all.jar
~~~

<div class="alert info">
  <p>Pour pouvoir utiliser l'outil <code>aws</code> en ligne de commande,
    il vous faudra tout d'abord le configurer en tapant <code>aws configure</code>
    dans votre terminal.
  </p>
</div>

### Test

Pour s'assurer que la fonction Lambda fonctionnera correctement en
production, vous pouvez essayer de lancer une série de tests en local avec
la commande :

~~~
$ gradle test -p Lambda
~~~

Si les tests ne passent pas en local, il y a peu de chances que les choses
se passent bien en production.
