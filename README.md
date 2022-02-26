# Poc Spring Batch
### Spring Batch ?
Spring Batch est un framework open source pour le traitement par lots. Il s'agit d'une solution légère et complète conçue pour permettre le développement d'applications 
par lots robustes, que l'on trouve souvent dans les systèmes d'entreprise modernes. Son développement est issu d'une collaboration entre SpringSource et Accenture.

Il permet de pallier à des problème récurrents lors de développement de batchs:
  - productivité
  - gestion de gros volumes de données
  - fiabilité
  - réinvention de la roue.

### Architecture de base de Spring Batch
![AjTWFQJ](https://user-images.githubusercontent.com/56096031/155855089-875a7c61-85c3-4344-b688-c5c8acb4f192.png)

- Le JobLauncher : il s'agit du composant chargé de lancer/démarrer le programme de traitement par lot (batch). Il peut être configuré pour s'auto déclencher ou pour être déclenché par un évènement extérieur (lancement manuel). Dans le workflow Spring Batch, le JobLauncher est chargé d'exécuter un Job (tâche).
- Le Job : il s'agit du composant qui représente la tâche à qui on délègue la responsabilité du besoin métier traité dans le programme. Il est chargé de lancer de façon séquentielle une ou plusieurs Step.
- La Step : c'est le composant qui enveloppe le cœur même du besoin métier à traiter. Il est chargé de définir trois sous-composants structurés comme suit :
    1. ItemReader : c'est le composant chargé de lire les données d'entrées à traiter. Elles peuvent provenir de diverses sources (bases de données, fichiers plats (csv, xml, xls, etc.), queue).
    2. ItemProcessor : c'est le composant responsable de la transformation des données lues. C'est en son sein que toutes les règles de gestion sont implémentées .
    3. ItemWriter : ce composant sauvegarde les données transformées par le processor dans un ou plusieurs conteneurs désirés (bases de données, fichiers plats (csv, xml, xls, etc.), cloud).
 - JobRepository : c'est le composant chargé d'enregistrer les statistiques issues du monitoring sur le JobLauncher, le Job et la (ou les) Step à chaque exécution. Il offre deux techniques possibles pour stocker ces statistiques : le passage par une base de données ou le passage par une Map. Lorsque le stockage des statistiques est fait dans une base de données, et donc persisté de façon durable, cela permet le suivi continuel du Batch dans le temps à l'effet d'analyser les éventuels problèmes en cas d'échec. A contrario lorsque c'est dans une Map, les statistiques persistées seront perdues à la terminaison de chaque instance d'exécution du Batch. Dans tous les cas, il faut configurer l'un ou l'autre obligatoirement.
