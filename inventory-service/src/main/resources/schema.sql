drop table if exists city;
create table city (
  city_code int auto_increment primary key,
  city_name varchar(50) not null,
  city_pincode int(8) not null
);