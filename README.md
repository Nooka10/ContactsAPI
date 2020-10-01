# **Projet Contacts API**

Le projet peut être démarré dans un container docker à l'aide des commandes suivantes:

```
mvn clean install
docker network create mysql_network
docker-compose up --build
```

L'API est utilisable via Swagger-ui atteignable à l'adresse http://localhost:8080/.

La base de données est automatiquement populée au démarrage.
Si vous souhaitez la repopuler manuellement, un script SQL est disponible dans le répertoire: src/main/resources/data.sql


Il y a 3 utilisateurs dans la base de données (un admin et 2 simples utilisateurs):

```
login:     admin@owt.ch
password:  admin

login:     user@owt.ch
password:  user

login:     user1@owt.ch
password:  user
```

Afin de vous identifier dans Swagger-ui, cliquez sur le bouton Authorize et indiquez soit un Bearer token obtenu à l'aide du endpoint /login, soit un username(email) et password d'un compte existant.

ATTENTION, si vous indiquez un Bearer token, il faut ajouter 'Bearer ' devant le token reçu du endpoint login!

Pour simplifier la création d'utilisateurs avec les droits d'admin, vous pouvez indiquer les droits de l'utilisateur que vous créez dans le endpoint /register.
Les 2 rôles existants sont:
ROLE_ADMIN pour les administrateurs.
ROLE_USER pour les utilisateurs.
