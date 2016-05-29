# Aerolinea
Proyecto de Fundamentos de Sistemas

-------------
Servicio Socio
-------------
Por el momento se puede crear un socio nuevo de un cliente que ya exista, para esto (yo uso el Postman) se envia una peticion POST como se ve en esta imagen.

![Socio](https://k60.kn3.net/C9EF4F031.jpg)

Los datos de documento y tipo de documento deben estar ya en la base de datos, mas especificamente en la tabla Cliente.

-------------
Servicio Busqueda
-------------

Se hace con una peticion get en una URL como esta:
/vuelos/busqueda?desde=BOG&hasta=OLH&salida=2016-05-29T12:00:00Z

![Busqueda](https://k60.kn3.net/B/6/F/8/F/3/1D5.jpg)
