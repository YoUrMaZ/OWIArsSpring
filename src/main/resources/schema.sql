drop table if exists categoria ;
drop table if exists producto ;
drop table if exists puntuacion ;
drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 100 increment by 1;

create table categoria (
                           id bigint not null,
                           imagen varchar(512),
                           nombre varchar(512),
                           primary key (id)
);

create table producto (
                          id bigint not null,
                          descripcion clob,
                          imagen varchar(512),
                          nombre varchar(512),
                          categoria_id bigint,
                          primary key (id)
);

create table puntuacion (
                            id bigint not null,
                            fecha timestamp,
                            puntuacion integer not null,
                            producto_id bigint,
                            primary key (id)
);

alter table producto add constraint fk_producto_categoria foreign key (categoria_id) references categoria;
alter table puntuacion add constraint fk_puntuacion_producto foreign key (producto_id) references producto;