select* from teste.uf;
-- verifica o estado aonde CEP1 e menor do que CEP e CEP2 e maior do  que CEP
SELECT * FROM teste.uf where teste.uf.Cep1<24572 and teste.uf.Cep2>24572;
SELECT rj.*,e.UF as estado FROM teste.uf as e, teste.rj as rj where e.Cep1<24572 and e.Cep2>24572 and rj.cep = "24752-640";
SELECT rj.tp_logradouro as Endereco, rj.logradouro as Endereco,e.Nome as estado FROM teste.uf as e, teste.rj as rj where e.Cep1<24572 and e.Cep2>24572 and rj.cep = "24752-640";
 prepare backup  teste to disk = 'D:\backups\testDB.bak';
