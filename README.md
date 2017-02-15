### Escuela Colombiana de Ingeniería
### Arquitecturas de Software - ARSW


#### Paralelismo y concurrencia - caso de estudio


##Red de prestamistas

### EJERCICIO PARA ENTREGAR EN CLASE

La 'red de prestamistas' es un modelo de colaboración en el que un grupo de personas se compromete a prestar una suma de dinero (por ahora es una cantidad fija) a cualquier otra persona de su red que se lo solicite (es decir, cada persona de la red podrá tanto pedir prestado y siempre deberá prestar a quien se lo solicite). El programa planteado es una simulación de este modelo, en el que N prestamistas, representados por N hilos, concurrente y aleatoriamente solicitan préstamos a otros integrantes de la red. En la simulación todos tienen un saldo inicial de $500 USD, y los prestamos siempre son de $10 USD. Por regla general, quienes lleguen a la bancarrota (saldo 0), ya no podrán solicitar ni realizar más préstamos. El programa automáticamente detiene la simulación cada 10 segundos para mostrar el saldo total de los prestamistas (es decir, la sumatoria de los saldos de todos los integrantes de la red de préstamos).

1. Revise el funcionamiento del simulador, ejecutándolo varias veces a través del comando:

	```java
	mvn exec:java -	Dexec.mainClass="edu.eci.arsw.loannetsim.LoanNetworkSimulation" 
	```

2. Ahora, En la configuración de Spring (applicationContext.xml) elimine el atributo 'scope="prototype"' y pruebe de nuevo la aplicación. Cómo cambia esto el comportamiento de la aplicación?, puede de aquí concluir ([antes de revisar la documentación de Spring](http://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch04s04.html)) qué efecto tiene la propiedad 'scope'?. Una vez tenga claro esto, deje la configuración original.

3. Analice con el funcionamiento de la aplicación: por qué razón a veces el mensaje de "*** PRESS ENTER TO VIEW STATISTICS ***" se presenta antes de los últimos LOGs de préstamos?. Plantee una solución para esto.
	
	
4. Analice la lógica del programa. Qué valor se debería mostrar cada vez que se pausa la simulación?. 
Si no es consistente, identifique la región crítica donde se da la condición de carrera que da lugar a este resultado. Implemente un mecanismo de sincronización para eliminar esta condición de carrera. Ejecute de nuevo, varias veces, el programa, y rectifique que (1) el resultado ahora SÍ sea consistente, y (2) que el mismo NO llegue a un DeadLock. Para poder rectificar si un proceso Java tiene hilos en Deadlock (en caso de que el programa quede interrumpido por fuera del lapso de los 10 segundos), use los comandos jps y jstack.

	```java
	jps
	jstack IDPROCESO
	```

5. Si su solución conduce a un Deadlock, tenga en cuenta que los bloqueos anidados se deben hacer siempre en el mismo orden. Implemente una estrategia para lograr esto. De nuevo, rectifique (experimentalmente) tanto la correctitud de la salida como la ausencia de Deadlocks.

6. La clase LenderAspectAdvisor tiene un método que se quiere sea usado como 'consejo' (advice) en un aspecto cuyo 'punto de corte' son todas las transacciones realizadas (es decir, la invocación de los métodos 'lend' de cada prestamista). Dicho consejo, como podrá observar, registra en memoria cada transacción realizada; cada vez que haya un prestamista en bancarrota, muestra una alerta por pantalla y e imprime el historial de transacciones desde la última bancarrota registrada. Teniendo en cuenta lo anterior, configure la aplicación para que dicho aspecto quede habilitado y funcionando.
7. Verifique la funcionalidad. Qué error se presenta? (busque la excepción raíz generada durante la ejecución e investigue de qué se trata). Plantee una solución que no afecte el desempeño de la simulación para resolver este problema.
