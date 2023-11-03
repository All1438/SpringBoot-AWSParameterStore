-- DROP TABLE public.product;

CREATE TABLE public.product (
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    category varchar NULL,
    description varchar NULL,
    price numeric NULL,
    created_at timestamp NULL,
    updated_at timestamp NULL
);