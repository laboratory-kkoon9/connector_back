INSERT INTO users(id, avatar, email, name, password)
VALUES (1, 'https://avatars.githubusercontent.com/u/43670900?v=4', 'kkk123@naver.com', 'Eric', '123123'),
       (2, null, 'jjj123@naver.com', 'John', '123123');

INSERT INTO profiles(id, user_id, bio, location)
VALUES (1, 1, '안녕하세요 JSCode의 Eric 부캐입니다.', 'Bucheon');

INSERT INTO skills(id, profile_id, name)
VALUES (1, 1, 'React'),
       (2, 1, 'Express'),
       (3, 1, 'Spring Framework'),
       (4, 1, 'Android');

INSERT INTO educations(id, profile_id, school, degree, field_of_study, start_date, end_date)
VALUES (1, 1, '덕산중학교',3,'공과대','2011-03-02','2014-02-28'),
       (2, 1, '덕산고등학교',3,'공과대','2014-03-02','2017-02-28');

INSERT INTO experiences(id, profile_id, company, position, description, start_date, end_date)
VALUES (1, 1, 'Google', 'Backend Developer','Senior Developer','2001-03-02', null);