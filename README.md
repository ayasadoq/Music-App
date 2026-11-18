# Music-App

Application web de gestion d'une école de musique développée avec Spring Boot, Thymeleaf, Spring Security et H2 Database.

## Description

Cette application permet de gérer :

- les cours de musique,
- les instruments,
- les professeurs,
- les statistiques de l'école,
- l'authentification et les rôles utilisateurs.

Elle contient à la fois une interface web (Thymeleaf) et des endpoints REST pour exploiter les données.

## Stack technique

- Java 17
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- H2 Database
- Maven

## Prérequis

- Java 17 ou plus
- Maven (ou utiliser le wrapper fourni : `mvnw`)

## Démarrage rapide

1. Ouvrir un terminal dans le dossier du projet.
2. Lancer l'application :

```bash
./mvnw spring-boot:run
```

Sur Windows PowerShell :

```powershell
./mvnw.cmd spring-boot:run
```

3. Accéder à l'application dans le navigateur :

```text
http://localhost:8080
```

## Authentification

Les comptes prédéfinis sont :

| Utilisateur | Mot de passe | Rôle |
| --- | --- | --- |
| admin | admin123 | ADMIN |
| aya | aya123 | ADMIN |
| visiteur | visiteur123 | VISITEUR |
| anwar | anwar123 | VISITEUR |

La page de connexion est accessible ici :

```text
http://localhost:8080/login
```

## Console H2

La base de données H2 est activée pour le développement :

```text
http://localhost:8080/h2-console
```

Paramètres JDBC :

- JDBC URL : `jdbc:h2:mem:ecolemusique`
- Username : `sa`
- Password : vide

## Points d'entrée principaux

### Interface web

- `/dashboard` : tableau de bord principal
- `/login` : page de connexion

### API REST

- `GET /api/cours`
- `GET /api/cours/{id}`
- `POST /api/cours`
- `PUT /api/cours/{id}`
- `DELETE /api/cours/{id}`

Les autres ressources de l'application (instruments, professeurs, statistiques) suivent le même modèle d'API REST.

## Structure du projet

```text
src/
├── main/
│   ├── java/com/ecolemusique/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── enumeration/
│   │   ├── repository/
│   │   ├── service/
│   │   └── MusicApplication.java
│   └── resources/
│       └── application.properties
└── test/java/com/ecolemusique/
```

## Commandes utiles

### Compiler le projet

```bash
./mvnw clean compile
```

### Exécuter les tests

```bash
./mvnw test
```

### Générer le package

```bash
./mvnw clean package
```

## Notes

- L'application est configurée en mode développement avec une base H2 en mémoire.
- Les données sont recréées au redémarrage selon la configuration JPA.
- La sécurité applique des restrictions selon les rôles et les méthodes HTTP.

## Licence

Ce projet est fourni à des fins de démonstration et de développement local.
