create table "booking_food"
(
    booking_id  int not null ,
    food_id int not null,
    primary key(booking_id,food_id),
        constraint booking_fk foreign key(booking_id)
        references "booking"(id),
        constraint food_fk foreign key(food_id)
        references "food"(id)

);