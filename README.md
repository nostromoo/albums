Albums - Romain PEDEPOY
=========================


Choix de l'architecture et des composants
------------

J'ai décidé d'utiliser les Android Architecture Component et de mettre en place les bonnes pratiques que Google conseille : une architecture sous forme de layer ( activity -> viewmodel -> repository -> WS et BDD)
L'avantage de cette architecture est que chaque layer ne connait que le layer en contact avec lui et pas les autres, il y a une séparation entre l'UI, la logique métier et la couche data (repository).
Cela respecte le principe de la "clean architecture".
J'utilise donc un pattern MVVM avec DataBinding, LiveData et Room pour la base de données, Retrofit pour les appels réseaux.
J'ai essayé d'inclure tous les aspects d'une app telles qu'elles sont conçues aujourd'hui, j'ai donc fait de l'injection de dépendance ainsi que quelques tests unitaires.


Gestion de la persistence
------------

J'utilise Room pour la base de données car il fait partie intégrante des AAC et est poussé par Google.
Pour les webservice j'ai choisi Retrofit et les coroutines.
Quand l'utilisateur démarre l'app on affiche dans un premier temps les données de la BDD s'il y en a, puis on fait l'appel réseau qui va mettre à jour la BDD en cas de succès.
Le LiveData se charge de déclencher la mise à jour de l'UI.
J'utilise également Paging qui permet avec Room de ne requêter qu'une partie de la base de donnée et de paginer le contenu automatiquement.

DI
------------

Les différents composents (VM, repository, retrofit, DAO) sont injectés avec Dagger(ce qui est recommandé par Google).
C'est quelque chose que je n'utilise pas dans mon poste actuel, j'ai du apprendre à l'utiliser pour ce test. Il y certainement moyen de faire quelque chose de mieux.
J'ai créé un AppComponent qui fournit toutes les dépendances dont l'application a besoin, il est créé dans la classe AlbumApplication.

Tests
------------

J'ai mis en place quelques tests unitaires sur le DAO et la partie API. Mais je ne suis pas aller très loin car c'est un domaine que je ne maitrise pas bien non plus, et il m'aurait fallu plus de temps pour les approndir.

Biblio
------------

Je me suis aidé pour ce projet des ressources de Google (notamment la sample app SunFlower) :

https://developer.android.com/jetpack/docs/guide
https://developer.android.com/topic/libraries/architecture

ainsi que de ce projet :

https://proandroiddev.com/android-architecture-starring-kotlin-coroutines-jetpack-mvvm-room-paging-retrofit-and-dagger-7749b2bae5f7