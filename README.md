# Especificaciones
- Java: 17
- SpringBoot: 3
- SpringCloud: 2023
- Maven: 3.6.8
- Ejecución de comandos Linux 

# Proyectos
- sofka-lab-common: clases en común entre los proyectos
- sofka-lab-customers-service: Microservicio para administrar clientes
- sofka- lab-accounts-service: Microservicio para administrar cuentas
- sofka - lab-apigateway-server: Apigateway para enrutar los endpoints
- sofka - lab-eureka-server: Registro de microservicios

# Levantar proyecto

Construir artefactos de los proyectos

```sh
$ ./build.sh
```

Construir las imagenes y levantar el proyectos
Crear archivo .env con las credenciales de base de datos, tomar como referencia template.env
```sh
$ docker-compose up --build
```

- Puerto expuesto APIGATEWAY: 8080
- Puerto expuesto EUREKA: 8761
