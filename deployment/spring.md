---
layout: default
---

## Déploiement du *back-end* Spring

La partie qui gère toutes les données de l'application et qui permet de
réaliser les actions nécessaire est un back-end écrit en Java avec le
framework Spring.

Pour pouvoir déployer ce composant, il vous faudra :

* Java 8 (avec JDK)
* Gradle (version 4.8 ou supérieur)

### Compilation

Dans un premier temps, si ce n'est pas déjà fait, il faut clôner le dépôt
Git :

~~~
$ git clone https://github.com/SamuelBzn/Monomalpoly
~~~

Il faut ensuite compiler l'application et produire un fichier JAR, pour
cela, il faut se rendre dans le dossier du dépôt avec la commande `cd`
et lancer la commande :

~~~
$ gradle bootJar -p api
~~~

### Déploiement

Il faut ensuite copier le fichier JAR sur le serveur où déployer
l'application et créer un fichier de service pour gérer le cycle de vie
de l'application. Pour cela, sous un système possédant Systemd, il suffit
de créer un fichier placé dans le dossier `/etc/systemd/system/` sous le
nom `spring.service` par exemple et d'y placer le contenu suivant :

~~~ini
[Unit]
Description=Spring HTTP Server
After=network.target

[Service]
Type=simple

# Si jamais vous souhaitez lancer le processus en tant qu'un
# utilisateur en particulier
# User=mouloud

WorkingDirectory=/chemin/vers/dossier
ExecStart=/usr/bin/java -jar /chemin/vers/dossier/gs-spring-boot-0.1.0.jar

Restart=always

[Install]
WantedBy=multi-user.target
~~~

Il suffit ensuite, en tant qu'utilisateur privilégié, de lancer la commande :

~~~
# systemctl start spring
~~~

L'application est ensuite disponible via le port 8080 de votre serveur
(et est exposée au monde extérieur).
