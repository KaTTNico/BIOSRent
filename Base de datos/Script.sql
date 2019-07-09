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
    Matricula VARCHAR(7) NOT NULL,
    Tipo VARCHAR(9),
    Descripcion VARCHAR(100),
    PrecioAlquilerDiario DECIMAL(15 , 2 ) NOT NULL DEFAULT 0,
    Activo bit NOT NULL default 1,
    PRIMARY KEY (Matricula)
);

CREATE TABLE VehiculoSucursal(
	MatriculaVehiculo VARCHAR(7),
    CodigoSucursal INT,
    FOREIGN KEY (MatriculaVehiculo) REFERENCES Vehiculo (Matricula),
    FOREIGN KEY (CodigoSucursal) REFERENCES Sucursal (Codigo),
    PRIMARY KEY (MatriculaVehiculo,CodigoSucursal)
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
Insert into Vehiculo values('SAE4337','AUTO','Renault Twingo', 50.00, 1);
Insert into Vehiculo values('RAS1452','AUTO','Fiat Argos', 65.00, 1);
Insert into Vehiculo values('SCO9735','CAMIONETA','Citroen Berlingo', 70.00, 1);
Insert into Vehiculo values('SEG1024','OTRO','Jac Camion', 75.00, 1);
Insert into Vehiculo values('SPL1582','CAMIONETA','Toyota Hilux', 150.00, 1);
Insert into Vehiculo values('SWP1451','AUTO','BMW Z4', 200.00, 1);

#Empelado SP
Delimiter //
Create procedure BuscarEmpleado(pNomUser varchar(25))
Begin	
	Select * from Empleado where NombreUser = pNomUser;
End//
Delimiter ;

Delimiter //
Create procedure LogueoEmpleado(pNomUser varchar(25),pPassUser varchar(15))
Begin	
	Select * from empleado where NombreUser = pNomUser and Pass = pPassUser;    
End//
Delimiter ;

#Cliente SP 
Delimiter //
create procedure BuscarCliente(pCI int)
begin 
	Select * from Cliente where CI = pCI;
End//
delimiter ;

Delimiter //
Create procedure AgregarCliente(pCI int, pNomCompleto varchar(50), pTelefono varchar(9), out pMsjError varchar(100))
cuerpo:Begin
	if(exists(Select * from Cliente where CI = pCI)) THEN 
		set pMsjError="Error ya existe el cliente";
		Leave cuerpo;
    End if;
    Insert into Cliente values(pCI,pNomCompleto, pTelefono);
End//
Delimiter ;

Delimiter //
Create procedure ModificarCliente(pCI int, pNomCompleto varchar(50), pTelefono varchar(9), out pMsjError varchar(100))
cuerpo:Begin
	if(not exists(select * from Cliente where CI = pCI)) then
		set pMsjError= "Error no existe el cliente";
        Leave cuerpo;
	End if;
    update cliente set NombreCompleto = pNomCompleto, Telefono = pTelefono where CI = pCI;
End //
Delimiter ;

Delimiter //
Create procedure EliminarCliente(pCI int, out pMsjError varchar(100))
cuerpo:begin
	if(not exists(select * from Cliente where CI = pCI)) then 
		set pMsjError= "Error no existe el cliente";
        Leave cuerpo;
	End if;
    if(exists(Select * from Alquiler where ClienteCedula = pCI)) then 
		Set pMsjError = "Error, no se puede eliminar un cliente con alquileres registrados";
		Leave cuerpo;
	End if;
	delete from Cliente where CI = pCI;
end//
Delimiter ;

#SPs Sucursal
Delimiter //
create procedure BuscarSucursal(pCodigo int)
begin 
	Select * from Sucursal where Codigo = pCodigo;
End//
delimiter ;

Delimiter //
create procedure ListarSucursal()
begin 
	Select * from Sucursal;
End//
delimiter ;

#SPs Vehiculo
Delimiter //
create procedure BuscarVehiculo(pMatricula varchar(7))
begin 
	Select * from Vehiculo where Matricula = pMatricula and Activo = 1;
End//
delimiter ;

Delimiter //
create procedure BuscarVehiculoTodos(pMatricula varchar(7))
begin 
	Select * from Vehiculo where Matricula = pMatricula;
End//
delimiter ;

Delimiter //
create procedure ListarVehiculo()
begin 
	Select * from Vehiculo where Activo = 1;
End//
delimiter ;

Delimiter //
Create procedure AgregarVehiculo(pMatricula varchar(7), pTipo varchar(9), pDescripcion varchar(100), pPrecioAlquilerDiario DECIMAL(15,2), pSucursalCodigo int, out pMsjError varchar(100))
cuerpo:Begin
	DECLARE mensajeError VARCHAR(200);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pMsjError = mensajeError;
    END;
    
	if(exists(select * from Vehiculo where Matricula = pMatricula and Activo = 1)) then
		set pMsjError= "Error ya existe el vehiculo";
        Leave cuerpo;
	End if;
    
    if(not exists(select * from Sucursal where Codigo = pSucursalCodigo)) then
		set pMsjError= "Error no existe la sucursal";
        Leave cuerpo;
	End if;
    
    set transaccionActiva=1;
    
    START TRANSACTION;
    
    set mensajeError='No se pudo agregar el vehiculo.';
    #Si estaba en baja logica
    if(exists(select * from Vehiculo where Matricula = pMatricula and Activo = 0)) then
        Update Vehiculo set Tipo = pTipo, Descripcion = pDescripcion, PrecioAlquilerDiario = pPrecioAlquilerDiario, Activo = 1 where Matricula = pMatricula;
        Insert into VehiculoSucursal (MatriculaVehiculo,CodigoSucursal) values(pMatricula,pCodigoSucursal);
        Leave cuerpo;
	End if;
    
    Insert into Vehiculo(Matricula, Tipo, Descripcion, PrecioAlquilerDiario) values(pMatricula, pTipo, pDescripcion, pPrecioAlquilerDiario);
    Insert into VehiculoSucursal (MatriculaVehiculo,CodigoSucursal) values(pMatricula,pCodigoSucursal);
    
    COMMIT;
    
    set transaccionActiva=0;
End//
Delimiter ;

Delimiter //
Create procedure ModificarVehiculo(pMatricula varchar(7), pTipo varchar(9), pDescripcion varchar(100), pPrecioAlquilerDiario DECIMAL(15,2), pSucursalCodigo int, out pMsjError varchar(100))
cuerpo:Begin
	DECLARE mensajeError VARCHAR(200);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pMsjError = mensajeError;
    END;
    
	if(not exists(select * from Vehiculo where Matricula = pMatricula and Activo = 1)) then
		set pMsjError= "Error no existe el vehiculo";
        Leave cuerpo;
	End if;
    
    set transaccionActiva=1;
    
    START TRANSACTION;
    
    set mensajeError='No se pudo modificar el vehiculo.';
    update Vehiculo set Tipo = pTipo, Descripcion = pDescripcion, PrecioAlquilerDiario = pPrecioAlquilerDiario where Matricula = pMatricula;
    update VehiculoSucursal set MatriculaVehiculo = pMatricula, CodigoSucursal = pCodigoSucursal; 
    
    COMMIT;
    
    set transaccionActiva=0;
End //
Delimiter ;

Delimiter //
Create procedure EliminarVehiculo(pMatricula varchar(7), out pMsjError varchar(100))
cuerpo:begin
	DECLARE mensajeError VARCHAR(200);
    DECLARE transaccionActiva BIT;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
		IF transaccionActiva THEN
			ROLLBACK;
		END IF;
        
        SET pMsjError = mensajeError;
    END;
    
	if(not exists(select * from Vehiculo where Matricula = pMatricula and Activo = 1)) then 
		set pMsjError= "Error no existe el vehiculo";
        Leave cuerpo;
	End if;
    
    set transaccionActiva=1;
    
    START TRANSACTION;
    
    set mensajeError='No se pudo eliminar el vehiculo.';
    
    #Si tiene que hacerce baja logica
    if(exists(select * from Alquiler where VehiculoMatricula = pMatricula)) Then
		update Vehiculo set Activo = 0 where Matricula = pMatricula;
        Leave cuerpo;
    End if;
    
	delete from Vehiculo where Matricula = pMatricula;
    
    COMMIT;
    
    set transaccionActiva=0;
end//
Delimiter ;

Delimiter //
create procedure ListarVehiculosDisponibles()
begin 
	Select 
    Vehiculo.Matricula,
    Vehiculo.Tipo,
    Vehiculo.Descripcion,
    Vehiculo.PrecioAlquilerDiario,
    Vehiculo.SucursalCodigo,
    Vehiculo.Activo 
    
    from Vehiculo 
    
    left join Alquiler on Alquiler.VehiculoMatricula = Vehiculo.Matricula 
    
    where 
    Vehiculo.Activo = 1 and 
    Alquiler.Id IS NULL OR (now() > DATE_ADD(Alquiler.FechaAlquiler, interval Alquiler.CantidadDias day));
End//
delimiter ;

Delimiter //
Create procedure AgregarAlquiler(
fechaAlquiler date, 
cantidadDias int, 
costoSeguro decimal(15,2),
total decimal(15,2),
depositoEnGarantia decimal(15,2),
clienteCedula int,
sucursalRetiraCodigo int,
matricula varchar(7),
out pMsjError varchar(100))
cuerpo:begin
	if(not exists(select * from Vehiculo where Matricula = matricula and Activo = 1)) then
		set pMsjError= "Error, el vehiculo no existe.";
        Leave cuerpo;
	end if;
    
    if(not exists(select * from Sucursal where Codigo = sucursalRetiraCodigo)) then
		set pMsjError= "Error no existe la sucursal.";
        Leave cuerpo;
	end if;
    
    if(not exists(select * from Cliente where CI = clienteCedula)) then
		set pMsjError= "Error, el cliente no existe.";
        Leave cuerpo;
	end if;
	
    if(exists (Select 
    Cliente.* from Cliente join Alquiler on Alquiler.ClienteCedula = Cliente.CI 
    where 
    Cliente.CI = clienteCedula and
    (fechaAlquiler >= DATE_ADD(Alquiler.FechaAlquiler, interval Alquiler.CantidadDias day))))then
		set pMsjError= "Error, el cliente tiene un alquiler vigente.";
		Leave cuerpo;
    end if;
    
    if(not exists (Select 
    Vehiculo.* from Vehiculo left join Alquiler on Alquiler.VehiculoMatricula = Vehiculo.Matricula 
    where 
    Vehiculo.Activo = 1 and 
    Vehiculo.Matricula = matricula and
    Alquiler.Id IS NULL OR (fechaAlquiler > DATE_ADD(Alquiler.FechaAlquiler, interval Alquiler.CantidadDias day))))then
		set pMsjError= "Error, el vehiculo no esta disponible.";
		Leave cuerpo;
    end if;
    
    insert into Alquiler(FechaAlquiler,CantidadDias,CostoSeguro,Total,DepositoEnGarantia,ClienteCedula,SucursalRetiraCodigo,SucursalRetiraCodigo) 
    values(fechaAlquiler,cantidadDias,costoSeguro,total,depositoEnGarantia,clienteCedula,sucursalRetiraCodigo,matricula);
End//
Delimiter ;

Delimiter //
Create procedure ObtenerAlquilerPendiente(clienteCedula int)
begin
	select 
    Alquiler.Id,
    Alquiler.FechaAlquiler,
    Alquiler.CantidadDias,
    Alquiler.CostoSeguro,
    Alquiler.Total,
    Alquiler.DepositoEnGarantia,
    Alquiler.ClienteCedula,
    Alquiler.SucursalRetiraCodigo,
    Alquiler.VehiculoMatricula 
    from Alquiler 
    left join Devolucion on Alquiler.Id = Devolucion.AlquilerId 
    where Alquiler.ClienteCedula = clienteCedula and Devolucion.AlquilerId is null
    order by Alquiler.FechaAlquiler desc limit 1;
End//
Delimiter ;

Delimiter //
Create procedure AgregarDevolucion(
alquilerId int,
sucursalCodigo int, 
fechaDevolucion date, 
multaAtraso decimal(15,2),
out pMsjError varchar(100))
cuerpo:begin
	if(not exists(select * from Alquiler where Id = alquilerId))then
		set pMsjError= "Error, este alquiler no existe.";
        Leave cuerpo;		
    end if;
    
	if(exists(select * from Devolucion where AlquilerId=alquilerId))then
		set pMsjError= "Error, este alquiler ya tiene una devolucion asociada.";
        Leave cuerpo;
    end if;

	if(not exists(select * from Sucursal where Codigo = sucursalCodigo)) then
		set pMsjError= "Error, la sucursal no existe.";
        Leave cuerpo;
	end if;
    
    insert into Devolucion(SucursalCodigo,FechaDevolucion,MultaAtraso) 
    values(sucursalCodigo,fechaDevolucion,multaAtraso);
End//
Delimiter ;