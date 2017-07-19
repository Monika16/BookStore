CREATE TABLE category (

	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

INSERT into category (name,description,image_url,is_active)
		values ('Thrillers','It is about Mystery!','CAT_1.png',true);
INSERT into category (name,description,image_url,is_active)
		values ('Fiction','It is about Science Fiction!','CAT_2.png',true);
INSERT into category (name,description,image_url,is_active)
		values ('Drama','It is about Drama!','CAT_3.png',true);
