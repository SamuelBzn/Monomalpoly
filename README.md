# Monomalpoly

## Déploiement de la fonction Lambda

Pour déployer la fonction Lambda, il faut d'abord inclure l'ID du Skill
Lambda dans le fichier `MonomalpolyRequestStreamHandler.java` en suivant
les instructions contenus dedans.

Ensuite lancer :

~~~
$ gradle fatJar
~~~

(Ou utiliser 'Clipse pour le packager).

Finalement, l'uploader sur AWS Lambda.
