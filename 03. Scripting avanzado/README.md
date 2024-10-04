# Scripting avanzado

Vamos a introducirnos en algunos conceptos mas complejos:

* Diferentes maneras de agregar un pedido POST
* Agregar headers
* Descargar el contenido estatico
* Parametrizar variables (extractores)
* Agregar Timers
* Uso de datos externos (csvDataSet)

# Buenas Practicas

A medida que nuestra prueba va adquiriendo mas complejidad, es recomendable seguir ciertos buenas practicas para facilitar y mejorar nuestras pruebas tanto a la hora de crearlas como al momento de ejecutar.

En particular alguinas buenas practicas son:

* Incluir Assertions/Validaciones
* Parametrización
* Modularización
* Transactions controllers
* HTTP Request Default
* Dashbord (vizualuzar el comportamiento de la prueba previo a las ejecuciones)
* Autostop

# Configuración de escenarios y ejecución

Aparte de la creación del script es necesario entender como JMeterDSL nos ayuda a configurar de forma mas sencilla los diferentes escenarios y como podemos ejecutar las pruebas en la nube.

* Diferentes ThreadGroups
* RPS ThreadGroups
* ShowTimeLine element
* Escalado en la nube (runIn)

# Reportes

Para poder tener toda la información una vez se ejecuten las pruebas vamos a necesitar de alguna forma de reporte o guardar los resultados. Para esto algunos metodos pueden ser:

* jtlWriter
* htmlReporter