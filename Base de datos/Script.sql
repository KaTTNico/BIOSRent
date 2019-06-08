use sys;

drop database if exists biosRent;

create database biosRent;

use biosRent;

CREATE TABLE Cliente (
    CI INT NOT NULL,
    NombreCompleto VARCHAR(50),
    Telefono VARCHAR(9),
    PRIMARY KEY (CI)
);

CREATE TABLE Sucursal (
    Codigo INT NOT NULL,
    Nombre VARCHAR(30) NOT NULL,
    PRIMARY KEY (Codigo)
);

CREATE TABLE Empleado (
    NombreUser VARCHAR(25) NOT NULL,
    Pass VARCHAR(15) NOT NULL,
    CodigoSucursal INT NOT NULL,
    PRIMARY KEY (NombreUser),
    FOREIGN KEY (CodigoSucursal) REFERENCES Sucursal (Codigo)
);

CREATE  TABLE Vehiculo (
    Matricula VARCHAR(7) NOT NULL,
    Tipo VARCHAR(9),
    Descripcion VARCHAR(100),
    PrecioAlquilerDiario DECIMAL(15 , 2 ) NOT NULL DEFAULT 0,
    SucursalCodigo INT,
    PRIMARY KEY (Matricula),
    FOREIGN KEY (SucursalCodigo) REFERENCES Sucursal (Codigo)
);

CREATE TABLE Alquiler (
    Id INT AUTO_INCREMENT NOT NULL,
    FechaAlquiler DATETIME NOT NULL,
    CantidadDias INT NOT NULL,
    CostoSeguro DECIMAL(15 , 2 ) NOT NULL DEFAULT 0,
    Total DECIMAL(15 , 2 ) NOT NULL DEFAULT 0,
    DepositoEnGarantia DECIMAL(15 , 2 ) NOT NULL DEFAULT 0,
    ClienteCedula INT NOT NULL,
    SucursalRetiraCodigo INT NOT NULL,
    VehiculoMatricula VARCHAR(7) NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (ClienteCedula) REFERENCES Cliente (CI),
    FOREIGN KEY (SucursalRetiraCodigo) REFERENCES Sucursal (Codigo),
    FOREIGN KEY (VehiculoMatricula) REFERENCES Vehiculo (Matricula)
);

CREATE TABLE Devoluciones (
    AlquilerId INT NOT NULL,
    SucursalCodigo INT NOT NULL,
    FechaDevolucion DATETIME NOT NULL,
    MultaAtraso DECIMAL(15 , 2 ),
    FOREIGN KEY (AlquilerId) REFERENCES Alquiler (Id),
    FOREIGN KEY (SucursalCodigo) REFERENCES Sucursal (Codigo),
    PRIMARY KEY (AlquilerId , SucursalCodigo)
);

#Sucursal test values
Insert into Sucursal values(1,'Montevideo');
Insert into Sucursal values(2,'Canelones');
Insert into Sucursal values(3,'Maldonado');
Insert into Sucursal values(4,'Rocha');
Insert into Sucursal values(5,'Treinta y Tres');
Insert into Sucursal values(6,'Colonia');
Insert into Sucursal values(7,'San José');
Insert into Sucursal values(8,'Artigas');
Insert into Sucursal values(9,'Salto');
Insert into Sucursal values(10,'Paysandú');
Insert into Sucursal values(11,'Tacuarembó');
Insert into Sucursal values(12,'Cerro Largo');
Insert into Sucursal values(13,'Rivera');
Insert into Sucursal values(14,'Lavalleja');
Insert into Sucursal values(15,'Rio Negro');
Insert into Sucursal values(16,'Soriano');
Insert into Sucursal values(17,'Flores');
Insert into Sucursal values(18,'Florida');
Insert into Sucursal values(19,'Durazno');

#Cliente test values
Insert into Cliente values(51476548,'Carlos Gonzáles',22408124);
Insert into Cliente values(35146859,'María Rodríguez',25141265);
Insert into Cliente values(41857654,'Roberto Perez',21501452);
Insert into Cliente values(31254652,'Romina Fernández',4150064);
Insert into Cliente values(35625848,'Nestor Mendez',214526523);
Insert into Cliente values(48575418,'Mariana Rey',285695124);

#Empleado test values
Insert into Empleado values('CarUser','qwerty123',15);
Insert into Empleado values('UsuRob','ytrewq321',13);
Insert into Empleado values('RomeoUser','ghjklñ486',2);
Insert into Empleado values('Maria','asdfg1234',6);
Insert into Empleado values('Federico','kilop55',14);

#Vehiculo test values
Insert into Vehiculo values('SAE4337','AUTO','Renault Twingo', 50.00,1);
Insert into Vehiculo values('RAS1452','AUTO','Fiat Argos', 65.00,6);
Insert into Vehiculo values('SCO9735','CAMIONETA','Citroen Berlingo', 70.00,7);
Insert into Vehiculo values('SEG1024','OTRO','Jac Camion', 75.00,8);
Insert into Vehiculo values('SPL1582','CAMIONETA','Toyota Hilux', 150.00,9);
Insert into Vehiculo values('SWP1451','AUTO','BMW Z4', 200.00,3);


