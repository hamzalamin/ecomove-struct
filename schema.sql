
CREATE TYPE partner_status AS ENUM ('ACTIVE', 'INACTIVE', 'SUSPENDED');
CREATE TYPE transport_types AS ENUM ('PLANE', 'BUS', 'TRAIN');
CREATE TYPE contract_status AS ENUM ('ACTIVE', 'INACTIVE', 'SUSPENDED');
CREATE TYPE reduction_types AS ENUM ('PERCENTAGE', 'FIXED_AMOUNT');
CREATE TYPE offer_status AS ENUM ('ACTIVE', 'EXPIRED', 'SUSPENDED');

CREATE TABLE partners (
    id UUID PRIMARY KEY,
    company_name VARCHAR(255),
    contact VARCHAR(255),
    geographic_zone VARCHAR(255),
    special_conditions VARCHAR(255),
    partner_status partner_status,
    transport_type transport_types,
    creation_date DATE DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE contracts (
    id UUID PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    special_conditions FLOAT,
    conditions VARCHAR(255),
    renewable BOOLEAN,
    contract_status contract_status,
    partner_id UUID REFERENCES partners(id)
);


CREATE TABLE offers (
    id UUID PRIMARY KEY,
    offer_name VARCHAR(255),
    description VARCHAR(255),
    start_date DATE,
    end_date DATE,
    discount_value FLOAT,
    conditions VARCHAR(255),
    reduction_type reduction_types,
    offer_status offer_status,
    contact_id UUID REFERENCES contracts(id)
);


CREATE TABLE tickets (
    id UUID PRIMARY KEY,
    purchase_price FLOAT,
    sale_price FLOAT,
    sale_date DATE
);
