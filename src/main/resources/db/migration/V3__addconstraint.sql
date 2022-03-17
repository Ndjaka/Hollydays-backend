ALTER TABLE user
    ADD CONSTRAINT uc_user UNIQUE (email,name);