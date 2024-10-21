# Selenium to DSL

En esta sección se muestra como a partir de una automatización funcional realizada con Selenium y Java se pueden generar scripts de performance usando el DSL.

## Requerimientos

- Maven 3.5+
- Java 19+

## Pasos

- Clonar el siguiente [repositorio](https://github.com/abstracta/selenium-jmeter-dsl-demo)
- Ejecutar el siguiente comando para instalarlo en el repositorio local de Maven

```
    mvn clean install
```
- Agregar a nuestro proyecto Selenium todas las dependencias del DSL descripta en los pasos anteriores y agregar la dependencia recién instalada

```xml
<dependency>
  <groupId>us.abstracta</groupId>
  <artifactId>selenium-jmeter-dsl</artifactId>
  <version>${project.version}</version>
</dependency> 
```

- Registrar la extension de JUnit ```JmeterDslSeleniumRecorder``` en nuestro test Selenium.

```java
@RegisterExtension
private final JmeterDslSeleniumRecorder recorder = new JmeterDslSeleniumRecorder()
    .basePageObject(BasePage.class);
```

- Agregar ```JmeterDslSeleniumRecorder``` como proxy en las opciones del WebDriver.

```java
options.setProxy(recorder.getProxy());
```
- Ejecutar el test Selenium y revisar el script **PerformanceTest.java.** generado.