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

CREATE TABLE Vehiculo (
    Matricula VARCHAR(7) NOT NULL CHECK (Matricula LIKE '[a-zA-Z][a-zA-Z][a-zA-Z][0-9][0-9][0-9]'),
    Tipo VARCHAR(9) CHECK (UPPER(Tipo) IN ('AUTO' , 'CAMIONETA', 'OTRO')),
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