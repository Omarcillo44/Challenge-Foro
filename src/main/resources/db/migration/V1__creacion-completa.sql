drop database if exists foro;
create database foro;
use foro;
drop table if exists usuarios;
create table usuarios(
                         id_usuario  bigint not null auto_increment,
                         correo_usuario varchar(100) not null,
                         pass_usuario varchar(300) not null,

                         primary key (id_usuario)
);

drop table if exists topicos;
create table topicos (
                         id_topico bigint not null auto_increment,
                         id_usuario  bigint not null,
                         contenido_topico text not null,
                        curso varchar(100),

                         primary key (id_topico),
                         foreign key (id_usuario) references usuarios (id_usuario)
);