insert into figure_type_property_value (figure_type_propety_value_id, value) values (1, 'solid'), (2, 'dashed'), (3, 'dotted');

insert into figure_type_property (figure_type_property_id, name) values (1, 'border'), (2, 'content'), (3, 'color');

create table figure_type_property_value_xref (
    figure_type_property_id int not null,
    figure_type_property_value_id int not null,
    primary key (figure_type_property_id, figure_type_property_value_id)
);

insert into figure_type_property_value_xref (figure_type_property_id, figure_type_property_value_id)
values (1, 1), (1, 2), (1, 3);

insert into figure_type (type_id, name) values (1, 'circle'), (2, 'square'), (3, 'triangle');

create table figure_type_property_xref (
    type_id int not null,
    figure_type_property_id int not null,
    primary key (type_id, figure_type_property_id)
);

insert into figure_type_property_xref (type_id, figure_type_property_id) values (1, 1), (2, 2), (3, 3);