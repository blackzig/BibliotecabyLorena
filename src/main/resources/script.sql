/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  zigui
 * Created: 12 de out. de 2020
 */

create database biblioteca_lorena;

use biblioteca_lorena;

create table obra(
    id bigint not null primary key auto_increment,
    titulo varchar(255) not null,
    editora varchar(255) not null,
    foto varchar(255) not null
)engine=innodb;

create table autor(
    id bigint not null primary key auto_increment,
    name varchar(255) not null,
    obra bigint not null,
    foreign key (obra) references obra (id) on update cascade
)engine=innodb;

