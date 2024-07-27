# Especificaciones
Java: 17
SpringBoot: 3
SpringCloud: 2023
Maven: 3.6.8
Ejecución de comandos Linux 

# Proyectos
lab-common: clases en común entre los proyectos
lab-customers-service: Microservicio para administrar clientes
lab-accounts-service: Microservicio para administrar cuentas
lab-apigateway-server: Apigateway para enrutar los endpoints
lab-eureka-server: Registro de microservicios

# Levantar proyecto

Construir artefactos de los proyectos

```sh
$ ./build.sh
```

Construir las imagenes y levantar el proyectos
```sh
$ docker-compose up --build
```

Puerto expuesto APIGATEWAY: 8080
Puerto expuesto EUREKA: 8761
