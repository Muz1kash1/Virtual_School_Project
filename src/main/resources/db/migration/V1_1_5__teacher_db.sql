ALTER TABLE teacher ADD COLUMN standingYears INTEGER;
ALTER TABLE teacher ADD COLUMN fullName VARCHAR(255);
ALTER TABLE teacher ADD COLUMN passport VARCHAR(10) UNIQUE;
ALTER TABLE teacher ADD COLUMN snils VARCHAR(14) UNIQUE;
