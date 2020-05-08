# MayCampaignProject
Para ejecutar cada uno de los sprints he creado dentro de la parte de Test un conjunto de carpetas dónde he añadido todos los tests que he creiado necesarios.

Todo el desarrollo se ha implementado mediante TDD clásico.

## Sprint 1
+ Crear las clases básicas del proyecto
- Campaign
- UserID
- Click
- CustomerID
- Budget
- RepositorioDeClicks
+ Crear una enumeración para los tipos de clicks(Si son o no premium)
+ Chequear que el presupuesto de la campaña efectivamente disminuye en función de los clicks 
+ Crear una enumeración para los estados de las campañas(Activa, pausada y finalizada) 
+ Dar la posibilidad de modificar el estado ( siempre que previamente no esté finaliza) 
+ Controlar que no se realicen cargos cuando una campaña está finalizada o en pausa
+ Si el presupuesto se termina la campaña se finaliza 

## Sprint 2
- Controlar que no se cobren clicks por duplicado(mismo usuario en un intervalo de 15 seg) 
- Controlo que antes de añadir un click al repositorio este no esté ya duplicado

He encontrado dificultad para trabajar con timestamps y m he decantado por Date. 

## Sprint 3
Para este sprint he intentado usar patrones, había pensando en usar DTOs, pero me he decantado por usar un Factory, ya que no tengo mucha práctica aún y no lo veo con facilidad.

- Creación de diferentes tipos de Campañas, por lo que he creado una interficie y he creado las diferntes clases a partir de ahi.
- Para el presupuesto me he decantado por usar patrón Factory para simplificar.
- He modificado la enumeracion de los tipos de clicks para añadir los precios cuando no es una camapaña Standard

## Sprint 4
- He creado un repositorio de usuarios para poder realizar la función de realizar la devolución que corresponda.
- He realizado una función que busca todos los clicks de un usuario en el tiempo que determine, me devuelve otro repo de Clicks
- HE realizado la función de devolver todo lo de ese repo, teniendo en cuenta que en el caso TOP no debe superar el 5%

