

# Avant :
- Un IDE : IntelliJ 
- Le JDK Java 17
- MySQL
- Docker
- Postman
- Maven

## Création d’un conteneur “mysql-container”

Sur Docker Hub, nous trouvons l’image officielle MySQL :
![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/bf734e2c-2934-4959-a5b7-39b105ac421b)

Afin de créer et exécuter un conteneur Docker basé sur cette image, nous lançons :
>> docker run -d -p 3306:3306 --name mysql-container -e 
MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=testdb -e 
MYSQL_USER=test-user -e MYSQL_PASSWORD=pass mysql



Voici une explication détaillée de la commande :
o docker run: C'est la commande de base pour exécuter un conteneur Docker.
o -d: Cela signifie "détaché" ou "en arrière-plan". L'option -d permet au conteneur de 
s'exécuter en arrière-plan, de sorte que nous puissions continuer à utiliser notre 
terminal sans bloquer l'interface.
o -p 3306:3306: Cette option relie le port 3306 de l'hôte (notre machine locale) au port 
3306 du conteneur. Le port 3306 est le port par défaut de MySQL. Cela signifie que 
nous pourrions accéder au serveur MySQL à partir de notre machine locale via le port 
3306.
o --name mysql-container: Cela attribue un nom au conteneur, en l'appelant "mysqlcontainer". 
o -e MYSQL_ROOT_PASSWORD=pass: Cette option définit une variable 
d'environnement pour le conteneur. Dans ce cas, elle définit le mot de passe du compte 
root de MySQL à "pass". 
o -e MYSQL_DATABASE=testdb: Cette option définit le nom de la base de données 
par défaut à "testdb".
o -e MYSQL_USER=test-user: Cette option définit le nom d'utilisateur de la base de 
données à "test-user".
o -e MYSQL_PASSWORD=pass: Cette option définit le mot de passe de l'utilisateur de 
la base de données à "pass".
o mysql: C'est le nom de l'image que vous souhaitez utiliser pour créer le conteneur.

Le résultat attendu est le suivant :
![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/7614bedd-868e-4de7-bda3-d4eac0bd5696)

Nous le consultons :
![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/b19d1bb8-16e9-4a6b-88c1-998b99dec005)

Maintenant, nous modifions « application.properties » avec les informations de cette 
image pour que notre application puisse se connecter à la base de données MySQL s'exécutant 
dans le conteneur Docker.

![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/35c9dc0b-6a94-41f3-bb32-3d600da536fc)


##  Génération des fichiers jar

Afin de construire l’image de l’application, il faut l’archiver. Nous lançons :

![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/f76ffafb-13a8-46d2-bf0a-dc11d14e157c)
![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/e5a276e1-3f6d-46c4-bf25-9e3737bd4831)

Ainsi, le répertoire "target" est généré ; ce répertoire est utilisé pour stocker les fichiers 
produits lors du processus de construction, y compris les fichiers compilés, les fichiers JAR, 
les rapports de tests, les fichiers de documentation, etc.

![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/881c04b5-d917-42df-9c95-ffa32128ca0f)



## Création de l’image d’application

Cette image va être utilisée pour exécuter des conteneurs Docker de notre application 
monolithique. Nous lançons :

>> docker build -t monolithic-application:1.0 .
Cela signifie :
o docker build : Lance la construction d'une nouvelle image Docker.
o -t monolithic-application:1.0 : Utilise l'option -t pour donner un nom et une étiquette 
(tag) à l'image. Dans ce cas, l'image sera nommée "monolithic-application" et aura 
l'étiquette (tag) "1.0".
o . : Spécifie le chemin vers le contexte de construction. Dans cet exemple, le point (.) 
signifie que le Dockerfile se trouve dans le répertoire actuel, et c'est à partir de ce 
répertoire que Docker va construire l'image.


![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/0d08429f-88be-44ec-a139-0a153b8dfe46)
![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/7909274c-e189-42d4-9f5b-94f895953913)

## Exécution de l’application

Nous lançons un conteneur Docker à partir de l'image d’application. Ce conteneur est nommé 
"monolithic-application", il lie le port 8082 du conteneur au port 8082 de la machine hôte, et 
il établit un lien direct au conteneur « mysql-container » pour la communication entre eux :

>> docker run -p 8082:8082 --link mysql-container --name monolithic-application 
fc7a4893b1b1

![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/384863be-3aad-4e55-84cd-45d5d84e3f64)

Nous pouvons la tester :

![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/4050c24b-6ef7-431f-ba35-26684fc7fe53)

![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/91e9b2b1-bb55-4d3c-94a7-0d22b9337068)

Nous tapons « ipconfig », pour avoir l’adresse ip, et ainsi nous pouvons accéder aux données 
JSON via : http://@ip:8082/student/findAll  , à partir du tél par exemple :

![image](https://github.com/HoussamEDGE/Dockerize-springboot-mysql-app/assets/99811097/83b87b1d-34a3-4cc5-83d5-896c7608f3d6)






