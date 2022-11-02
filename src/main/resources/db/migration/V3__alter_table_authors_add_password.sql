ALTER TABLE author
    ADD COLUMN password text;

insert into author
values (1,
        'Ruan Failache',
        'ruan.failache@forum.com',
        '$2a$12$Wa.TxPUTiTU0RNrDISjlMOijxLnnPMM9v/lGV9.b2oYfwBc4e0n0m');

insert into author
values (2,
        'Eliane Gomes',
        'eli.gomes@forum.com',
        '$2a$12$Wa.TxPUTiTU0RNrDISjlMOijxLnnPMM9v/lGV9.b2oYfwBc4e0n0m');

insert into author
values (3,
        'Subaru Sakaguchi',
        'subaru.sakaguchi@forum.com',
        '$2a$12$Wa.TxPUTiTU0RNrDISjlMOijxLnnPMM9v/lGV9.b2oYfwBc4e0n0m');