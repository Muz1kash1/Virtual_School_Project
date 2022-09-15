ALTER TABLE teacher ALTER COLUMN passport TYPE varchar(255);
ALTER TABLE teacher RENAME COLUMN fullname TO full_name;
ALTER TABLE teacher RENAME COLUMN standingyears TO standing_years;
ALTER TABLE teacher ALTER COLUMN snils TYPE varchar(255);