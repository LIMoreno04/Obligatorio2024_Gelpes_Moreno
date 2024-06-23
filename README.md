# Obligatorio2024_Gelpes_Moreno

Proceso de carga:
Se utiliza una estructura de hash cerrado con fecha como key, que en cada stash contiene un hash cerrado con país como key, que en cada stash tendrá un array de 50 entradas el cual en cada entrada tendrá una linked list de todas las canciones que ocupen ese puesto en ese país y ese día (el uso de linked lists es dado a casos de “empates”, siendo el primero en la tupla 197638 del csv, y ocurriendo frecuentemente desde entonces).
Se utiliza las clases BufferedReader y FileReader de Java para la lectura del archivo, y luego por cada tupla (por cada canción) se crea un objeto de clase Canción y un array con todos los datos de la misma, separados por “ “,” ” (pues las comas del csv se estaban cargando así en los String[]). Luego se hacen retoques de comodidad en relación al formato de los datos, como arreglar algunos atributos que quedan juntos, reemplazar los strings vacíos del atributo país por “World” en los casos correspondientes, o separar los artistas uno por uno y ponerlos individualmente en una linked list. A continuación se le instancian todos los atributos al objeto Canción, convirtiendo los tipos de String al que sea necesario, y se agregan también los artistas a un hash auxiliar en el que almacenaremos una linked list de todos los artistas participando en cada día. Finalmente se agregan las canciones a su respectivo top, chequeando si es que existe o no existe de antemano la estructura, creándola en caso negativo.

Memoria:
Carga - 1.454Gb
F1 - 0.005Gb
F2 - 0.008Gb
F3 - 0.010Gb
F4 - 0.004Gb
F5 - 0.008Gb




Lautaro Gelpes, Ignacio Moreno
