# Creando nuestra primera prueba

En esa sección la idea es poder crear una primera prueba basica comenzando desde un proyecto desde cero.

Para esto es necesario tener instalado y configurado el entorno con los requerimientos.

Los requerimientos son:

* Java 11+
* Maven 3.9
* (Opcional) IDE. Ej: IntelliJ IDEA 2022.31 (Community Edition)

## ¿Que necesitamos realizar para tener nuestra primera prueba?

Nos vamos a enfocar en las siguientes tareas:

* Crear un proyecto nuevo en Maven
* Agregar dependencias necesarias en el archivo pom.xml
* Crear estructura basica
* Agregar componentes basicos

Para esto, tener en cuenta que se encuentra la [Guía de usuario](https://abstracta.github.io/jmeter-java-dsl/guide/) disponible para profundizar y consultar ante cualquier duda.

## Resultado esperado

Como resultado de esta primera parte del taller vamos a obtener una prueba similar a la brindada [aqui](https://github.com/abstracta/jmeter-java-dsl-sample).

### Configuración del pom

Es necesario agregar estas dependencias al archivo pom.xml

```
    <dependency>
      <groupId>us.abstracta.jmeter</groupId>
      <artifactId>jmeter-java-dsl</artifactId>
      <version>1.29</version>
      <scope>test</scope>
    </dependency>
```
```
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.10.0</version>
      <scope>test</scope>
    </dependency>
```
```
    <dependency>
      <groupId>org.assertj</groupId>
      <artifactId>assertj-core</artifactId>
      <version>3.24.2</version>
      <scope>test</scope>
```


### Estructura y componentes basica

Esta estructura debe tener los siguientes elementos (de tener dudas sobre como crearlo puede verificar al repositorio). La prueba debe de:

* Estar indicada con @Test dentro de la clase definida
* Poseer Testplan, threadgroup y un pedido http/s
* Incluir Assetions/validaciones

# Alternativas para crear scripts

Al momento de crear nuestra prueba pueden aparecer diferentes situaciones como por ejemplo, no conocer los pedidos que el flujo utiliza o que ya tengamos automatizado el mismo en JMeter, entre otras. Para estas situaciones, JMeterDSL cuenta con estas dos alternativas entre otras:

* [Recorder](https://abstracta.github.io/jmeter-java-dsl/guide/#dsl-recorder): Nos permite grabar el flujo y una vez terminado, nos devolverá el script de JMeterDSL.
* [jmx2dsl](https://abstracta.github.io/jmeter-java-dsl/guide/#dsl-code-generation-from-jmx-file): Nos permite convertir un JMX (archivo de JMeter) a un script de JMeterDSL, para reutilizar estos scripts ya creados.

Para mas información consultar sus respectivos links.
