delete from article_authors;
delete from authors;        
delete from reviews;
delete from review_invitations;
delete from articles;
delete from journals;
delete from users;

insert into authors (id, first_name, last_name, affiliation, email) values (2000, 'Pooja', 'Rani', 'University of Bern', 'pooja.rani@unibe.ch');
insert into authors (id, first_name, last_name, affiliation, email) values (2001, 'Ariana', 'Blasi', 'Università della Svizzera italiana', 'arianna.blasi@usi.ch');
insert into authors (id, first_name, last_name, affiliation, email) values (2002, 'Enrico', 'Fregnan', 'University of Zurich', 'fregnan@ifi.uzh.ch');
insert into authors (id, first_name, last_name, affiliation, email) values (2003, 'Josua', 'Fröhlich', 'University of Zurich', 'josua.froehlich@uzh.ch');

insert into users(id, user_type, first_name, last_name, affiliation, email, password) values (1000, 'RESEARCHER', 'Nikos', 'Diamantidis', 'AUEB', 'ndia@aueb.gr', null);
insert into users(id, user_type, first_name, last_name, affiliation, email, password) values (1001, 'RESEARCHER', 'Pooja', 'Rani', 'University of Bern', 'pooja.rani@unibe.ch', null);
insert into users(id, user_type, first_name, last_name, affiliation, email, password) values (1002, 'RESEARCHER', 'Enrico', 'Fregnan', 'University of Zurich', 'fregnan@ifi.uzh.ch', null);
insert into users(id, user_type, first_name, last_name, affiliation, email, password) values (1003, 'EDITOR', 'Paris', 'Avgeriou', 'University of Groningen', 'avgeriou@gmail.com', null);

insert into journals(id, title, issn, editor_id) values (3000, 'Journal of Systems and Software', '0164-1212', 1003);

insert into articles(id, title, summary, keywords, created_at, journal_id, correspondent_author_id)
    values(4000, 'A decade of code comment quality assessment: A systematic literature review',
    'Code comments are important artifacts in software systems and play a paramount role in many software engineering (SE) tasks...',
    'Code comments\n Documentation quality\n Systematic literature review',
    '20221124', 3000, 1001);

insert into articles(id, title, summary, keywords, created_at, journal_id, correspondent_author_id)
    values(4001, 'Graph-based visualization of merge requests for code review',
    'Code review is a software development practice aimed at assessing code quality, finding defects, and sharing knowledge among developers ...',
    'Modern code review\n Software visualization\n Empirical software engineering',
    '20221124', 3000, 1002);
    
insert into article_authors(article_id, author_id) values(4000, 2000);
insert into article_authors(article_id, author_id) values(4000, 2001);
insert into article_authors(article_id, author_id) values(4001, 2002);
insert into article_authors(article_id, author_id) values(4001, 2003);

insert into review_invitations(id, reviewer_id, article_id, created_at, accepted)
    values(5000, 1000, 4000, '20221124', null);
insert into review_invitations(id, reviewer_id, article_id, created_at, accepted)
    values(5001, 1001, 4001, '20221124', null);    

insert into reviews(id, score, author_comments, editor_comments, recommendation, submitted, created_at, submitted_at, invitation_id)
    values(6000, 60, 'Comments for author', 'Confidential comments for editor', 'ACCEPT', false, '20221101', '20221124', 5000);