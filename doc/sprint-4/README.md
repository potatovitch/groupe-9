# Sprint 4 - Organisation d'Équipe & Radiateur d'Information

## Objectif du Sprint

- L'objectif principal de ce sprint était d'améliorer le code 
- Le code du jeu de la roulette afin de permettre au joueur d'avoir la possibilité de choisir de 1 des 36 nombres de la roulette.
- Dans le jeu de la machine à sous il fallais : 
    - pouvoir modifié la mise de la roulette après chaque lancer
    - crée de vrai probabilité pour que certain symbole soit plus rare que d'autre 
- Jouer au mastermind avec une mise souhaitée multipliable a la fin en fontion du nombre de tentatives choisi.
- Permettre l'accès a l'argent gagner propre au joueur, le joueur pourra donc se balader a travers les différent jeu sans perdre les prix gagné précedemment
---

## Ce que nous avons fait

- Répartition des tâches pour permettre à chacun de commencer à coder.
- Début du développement de plusieurs mini-jeux : roulette, machine à sous, première version du mastermind.
- Mise en place du radiateur d'information pour suivre l'avancement du projet en physique et sur GIT.
- Mise au point réguliére sur la progression afin d'ajuster la planification si nécessaire.

---

## Radiateur d'Information en fin de sprint

- **Done**
    - Jouer a la roulette V2
    - Jouer a la machine a sous avec mise
    - Jouer a la slot machine avec de vrai probabilité
    - Jouer a différent jeux avec ma somme d'argent cumulé des différents jeux ou non
    - Jouer au master mind avec une mise libre

Au desous on peut également y retrouver une burn-up chart qui nous permet de conaitre notre vélocité et notre avancé par rapport au projet.
***Vélocité en fin de sprint :*** 11
***Points de sprint :*** 16


---

## Répartition des rôles (Sprint 4)

Scrum Master: Desprez Matheo

---

## Rétrospective


### Sur quoi avons nous butté ?

- le passage à une logique où l’argent circule entre tous les jeux a demandé beaucoup de coordination, et certains oublis de mise à jour ont entraîné des incohérences.
- la roulette, la machine à sous et le mastermind ont évolué en parallèle ce qui a permis de se répartir plus facilement
- Mise en place des vraies probabilités pour la machine à sous plus complexe que prévu (recherche réalisé)
- Difficultés avec la gestion des mises (validation des montants, erreurs si le joueur n’avait pas assez d’argent)
- encore quelques conflits liés aux merges, surtout quand plusieurs personnes modifiaient la logique de gestion de l’argent

### PDCA

**Quel sujet souhaitons nous améliorer ?**

La fiabilité des mises et des revenues dans les jeux.

**Comment améliorer**

- empêcher une mise supérieure au solde du joueur
- Mutualiser la gestion de l'argent dans une seule classe centrale (`Player`)

**Quelle action a mettre en place au prochain sprint**

- Organiser complétement la classe partagé afin d'éviter des incohérence de soldes et de simplifier les accés.


---

## Annexes

- Photo du radiateur d’information (voir dans le dossier doc/sprint-4/)

---
