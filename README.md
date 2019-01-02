# Politic App
## D'une Local Database à FireBase

Afin de passer notre application sur FireBase nous avons tout d'abord tenté de modifier toutes nos entity, malheureusement toute notre application fût criblée d'erreurs. Nous avons donc choisi de créer une nouvelle classe PartyFB afin d'effectuer des test avec FireBase. Nous arrivons à insérer des données, updater ces dernières, ainsi que les supprimer. 

### Notre problématique
Nous sommes capable de lire les données sur FireBase mais à cause d'un problème de synchronisation, les données ne s'affiche pas directement dans notre liste. Nous sommes obligé de passer par un bouton showData. Avec cette manière nous pouvons afficher les données qui sont récupérées grâce au thread précédement lancé.

### Tester le module party
Afin de tester le module party il est recommandé de procéder comme suit:
1. Lancer l'application
2. Cliquer sur "Party List"
3. Cliquer sur "ShowData"

	3.1 les données affichées correspondent à des party présents dans la base de donnée firebase mais ne s'affichent pas correctement.

A partir de là 2 options s'offre à l'utilisateur :

#### Ajouter un parti

4. cliquer sur "Add a party"
5. Saisir les données 

#### Editer / Supprimer un parti

6. cliquer sur un parti
7. cliquer sur supprimer parti

### Tester le module Politician

Il est possible de créer un politicien sur le sign-up de la page de login. De plus pour afficher les politiciens il faut aller sur "Liste politiciens" et cliquer sur show data en haut à droite. Il est possible de le supprimer et de l'éditer comme pour les parties.

