insert into tb_usuario ("EMAIL", "NOME", "SENHA") values ('bruno@email.com', 'Bruno', '$2a$10$MQc3sWqD0Wu94HFYZ7MWGOFkXNoaIcliPPHPinTjGGk7Pol55NKE6');
insert into tb_permissao ("DESCRICAO") values ('ROLE_ROLE');
insert into tb_usuario_permissao ("ID_USUARIO", "ID_PERMISSAO") values (1, 1);