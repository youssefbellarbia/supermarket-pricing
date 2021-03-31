# Présentation de la conception
Commençons par une conception simple sans aucune logique de tarification complexe:
![domain]()

L'étape suivante consiste à concevoir une logique plus complexe qui nous permet de sélectionner un algorithme ou une stratégie pour calculer le prix total au moment de l'exécution: <a href='https://en.wikipedia.org/wiki/Strategy_pattern'>Strategy pattern</a>. Au lieu d'implémenter directement une seule stratégie, comme ce que nous avons fait dans l'approche précédente, le code reçoit des instructions d'exécution indiquant laquelle dans une famille de stratégies à utiliser.

Consdier une stratégie simple qui gère la <a href='https://en.wikipedia.org/wiki/Bulk_purchasing'>volume pricing</a>: une stratégie de tarification qui permet une remise pour les achats en gros. Par exemple, «la pomme coûte 50 cents, mais si un client veut trois pommes, le coût total est de 1,30 $».

Comme le comportement de la tarification varie en fonction de différentes stratégies, nous créons deux classes PricingStrategy pour calculer le prix total en fonction de deux stratégies: régulière et volume.

![strategy]()

Observez que les classes PricingStrategy implémentent l' interface IPricingStrategy avec une méthode GetTotal polymorphe. Dans cette méthode, nous transmettons l'objet OrderItem en tant que paramètre, afin que l'objet de stratégie puisse calculer le prix normal (pré-remise) et la remise sur volume en fonction du nombre d'articles.

Jetons un œil au diagramme de séquence:

![collaboration]()

Le diagramme de séquence UML montre l'interaction d'exécution: Le contexte est OrderItem qui délègue le calcul du prix à un objet de stratégie.

Jetez maintenant un œil à l'objet de contexte: OrderItem. Il ne met pas en œuvre la stratégie directement. Au lieu de cela, il fait référence à l' interface IPricingStrategy pour effectuer le calcul du prix total, ce qui rend OrderItem indépendant de la façon dont la stratégie est mise en œuvre.

![orderitem]()

Il existe différents algorithmes ou stratégies de tarification, et ils changent avec le temps. Qui devrait créer la stratégie? Une approche simple consiste à appliquer le <a href='https://en.wikipedia.org/wiki/Factory_method_pattern'>Factory pattern</a>: un PricingStrategyFactory peut être responsable de la création de toutes les stratégies nécessaires à l'application. PricingStrategyFactory est également responsable de la lecture des règles de tarification à partir du magasin de données, car il crée la stratégie de tarification.

Pour soulever encore une autre exigence intéressante et un problème de conception: comment gérer le cas de politiques de prix multiples et contradictoires? Existe-t-il un moyen de modifier la conception de sorte que l'objet OrderItem ne sache pas s'il traite une ou plusieurs stratégies de tarification, et propose également une conception pour la résolution de conflit? Oui, avec le <a href='https://en.wikipedia.org/wiki/Composite_pattern'>Composite pattern</a>.

Par exemple, une nouvelle classe appelée CompositeLowestPricingStrategy peut implémenter IPricingStrategy et contient elle-même d'autres objets PricingStrategy. Il s'agit d'une caractéristique de signature d'un objet composite: l'objet composite externe contient une liste d'objets internes, et les objets externe et interne implémentent la même interface. Autrement dit, la classe composite elle-même implémente l' interface IPricingStrategy .

![composite]()

# Refactoring

Un problème que je vois avec le passage de l'objet Contexte à la stratégie est que les classes Stratégie et Contexte peuvent être étroitement couplées et moins testables. Le contexte doit fournir les données pertinentes à la stratégie pour la mise en œuvre de l'algorithme et parfois, toutes les données transmises par le contexte peuvent ne pas être pertinentes pour toutes les stratégies concrètes.

Une approche serait de ne pas passer la référence (this) de la classe Context dans la classe de stratégie mais de ne transmettre que les données nécessaires:

```
Public GetTotal(int units, Price unitPrice);
```

Cependant, cela semble être une violation du principe ouvert-fermé, car nous devrons peut-être étendre cette méthode à l'avenir pour obtenir plus de données du contexte.

Une meilleure approche serait de rendre nos classes de stratégie dépendantes (ou d'utiliser) une interface que la classe de contexte implémenterait. Cela signifierait ajouter des getters (si nécessaire) dans la classe Context pour que les classes de stratégie obtiennent les données dont elles ont besoin.

```
public interface IOrderItemContext
{
    int GetUnits();
    Price GetUnitPrice();
}
```

Un autre problème que je vois est la logique en double dans VolumePricingStrategy:

```
var regularPrice = item.GetUnits() * item.GetUnitPrice();
```

Nous avons besoin d'un prix régulier dans certaines stratégies de tarification, car un certain seuil peut nous empêcher d'appliquer une remise.

En rendant la méthode GetTotal dans NormalPricingStrategy virtuelle et en héritant VolumePricingStrategy de NormalPricingStrategy, nous pouvons supprimer le code en double

```
@Override
	public Price GetTotal(IOrderItemContext item)
	{
		var regularPrice = super.GetTotal(item);
		Price volumeDiscount = Price.priceFromBigDecimal(BigDecimal.valueOf( 0 ));

		if (item.GetUnits() >= _volumeThreshold)
		{
			volumeDiscount = Price.opMultiply(_volumeThreshold, Price.opSubtract(item.GetUnitPrice(), _volumePrice));
		}

		return Price.opSubtract(regularPrice, volumeDiscount);
	}
```

![final]()
