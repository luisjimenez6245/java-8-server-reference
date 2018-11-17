create database server_reference;
use server_reference;

create table tbl_archivos(
	id int not null primary key auto_increment,
    nombre nvarchar(2000),
    archivo longblob,
    tipo nvarchar(200)
);

select * from tbl_archivos;

create table tbl_Errores(	
	idError int not null primary key auto_increment,
    toString longtext,
    getClassgetName longtext,
    getLocalizedMessage longtext,
    getMessage longtext,
    getStackTrace longtext,
    fillInStackTracegetMessage longtext,
    fillInStackTracegetLocalizedMessage longtext,
    getCausegetMessage longtext,
    getCausegetClassgetName longtext,
    getCausegetLocalizedMessage longtext,
    hora datetime default now()
);

select * from tbl_Errores;

drop procedure if exists sp_RegistroErrores;
delimiter $$
create procedure sp_RegistroErrores (in opcion int,  in _toString longtext, in _getClassgetName longtext, in _getLocalizedMessage longtext, in _getMessage longtext,in _getStackTrace longtext,in _fillInStackTracegetMessage longtext,in _fillInStackTracegetLocalizedMessage longtext,in _getCausegetMessage longtext,in _getCausegetClassgetName longtext,in _getCausegetLocalizedMessage longtext)
begin
   if(opcion  = 1)
   then
		insert into tbl_Errores(toString, getClassgetName,getLocalizedMessage,getMessage,getStackTrace,fillInStackTracegetMessage,fillInStackTracegetLocalizedMessage,getCausegetMessage,getCausegetClassgetName,getCausegetLocalizedMessage)  values ( _toString,_getClassgetName,_getLocalizedMessage,_getMessage,_getStackTrace,_fillInStackTracegetMessage,_fillInStackTracegetLocalizedMessage,_getCausegetMessage,_getCausegetClassgetName,_getCausegetLocalizedMessage);
   end if;
   
   
   
   
end $$

delimiter ;