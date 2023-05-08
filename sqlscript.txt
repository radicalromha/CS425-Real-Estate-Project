DROP TABLE IF EXISTS renter,agent, property,price,booking, renter_cards;

CREATE TABLE renter(
    email    text,
    last_name    text,
    first_name    text,
    addresses    text[],
    mv_in_date    date,
    desired_location        text,
    budget        numeric check (budget > 0),
    credit_cards    text[],
    getRewards      boolean,
    reward_pts  numeric check (reward_pts >= 0),
    primary key (email)
);

CREATE TABLE renter_cards(
    email       text,
    credit_card     text,
    address     text,
    primary key (credit_card),
    foreign key (email) references renter (email)
);

CREATE TABLE agent(
    email    text,
    last_name    text,
    first_name    text,
    title    text,
    agency_name    text,
    addresses    text[],
    phone_number    numeric,
    primary key (email)
);

CREATE TABLE price(
    priceID        SERIAL,
    propID        int,
    sale_price    numeric,
    rent_price    numeric,
    primary key (priceID)
);

CREATE TABLE booking(
    bookID    SERIAL,
    email    text,
    propID    int,
    credit_card    text,
    primary key (bookID),
	foreign key (email) references renter (email) on delete cascade
);

CREATE TABLE property(
    propID      int ,
    address        text,
    city        text,
    state    char(2),
    prop_type    char(1) check (prop_type = 'h' or prop_type = 'b' or prop_type = 'a' or prop_type = 'd'),
    allow_pets      boolean,
    is_furnished    boolean,
    wheelchair_access   boolean,
    sq_foot        int,
    num_Rooms    int,
    apart_type    text,
    bus_type    text,
    primary key (propID)
);

INSERT INTO property VALUES('0', '450 n racine ave', 'chicago', 'il','a', 'false', 'true', 'true', '1065', '6', 'apartment', 'null');
INSERT INTO property VALUES('1', '5231 s wabash ave', 'chicago', 'il','a', 'false', 'true', 'true', '900', '2', 'apartment', 'null');
INSERT INTO property VALUES('2', '1906 n harding ave', 'chicago', 'il','a', 'true', 'true', 'false', '1268', '7', 'apartment', 'null');
INSERT INTO property VALUES('3', '4817 w quincy st #1', 'chicago', 'il','a', 'true', 'true', 'true', '2001', '5', 'condo', 'null');
INSERT INTO property VALUES('4', '5719 n rogers ave', 'chicago', 'il','d', 'false', 'true', 'false', '1500', '5', 'null', 'null');
INSERT INTO property VALUES('5', '4605 s washtenaw ave', 'chicago', 'il','a', 'true', 'false', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('6', '11755 s burley ave', 'chicago', 'il','a', 'true', 'true', 'false', '605', '2', 'apartment', 'null');
INSERT INTO property VALUES('7', '2343 w dickens ave', 'chicago', 'il','a', 'false', 'false', 'true', '2000', '5', 'apartment', 'null');
INSERT INTO property VALUES('8', '2425 w washburne ave', 'chicago', 'il','a', 'true', 'false', 'false', '715', '2', 'apartment', 'null');
INSERT INTO property VALUES('9', '2610 w wilcox st', 'chicago', 'il','a', 'true', 'true', 'true', '800', '3', 'flat', 'null');
INSERT INTO property VALUES('10', '5305 s kilpatrick ave', 'chicago', 'il','h', 'true', 'false', 'false', '2005', '4', 'null', 'null');
INSERT INTO property VALUES('11', '6740 s wabash ave', 'chicago', 'il','a', 'false', 'true', 'true', '860', '3', 'apartment', 'null');
INSERT INTO property VALUES('12', '8054 s harper ave', 'chicago', 'il','a', 'false', 'false', 'false', '335', '1', 'apartment', 'null');
INSERT INTO property VALUES('13', '655 w irving park rd #b147', 'chicago', 'il','a', 'true', 'true', 'false', '1100', '4', 'apartment', 'null');
INSERT INTO property VALUES('14', '6515 s knox ave', 'chicago', 'il','a', 'true', 'true', 'false', '1100', '3', 'apartment', 'null');
INSERT INTO property VALUES('15', '1941 n honore st', 'chicago', 'il','a', 'false', 'true', 'false', '335', '1', 'apartment', 'null');
INSERT INTO property VALUES('16', '6927 s ashland ave', 'chicago', 'il','a', 'false', 'true', 'false', '512', '2', 'apartment', 'null');
INSERT INTO property VALUES('17', '349 w 115th st', 'chicago', 'il','a', 'true', 'false', 'true', '1100', '3', 'apartment', 'null');
INSERT INTO property VALUES('18', '5317 s maryland ave', 'chicago', 'il','h', 'true', 'true', 'true', '1400', '2', 'null', 'null');
INSERT INTO property VALUES('19', '1016 n lavergne ave', 'chicago', 'il','a', 'false', 'false', 'false', '1247', '6', 'apartment', 'null');
INSERT INTO property VALUES('20', '5255 s racine ave', 'chicago', 'il','h', 'true', 'false', 'true', '1055', '3', 'null', 'null');
INSERT INTO property VALUES('21', '1735 w rosehill dr', 'chicago', 'il','a', 'true', 'true', 'false', '1750', '7', 'apartment', 'null');
INSERT INTO property VALUES('22', '5625 s princeton ave', 'chicago', 'il','a', 'false', 'false', 'false', '800', '2', 'apartment', 'null');
INSERT INTO property VALUES('23', '1856 w 23rd st', 'chicago', 'il','a', 'true', 'true', 'false', '1200', '4', 'apartment', 'null');
INSERT INTO property VALUES('24', '2550 w harrison st', 'chicago', 'il','a', 'false', 'true', 'true', '700', '2', 'apartment', 'null');
INSERT INTO property VALUES('25', '2658 w cortland st', 'chicago', 'il','a', 'false', 'false', 'true', '1020', '5', 'apartment', 'null');
INSERT INTO property VALUES('26', '4849 w hubbard st', 'chicago', 'il','a', 'false', 'false', 'true', '1000', '2', 'apartment', 'null');
INSERT INTO property VALUES('27', '232 n peoria st', 'chicago', 'il','a', 'true', 'false', 'true', '1480', '4', 'apartment', 'null');
INSERT INTO property VALUES('28', '5715 n rogers ave', 'chicago', 'il','a', 'true', 'true', 'true', '700', '2', 'apartment', 'null');
INSERT INTO property VALUES('29', '1657 n mayfield ave', 'chicago', 'il','a', 'false', 'false', 'false', '850', '3', 'apartment', 'null');
INSERT INTO property VALUES('30', '3724 w 82nd st', 'chicago', 'il','a', 'true', 'true', 'false', '960', '4', 'apartment', 'null');
INSERT INTO property VALUES('31', '1234 w 96th st', 'chicago', 'il','a', 'false', 'false', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('32', '2145 s central park ave', 'chicago', 'il','a', 'false', 'false', 'true', '1180', '3', 'apartment', 'null');
INSERT INTO property VALUES('33', '4456 n hamlin ave', 'chicago', 'il','a', 'true', 'true', 'true', '500', '2', 'apartment', 'null');
INSERT INTO property VALUES('34', '2334 w adams st', 'chicago', 'il','a', 'true', 'true', 'true', '860', '3', 'apartment', 'null');
INSERT INTO property VALUES('35', '2751 w cermak rd', 'chicago', 'il','a', 'false', 'false', 'true', '1303', '4', 'apartment', 'null');
INSERT INTO property VALUES('36', '2503 w 47th st', 'chicago', 'il','a', 'false', 'true', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('37', '5034 w fulton st', 'chicago', 'il','a', 'true', 'true', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('38', '1349 w ancona st', 'chicago', 'il','h', 'false', 'true', 'true', '1373', '5', 'null', 'null');
INSERT INTO property VALUES('39', '3807 s lowe ave', 'chicago', 'il','a', 'true', 'false', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('40', '840 n hamlin ave', 'chicago', 'il','h', 'false', 'true', 'false', '1351', '5', 'null', 'null');
INSERT INTO property VALUES('41', '6515 s blackstone ave', 'chicago', 'il','a', 'false', 'true', 'true', '850', '3', 'apartment', 'null');
INSERT INTO property VALUES('42', '12151 s harvard ave', 'chicago', 'il','a', 'false', 'false', 'false', '200', '2', 'apartment', 'null');
INSERT INTO property VALUES('43', '2005 w fullerton ave', 'chicago', 'il','a', 'true', 'false', 'true', '1080', '7', 'apartment', 'null');
INSERT INTO property VALUES('44', '3824 w grand ave', 'chicago', 'il','a', 'false', 'true', 'false', '861', '4', 'apartment', 'null');
INSERT INTO property VALUES('45', '1028 n hamlin ave', 'chicago', 'il','a', 'false', 'false', 'true', '817', '4', 'apartment', 'null');
INSERT INTO property VALUES('46', '237 w 79th st', 'chicago', 'il','a', 'true', 'true', 'true', '860', '3', 'apartment', 'null');
INSERT INTO property VALUES('47', '6159 s marshfield ave', 'chicago', 'il','a', 'false', 'true', 'false', '1050', '4', 'apartment', 'null');
INSERT INTO property VALUES('48', '4337 s langley ave', 'chicago', 'il','a', 'false', 'true', 'true', '850', '3', 'apartment', 'null');
INSERT INTO property VALUES('49', '3012 e 78th st', 'chicago', 'il','h', 'false', 'true', 'false', '1162', '5', 'null', 'null');
INSERT INTO property VALUES('50', '1410 n avers ave', 'chicago', 'il','a', 'false', 'true', 'true', '779', '4', 'apartment', 'null');
INSERT INTO property VALUES('51', '4923 s champlain ave', 'chicago', 'il','a', 'true', 'false', 'true', '750', '2', 'apartment', 'null');
INSERT INTO property VALUES('52', '1713 n keeler ave', 'chicago', 'il','a', 'true', 'false', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('53', '525-29 n bishop st', 'chicago', 'il','h', 'true', 'false', 'false', '1300', '6', 'null', 'null');
INSERT INTO property VALUES('54', '4552 n leavitt st', 'chicago', 'il','a', 'false', 'true', 'true', '300', '2', 'apartment', 'null');
INSERT INTO property VALUES('55', '1224 n bosworth ave', 'chicago', 'il','a', 'true', 'true', 'true', '605', '2', 'apartment', 'null');
INSERT INTO property VALUES('56', '8126 s hoyne ave', 'chicago', 'il','a', 'false', 'true', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('57', '4342 s marshfield ave', 'chicago', 'il','h', 'false', 'true', 'false', '1200', '5', 'null', 'null');
INSERT INTO property VALUES('58', '2619 w jackson blvd', 'chicago', 'il','h', 'false', 'false', 'false', '1200', '2', 'null', 'null');
INSERT INTO property VALUES('59', '1753 n kedzie ave #1', 'chicago', 'il','a', 'true', 'true', 'true', '600', '2', 'apartment', 'null');
INSERT INTO property VALUES('60', '3926 s king dr', 'chicago', 'il','a', 'true', 'false', 'false', '1070', '4', 'apartment', 'null');
INSERT INTO property VALUES('61', '1359 n noble st #p-4', 'chicago', 'il','h', 'false', 'false', 'false', '1400', '3', 'null', 'null');
INSERT INTO property VALUES('62', '1228 n bosworth ave', 'chicago', 'il','a', 'false', 'true', 'true', '600', '2', 'apartment', 'null');
INSERT INTO property VALUES('63', '1457 w 47th st', 'chicago', 'il','h', 'false', 'true', 'false', '3200', '7', 'null', 'null');
INSERT INTO property VALUES('64', '2894 e 94th st', 'chicago', 'il','a', 'true', 'false', 'false', '400', '2', 'apartment', 'null');
INSERT INTO property VALUES('65', '4830 w congress pkwy', 'chicago', 'il','h', 'true', 'false', 'true', '1900', '5', 'null', 'null');
INSERT INTO property VALUES('66', '2351 w maypole ave', 'chicago', 'il','a', 'true', 'true', 'false', '425', '2', 'apartment', 'null');
INSERT INTO property VALUES('67', '2816 n southport ave', 'chicago', 'il','a', 'true', 'false', 'true', '1185', '4', 'apartment', 'null');
INSERT INTO property VALUES('68', '8850 s genoa ave', 'chicago', 'il','a', 'false', 'false', 'true', '1100', '3', 'apartment', 'null');
INSERT INTO property VALUES('69', '11757 s burley ave', 'chicago', 'il','a', 'true', 'true', 'false', '860', '3', 'apartment', 'null');
INSERT INTO property VALUES('70', '1050 w monroe st', 'chicago', 'il','a', 'false', 'true', 'false', '1500', '5', 'apartment', 'null');
INSERT INTO property VALUES('71', '4231 w arthington st', 'chicago', 'il','a', 'true', 'true', 'true', '860', '3', 'apartment', 'null');
INSERT INTO property VALUES('72', '2656-58 w cortland st', 'chicago', 'il','a', 'false', 'false', 'true', '950', '5', 'apartment', 'null');
INSERT INTO property VALUES('73', '1835 n honore st', 'chicago', 'il','a', 'false', 'false', 'false', '1100', '3', 'apartment', 'null');
INSERT INTO property VALUES('74', '1804 n harding ave', 'chicago', 'il','a', 'false', 'true', 'true', '1750', '7', 'apartment', 'null');
INSERT INTO property VALUES('75', '6025 n kimball ave', 'chicago', 'il','a', 'true', 'false', 'true', '598', '2', 'apartment', 'null');
INSERT INTO property VALUES('76', '3735 s california ave', 'chicago', 'il','a', 'true', 'false', 'false', '860', '3', 'apartment', 'null');
INSERT INTO property VALUES('77', '2429 w washburne ave', 'chicago', 'il','a', 'false', 'true', 'false', '565', '1', 'apartment', 'null');
INSERT INTO property VALUES('78', '7525 s maryland ave', 'chicago', 'il','a', 'false', 'false', 'true', '1400', '4', 'apartment', 'null');
INSERT INTO property VALUES('79', '8835 s princeton ave', 'chicago', 'il','d', 'true', 'false', 'true', '1620', '5', 'null', 'null');
INSERT INTO property VALUES('80', '4531 s cottage grove ave', 'chicago', 'il','a', 'true', 'true', 'true', '883', '4', 'apartment', 'null');
INSERT INTO property VALUES('81', '1334 w walton st', 'chicago', 'il','a', 'false', 'false', 'false', '2099', '7', 'townhouse', 'null');
INSERT INTO property VALUES('82', '7251 s coles ave', 'chicago', 'il','a', 'false', 'true', 'true', '1306', '6', 'apartment', 'null');
INSERT INTO property VALUES('83', '5213 s damen ave', 'chicago', 'il','h', 'false', 'true', 'false', '2000', '4', 'null', 'null');
INSERT INTO property VALUES('84', '4700 w fulton st', 'chicago', 'il','h', 'true', 'false', 'false', '1100', '4', 'null', 'null');
INSERT INTO property VALUES('85', '1107 n homan ave', 'chicago', 'il','a', 'false', 'true', 'false', '903', '4', 'apartment', 'null');
INSERT INTO property VALUES('86', '516 n western ave', 'chicago', 'il','a', 'true', 'true', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('87', '1231 w draper st', 'chicago', 'il','a', 'true', 'true', 'false', '1180', '3', 'apartment', 'null');
INSERT INTO property VALUES('88', '205 w 95th st', 'chicago', 'il','a', 'true', 'true', 'false', '900', '3', 'apartment', 'null');
INSERT INTO property VALUES('89', '8224 s exchange ave', 'chicago', 'il','a', 'false', 'true', 'true', '860', '3', 'apartment', 'null');
INSERT INTO property VALUES('90', '3310 n harding ave', 'chicago', 'il','a', 'false', 'false', 'true', '945', '3', 'apartment', 'null');
INSERT INTO property VALUES('91', '1147 w 112th pl', 'chicago', 'il','h', 'true', 'true', 'false', '820', '6', 'null', 'null');
INSERT INTO property VALUES('92', '5924 s wabash ave', 'chicago', 'il','a', 'false', 'true', 'false', '1180', '3', 'apartment', 'null');
INSERT INTO property VALUES('93', '4838 s ashland ave', 'chicago', 'il','a', 'false', 'true', 'true', '750', '3', 'apartment', 'null');
INSERT INTO property VALUES('94', '6440 s ingleside ave', 'chicago', 'il','a', 'true', 'true', 'true', '500', '2', 'apartment', 'null');
INSERT INTO property VALUES('95', '5347 w van buren st', 'chicago', 'il','h', 'false', 'true', 'true', '1426', '6', 'null', 'null');
INSERT INTO property VALUES('96', '3540 w le moyne st', 'chicago', 'il','a', 'false', 'true', 'false', '782', '3', 'apartment', 'null');
INSERT INTO property VALUES('97', '406 s kostner ave', 'chicago', 'il','a', 'false', 'true', 'false', '860', '3', 'apartment', 'null');
INSERT INTO property VALUES('98', '9031 s carpenter st', 'chicago', 'il','a', 'true', 'true', 'true', '1100', '3', 'apartment', 'null');
INSERT INTO property VALUES('99', '2803 johnson ave', 'san luis obispo', 'ca','b', 'false', 'true', 'true', '1107', '3', 'null', 'office');
INSERT INTO property VALUES('100', '136 diamond st', 'auburn', 'ca','b', 'true', 'true', 'false', '1732', '5', 'null', 'store');
INSERT INTO property VALUES('101', '40875 lois ct', 'hemet', 'ca','h', 'false', 'true', 'true', '3704', '9', 'null', 'null');
INSERT INTO property VALUES('102', '6212 gretchen ct', 'fontana', 'ca','d', 'true', 'false', 'false', '1222', '5', 'null', 'null');
INSERT INTO property VALUES('103', '555 e 8th st', 'beaumont', 'ca','b', 'true', 'false', 'true', '1415', '5', 'null', 'office');
INSERT INTO property VALUES('104', '3667 mariella st', 'riverside', 'ca','h', 'false', 'false', 'false', '720', '3', 'null', 'null');
INSERT INTO property VALUES('105', '1823 mendoza dr', 'firebaugh', 'ca','a', 'true', 'false', 'false', '1560', '5', 'flat', 'null');
INSERT INTO property VALUES('106', '462 oriente st', 'daly city', 'ca','b', 'false', 'true', 'true', '1400', '5', 'null', 'warehouse');
INSERT INTO property VALUES('107', '6107 callaway pl', 'rancho cucamonga', 'ca','b', 'true', 'true', 'false', '1198', '3', 'null', 'store');
INSERT INTO property VALUES('108', '322 e mendocino ave', 'stockton', 'ca','b', 'true', 'false', 'false', '1930', '5', 'null', 'office');
INSERT INTO property VALUES('109', '4183 cosmo st', 'san diego', 'ca','b', 'true', 'true', 'false', '1306', '4', 'null', 'office');
INSERT INTO property VALUES('110', '22136 viscanio rd', 'woodland hills', 'ca','d', 'false', 'false', 'false', '2472', '6', 'null', 'null');
INSERT INTO property VALUES('111', '1511 buena vista st', 'brentwood', 'ca','h', 'true', 'false', 'false', '816', '3', 'null', 'null');
INSERT INTO property VALUES('112', '4799 lyric ln', 'san jose', 'ca','d', 'true', 'false', 'true', '1796', '6', 'null', 'null');
INSERT INTO property VALUES('113', '115 lechuza ln', 'spring valley', 'ca','h', 'false', 'false', 'true', '1076', '4', 'null', 'null');
INSERT INTO property VALUES('114', '921 pasadena ln', 'modesto', 'ca','b', 'false', 'true', 'true', '1828', '5', 'null', 'store');
INSERT INTO property VALUES('115', '1884 playa riviera dr', 'cardiff by the sea', 'ca','b', 'false', 'false', 'true', '2373', '7', 'null', 'store');
INSERT INTO property VALUES('116', '170 w 50th st', 'san bernardino', 'ca','b', 'false', 'true', 'true', '2560', '6', 'null', 'office');
INSERT INTO property VALUES('117', '564 lochridge dr', 'san jose', 'ca','b', 'false', 'false', 'true', '1369', '3', 'null', 'store');
INSERT INTO property VALUES('118', '525 w roberts ave', 'fresno', 'ca','a', 'false', 'false', 'false', '1396', '4', 'condo', 'null');
INSERT INTO property VALUES('119', '5652 linda rosa ave', 'la jolla', 'ca','a', 'false', 'true', 'false', '2773', '6', 'flat', 'null');
INSERT INTO property VALUES('120', '3283 desertwood ln', 'san jose', 'ca','b', 'false', 'false', 'true', '3520', '7', 'null', 'office');
INSERT INTO property VALUES('121', '607 vanessa dr', 'san mateo', 'ca','h', 'true', 'false', 'true', '2016', '5', 'null', 'null');
INSERT INTO property VALUES('122', '11 dorian way', 'san rafael', 'ca','b', 'false', 'false', 'true', '1648', '5', 'null', 'office');
INSERT INTO property VALUES('123', '25784 delphinium ave', 'moreno valley', 'ca','b', 'false', 'true', 'false', '2745', '5', 'null', 'store');
INSERT INTO property VALUES('124', '1209 mason st', 'napa', 'ca','h', 'true', 'false', 'true', '1129', '4', 'null', 'null');
INSERT INTO property VALUES('125', '9487 questa pointe', 'san diego', 'ca','d', 'true', 'false', 'false', '1232', '4', 'null', 'null');
INSERT INTO property VALUES('126', '5840 calvine rd', 'sacramento', 'ca','b', 'true', 'false', 'true', '605', '2', 'null', 'office');
INSERT INTO property VALUES('127', '341 s brent st', 'ventura', 'ca','d', 'false', 'false', 'false', '3593', '7', 'null', 'null');
INSERT INTO property VALUES('128', '1529 hoover ave', 'national city', 'ca','a', 'false', 'true', 'true', '908', '3', 'townhouse', 'null');
INSERT INTO property VALUES('129', '2360 el camino dr', 'turlock', 'ca','h', 'true', 'false', 'true', '1667', '4', 'null', 'null');
INSERT INTO property VALUES('130', '4304 robbins st', 'san diego', 'ca','b', 'false', 'false', 'false', '1910', '5', 'null', 'office');
INSERT INTO property VALUES('131', '7721 cherokee trl', 'yucca valley', 'ca','a', 'false', 'true', 'true', '1184', '4', 'condo', 'null');
INSERT INTO property VALUES('132', '230 n lyn cir', 'palm springs', 'ca','b', 'true', 'true', 'false', '1491', '5', 'null', 'store');
INSERT INTO property VALUES('133', '522 sycamore pl', 'manteca', 'ca','a', 'false', 'false', 'true', '1266', '4', 'flat', 'null');
INSERT INTO property VALUES('134', '843 chilicothe st', 'south lake tahoe', 'ca','a', 'false', 'false', 'true', '1646', '4', 'flat', 'null');
INSERT INTO property VALUES('135', '4109 greenbush ave', 'sherman oaks', 'ca','d', 'true', 'true', 'true', '2900', '7', 'null', 'null');
INSERT INTO property VALUES('136', '11810 villa hermosa', 'moreno valley', 'ca','b', 'true', 'true', 'false', '1591', '5', 'null', 'warehouse');
INSERT INTO property VALUES('137', '5451 n state st', 'fresno', 'ca','d', 'true', 'true', 'true', '2446', '6', 'null', 'null');
INSERT INTO property VALUES('138', '3622 s monitor cir', 'stockton', 'ca','h', 'true', 'true', 'true', '1712', '5', 'null', 'null');
INSERT INTO property VALUES('139', '8667 jackie dr', 'san diego', 'ca','a', 'true', 'false', 'true', '2687', '8', 'townhouse', 'null');
INSERT INTO property VALUES('140', '414 reina dr', 'dinuba', 'ca','d', 'false', 'false', 'false', '1806', '6', 'null', 'null');
INSERT INTO property VALUES('141', '4995 benedict ct', 'oak park', 'ca','b', 'false', 'true', 'false', '2921', '6', 'null', 'store');
INSERT INTO property VALUES('142', '2144 rexford dr', 'san diego', 'ca','b', 'false', 'true', 'true', '1006', '3', 'null', 'warehouse');
INSERT INTO property VALUES('143', '4065 porte ln 165', 'san diego', 'ca','a', 'false', 'true', 'true', '912', '4', 'condo', 'null');
INSERT INTO property VALUES('144', '1211 king ave', 'corcoran', 'ca','a', 'false', 'false', 'true', '1500', '5', 'townhouse', 'null');
INSERT INTO property VALUES('145', '5403 saddleback ct', 'el sobrante', 'ca','a', 'false', 'false', 'true', '927', '4', 'townhouse', 'null');
INSERT INTO property VALUES('146', '210 summit rd', 'walnut creek', 'ca','a', 'false', 'true', 'false', '1706', '7', 'condo', 'null');
INSERT INTO price (propID,sale_price,rent_price) VALUES('0', '21504.0', '640.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('1', '20664.0', '615.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('2', '106848.0', '3180.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('3', '37800.0', '1125.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('4', '38640.0', '1150.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('5', '11928.0', '355.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('6', '26712.0', '795.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('7', '67200.0', '2000.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('8', '87360.0', '2600.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('9', '42000.0', '1250.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('10', '37800.0', '1125.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('11', '19488.0', '580.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('12', '16766.4', '499.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('13', '28560.0', '850.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('14', '22680.0', '675.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('15', '17808.0', '530.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('16', '18312.0', '545.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('17', '24864.0', '740.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('18', '26040.0', '775.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('19', '83160.0', '2475.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('20', '18816.0', '560.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('21', '23352.0', '695.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('22', '19320.0', '575.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('23', '159465.6', '4746.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('24', '25200.0', '750.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('25', '35280.0', '1050.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('26', '23856.0', '710.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('27', '68376.0', '2035.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('28', '19320.0', '575.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('29', '27686.4', '824.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('30', '57120.0', '1700.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('31', '11928.0', '355.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('32', '25368.0', '755.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('33', '18144.0', '540.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('34', '20496.0', '610.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('35', '172065.6', '5121.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('36', '19992.0', '595.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('37', '23352.0', '695.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('38', '42840.0', '1275.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('39', '24360.0', '725.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('40', '45360.0', '1350.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('41', '30206.4', '899.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('42', '15120.0', '450.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('43', '100800.0', '3000.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('44', '58800.0', '1750.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('45', '58800.0', '1750.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('46', '20496.0', '610.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('47', '31449.6', '936.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('48', '40320.0', '1200.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('49', '40320.0', '1200.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('50', '58800.0', '1750.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('51', '29366.4', '874.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('52', '11928.0', '355.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('53', '50400.0', '1500.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('54', '28190.4', '839.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('55', '26712.0', '795.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('56', '19992.0', '595.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('57', '35280.0', '1050.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('58', '21840.0', '650.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('59', '23352.0', '695.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('60', '48720.0', '1450.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('61', '26040.0', '775.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('62', '21000.0', '625.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('63', '40320.0', '1200.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('64', '17640.0', '525.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('65', '16800.0', '500.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('66', '19992.0', '595.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('67', '19152.0', '570.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('68', '22680.0', '675.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('69', '20496.0', '610.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('70', '97776.0', '2910.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('71', '20496.0', '610.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('72', '36960.0', '1100.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('73', '24864.0', '740.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('74', '23352.0', '695.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('75', '34440.0', '1025.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('76', '20496.0', '610.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('77', '70560.0', '2100.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('78', '12936.0', '385.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('79', '44520.0', '1325.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('80', '53592.0', '1595.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('81', '31920.0', '950.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('82', '80640.0', '2400.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('83', '30240.0', '900.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('84', '31920.0', '950.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('85', '57120.0', '1700.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('86', '19992.0', '595.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('87', '25368.0', '755.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('88', '11928.0', '355.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('89', '20496.0', '610.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('90', '21840.0', '650.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('91', '17472.0', '520.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('92', '25200.0', '750.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('93', '33600.0', '1000.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('94', '18312.0', '545.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('95', '34440.0', '1025.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('96', '50400.0', '1500.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('97', '20496.0', '610.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('98', '24864.0', '740.0');
INSERT INTO price (propID,sale_price,rent_price) VALUES('99', '55632.5', '1159.01');
INSERT INTO price (propID,sale_price,rent_price) VALUES('100', '71258.4', '1484.55');
INSERT INTO price (propID,sale_price,rent_price) VALUES('101', '143947.1', '2998.9');
INSERT INTO price (propID,sale_price,rent_price) VALUES('102', '110043.5', '2292.57');
INSERT INTO price (propID,sale_price,rent_price) VALUES('103', '96156.0', '2003.25');
INSERT INTO price (propID,sale_price,rent_price) VALUES('104', '46719.9', '973.33');
INSERT INTO price (propID,sale_price,rent_price) VALUES('105', '69314.2', '1444.05');
INSERT INTO price (propID,sale_price,rent_price) VALUES('106', '175856.4', '3663.68');
INSERT INTO price (propID,sale_price,rent_price) VALUES('107', '62402.4', '1300.05');
INSERT INTO price (propID,sale_price,rent_price) VALUES('108', '95908.8', '1998.1');
INSERT INTO price (propID,sale_price,rent_price) VALUES('109', '45790.9', '953.98');
INSERT INTO price (propID,sale_price,rent_price) VALUES('110', '51271.0', '1068.15');
INSERT INTO price (propID,sale_price,rent_price) VALUES('111', '28392.0', '591.5');
INSERT INTO price (propID,sale_price,rent_price) VALUES('112', '100593.6', '2095.7');
INSERT INTO price (propID,sale_price,rent_price) VALUES('113', '20964.5', '436.76');
INSERT INTO price (propID,sale_price,rent_price) VALUES('114', '117678.2', '2451.63');
INSERT INTO price (propID,sale_price,rent_price) VALUES('115', '132505.2', '2760.53');
INSERT INTO price (propID,sale_price,rent_price) VALUES('116', '72353.4', '1507.36');
INSERT INTO price (propID,sale_price,rent_price) VALUES('117', '23252.4', '484.43');
INSERT INTO price (propID,sale_price,rent_price) VALUES('118', '41199.8', '858.33');
INSERT INTO price (propID,sale_price,rent_price) VALUES('119', '61204.5', '1275.09');
INSERT INTO price (propID,sale_price,rent_price) VALUES('120', '380307.2', '7923.07');
INSERT INTO price (propID,sale_price,rent_price) VALUES('121', '108912.4', '2269.01');
INSERT INTO price (propID,sale_price,rent_price) VALUES('122', '92385.3', '1924.69');
INSERT INTO price (propID,sale_price,rent_price) VALUES('123', '136447.2', '2842.65');
INSERT INTO price (propID,sale_price,rent_price) VALUES('124', '62997.0', '1312.44');
INSERT INTO price (propID,sale_price,rent_price) VALUES('125', '55928.9', '1165.19');
INSERT INTO price (propID,sale_price,rent_price) VALUES('126', '34731.9', '723.58');
INSERT INTO price (propID,sale_price,rent_price) VALUES('127', '396607.4', '8262.65');
INSERT INTO price (propID,sale_price,rent_price) VALUES('128', '57942.0', '1207.13');
INSERT INTO price (propID,sale_price,rent_price) VALUES('129', '79671.6', '1659.83');
INSERT INTO price (propID,sale_price,rent_price) VALUES('130', '153187.2', '3191.4');
INSERT INTO price (propID,sale_price,rent_price) VALUES('131', '81510.2', '1698.13');
INSERT INTO price (propID,sale_price,rent_price) VALUES('132', '46898.1', '977.04');
INSERT INTO price (propID,sale_price,rent_price) VALUES('133', '24586.5', '512.22');
INSERT INTO price (propID,sale_price,rent_price) VALUES('134', '246299.2', '5131.23');
INSERT INTO price (propID,sale_price,rent_price) VALUES('135', '351747.2', '7328.07');
INSERT INTO price (propID,sale_price,rent_price) VALUES('136', '126165.6', '2628.45');
INSERT INTO price (propID,sale_price,rent_price) VALUES('137', '112313.6', '2339.87');
INSERT INTO price (propID,sale_price,rent_price) VALUES('138', '169025.4', '3521.36');
INSERT INTO price (propID,sale_price,rent_price) VALUES('139', '61812.5', '1287.76');
INSERT INTO price (propID,sale_price,rent_price) VALUES('140', '76072.2', '1584.84');
INSERT INTO price (propID,sale_price,rent_price) VALUES('141', '86370.4', '1799.38');
INSERT INTO price (propID,sale_price,rent_price) VALUES('142', '92814.4', '1933.63');
INSERT INTO price (propID,sale_price,rent_price) VALUES('143', '41669.4', '868.11');
INSERT INTO price (propID,sale_price,rent_price) VALUES('144', '44548.3', '928.09');
INSERT INTO price (propID,sale_price,rent_price) VALUES('145', '50554.0', '1053.21');
INSERT INTO price (propID,sale_price,rent_price) VALUES('146', '75395.3', '1570.74');
INSERT INTO renter VALUES('mstoneman@aol.com', 'stoneman', 'mechelle', ARRAY ['4456 n hamlin ave','12151 s harvard ave'], DATE '8/11/2025', '4337 s langley ave', '186000', ARRAY ['1477512277819440'], 'false');
INSERT INTO renter_cards VALUES('mstoneman@aol.com', '1477512277819440', '4456 n hamlin ave');
INSERT INTO renter VALUES('tfreitag@google.com', 'freitag', 'tyesha', ARRAY ['2145 s central park ave','2343 w dickens ave'], DATE '1/4/2024', '4605 s washtenaw ave', '97000', ARRAY ['1435191941669060'], 'true');
INSERT INTO renter_cards VALUES('tfreitag@google.com', '1435191941669060', '2145 s central park ave');
INSERT INTO renter VALUES('dstoecker@outlook.com', 'stoecker', 'dean', ARRAY ['3724 w 82nd st'], DATE '2/15/2025', '4456 n hamlin ave', '862000', ARRAY ['1680330806462870'], 'true');
INSERT INTO renter_cards VALUES('dstoecker@outlook.com', '1680330806462870', '3724 w 82nd st');
INSERT INTO renter VALUES('apickney@aol.com', 'pickney', 'annelle', ARRAY ['5034 w fulton st'], DATE '11/17/2026', '237 w 79th st', '928000', ARRAY ['1630348767610150'], 'true');
INSERT INTO renter_cards VALUES('apickney@aol.com', '1630348767610150', '5034 w fulton st');
INSERT INTO renter VALUES('mtacy@aol.com', 'tacy', 'margareta', ARRAY ['655 w irving park rd #b147','6515 s blackstone ave'], DATE '10/2/2023', '6740 s wabash ave', '371000', ARRAY ['1707792765281290'], 'false');
INSERT INTO renter_cards VALUES('mtacy@aol.com', '1707792765281290', '655 w irving park rd #b147');
INSERT INTO renter VALUES('mplacencia@aol.com', 'placencia', 'meghann', ARRAY ['3012 e 78th st'], DATE '7/17/2026', '2610 w wilcox st', '345000', ARRAY ['1495333224677030'], 'true');
INSERT INTO renter_cards VALUES('mplacencia@aol.com', '1495333224677030', '3012 e 78th st');
INSERT INTO renter VALUES('kcieslak@yahoo.com', 'cieslak', 'kendrick', ARRAY ['2751 w cermak rd','6740 s wabash ave'], DATE '12/26/2023', '1735 w rosehill dr', '838000', ARRAY ['1197569635449980'], 'false');
INSERT INTO renter_cards VALUES('kcieslak@yahoo.com', '1197569635449980', '2751 w cermak rd');
INSERT INTO renter VALUES('pisenberg@yahoo.com', 'isenberg', 'polly', ARRAY ['4849 w hubbard st'], DATE '11/12/2024', '232 n peoria st', '349000', ARRAY ['1920155374283520'], 'false');
INSERT INTO renter_cards VALUES('pisenberg@yahoo.com', '1920155374283520', '4849 w hubbard st');
INSERT INTO renter VALUES('eracicot@yahoo.com', 'racicot', 'evelyne', ARRAY ['2503 w 47th st'], DATE '7/29/2026', '3012 e 78th st', '929000', ARRAY ['1727663985033520'], 'false');
INSERT INTO renter_cards VALUES('eracicot@yahoo.com', '1727663985033520', '2503 w 47th st');
INSERT INTO renter VALUES('adelaune@yahoo.com', 'delaune', 'augustus', ARRAY ['840 n hamlin ave'], DATE '10/27/2026', '1349 w ancona st', '618000', ARRAY ['1349726556655150'], 'true');
INSERT INTO renter_cards VALUES('adelaune@yahoo.com', '1349726556655150', '840 n hamlin ave');
INSERT INTO renter VALUES('sclient@google.com', 'client', 'shawanda', ARRAY ['5715 n rogers ave','2658 w cortland st'], DATE '3/21/2024', '12151 s harvard ave', '320000', ARRAY ['1075229142795830'], 'false');
INSERT INTO renter_cards VALUES('sclient@google.com', '1075229142795830', '5715 n rogers ave');
INSERT INTO renter VALUES('lcoffield@outlook.com', 'coffield', 'loura', ARRAY ['1657 n mayfield ave'], DATE '7/23/2023', '450 n racine ave', '399000', ARRAY ['1905087392097200'], 'true');
INSERT INTO renter_cards VALUES('lcoffield@outlook.com', '1905087392097200', '1657 n mayfield ave');
INSERT INTO renter VALUES('lmachin@google.com', 'machin', 'lorriane', ARRAY ['6927 s ashland ave','2425 w washburne ave'], DATE '12/25/2023', '8054 s harper ave', '871000', ARRAY ['1521137123640960'], 'false');
INSERT INTO renter_cards VALUES('lmachin@google.com', '1521137123640960', '6927 s ashland ave');
INSERT INTO renter VALUES('losier@outlook.com', 'osier', 'lacey', ARRAY ['11755 s burley ave'], DATE '6/20/2023', '4817 w quincy st #1', '785000', ARRAY ['1047820372154230'], 'false');
INSERT INTO renter_cards VALUES('losier@outlook.com', '1047820372154230', '11755 s burley ave');
INSERT INTO renter VALUES('nmalchow@google.com', 'malchow', 'nicki', ARRAY ['2005 w fullerton ave','8054 s harper ave'], DATE '8/4/2025', '4849 w hubbard st', '290000', ARRAY ['1325259547167940'], 'true');
INSERT INTO renter_cards VALUES('nmalchow@google.com', '1325259547167940', '2005 w fullerton ave');
INSERT INTO renter VALUES('sbodiford@google.com', 'bodiford', 'sidney', ARRAY ['1941 n honore st','5231 s wabash ave'], DATE '9/14/2026', '1657 n mayfield ave', '747000', ARRAY ['1602645637551950'], 'true');
INSERT INTO renter_cards VALUES('sbodiford@google.com', '1602645637551950', '1941 n honore st');
INSERT INTO renter VALUES('bcun@google.com', 'cun', 'barbie', ARRAY ['6515 s knox ave'], DATE '12/27/2023', '1856 w 23rd st', '525000', ARRAY ['1312444120404150'], 'true');
INSERT INTO renter_cards VALUES('bcun@google.com', '1312444120404150', '6515 s knox ave');
INSERT INTO renter VALUES('ehanshaw@google.com', 'hanshaw', 'elden', ARRAY ['4605 s washtenaw ave'], DATE '4/17/2025', '1016 n lavergne ave', '134000', ARRAY ['1266075649572850'], 'true');
INSERT INTO renter_cards VALUES('ehanshaw@google.com', '1266075649572850', '4605 s washtenaw ave');
INSERT INTO renter VALUES('bloggins@aol.com', 'loggins', 'blossom', ARRAY ['2334 w adams st','5625 s princeton ave'], DATE '3/20/2026', '2425 w washburne ave', '520000', ARRAY ['1581746598143260'], 'false');
INSERT INTO renter_cards VALUES('bloggins@aol.com', '1581746598143260', '2334 w adams st');
INSERT INTO renter VALUES('jdennie@outlook.com', 'dennie', 'joseph', ARRAY ['6515 s blackstone ave'], DATE '5/25/2024', '6515 s knox ave', '88000', ARRAY ['1118104044859330'], 'true');
INSERT INTO renter_cards VALUES('jdennie@outlook.com', '1118104044859330', '6515 s blackstone ave');
INSERT INTO renter VALUES('pmarkley@google.com', 'markley', 'pattie', ARRAY ['349 w 115th st'], DATE '3/23/2024', '1941 n honore st', '637000', ARRAY ['1260028947643510'], 'false');
INSERT INTO renter_cards VALUES('pmarkley@google.com', '1260028947643510', '349 w 115th st');
INSERT INTO renter VALUES('gspahn@google.com', 'spahn', 'genevieve', ARRAY ['237 w 79th st'], DATE '7/11/2023', '2343 w dickens ave', '113000', ARRAY ['1658052102441210'], 'true');
INSERT INTO renter_cards VALUES('gspahn@google.com', '1658052102441210', '237 w 79th st');
INSERT INTO renter VALUES('ljaworski@google.com', 'jaworski', 'luciano', ARRAY ['4817 w quincy st #1','5719 n rogers ave'], DATE '4/30/2024', '5255 s racine ave', '1000000', ARRAY ['1323435004412840'], 'false');
INSERT INTO renter_cards VALUES('ljaworski@google.com', '1323435004412840', '4817 w quincy st #1');
INSERT INTO renter VALUES('nmahler@yahoo.com', 'mahler', 'noe', ARRAY ['5719 n rogers ave'], DATE '5/16/2025', '2145 s central park ave', '294000', ARRAY ['1362069516858360'], 'false');
INSERT INTO renter_cards VALUES('nmahler@yahoo.com', '1362069516858360', '5719 n rogers ave');
INSERT INTO renter VALUES('kbransford@yahoo.com', 'bransford', 'karri', ARRAY ['5625 s princeton ave','6515 s knox ave'], DATE '12/31/2023', '349 w 115th st', '656000', ARRAY ['1470194613769180'], 'true');
INSERT INTO renter_cards VALUES('kbransford@yahoo.com', '1470194613769180', '5625 s princeton ave');
INSERT INTO renter VALUES('lbonney@outlook.com', 'bonney', 'larae', ARRAY ['1028 n hamlin ave'], DATE '3/31/2025', '2751 w cermak rd', '592000', ARRAY ['1873315829610990'], 'false');
INSERT INTO renter_cards VALUES('lbonney@outlook.com', '1873315829610990', '1028 n hamlin ave');
INSERT INTO renter VALUES('sfierros@google.com', 'fierros', 'sharita', ARRAY ['5317 s maryland ave'], DATE '5/21/2026', '2503 w 47th st', '800000', ARRAY ['1744366622144490'], 'false');
INSERT INTO renter_cards VALUES('sfierros@google.com', '1744366622144490', '5317 s maryland ave');
INSERT INTO renter VALUES('fperla@yahoo.com', 'perla', 'frederica', ARRAY ['1234 w 96th st'], DATE '4/2/2025', '2658 w cortland st', '847000', ARRAY ['1741105904511140'], 'true');
INSERT INTO renter_cards VALUES('fperla@yahoo.com', '1741105904511140', '1234 w 96th st');
INSERT INTO renter VALUES('melder@outlook.com', 'elder', 'mara', ARRAY ['2425 w washburne ave'], DATE '11/10/2024', '5625 s princeton ave', '234000', ARRAY ['1824454735604100'], 'false');
INSERT INTO renter_cards VALUES('melder@outlook.com', '1824454735604100', '2425 w washburne ave');
INSERT INTO renter VALUES('evolz@google.com', 'volz', 'enola', ARRAY ['5231 s wabash ave','450 n racine ave'], DATE '6/16/2024', '5305 s kilpatrick ave', '238000', ARRAY ['1571306554049020'], 'false');
INSERT INTO renter_cards VALUES('evolz@google.com', '1571306554049020', '5231 s wabash ave');
INSERT INTO renter VALUES('lmccardell@google.com', 'mccardell', 'leslie', ARRAY ['1735 w rosehill dr','237 w 79th st'], DATE '12/23/2023', '2550 w harrison st', '706000', ARRAY ['1028945231305570'], 'true');
INSERT INTO renter_cards VALUES('lmccardell@google.com', '1028945231305570', '1735 w rosehill dr');
INSERT INTO renter VALUES('gcoria@outlook.com', 'coria', 'gina', ARRAY ['4337 s langley ave'], DATE '10/5/2026', '5715 n rogers ave', '570000', ARRAY ['1876436914405010'], 'true');
INSERT INTO renter_cards VALUES('gcoria@outlook.com', '1876436914405010', '4337 s langley ave');
INSERT INTO renter VALUES('mtaing@outlook.com', 'taing', 'marietta', ARRAY ['5305 s kilpatrick ave'], DATE '9/29/2026', '1028 n hamlin ave', '717000', ARRAY ['1722615820726940'], 'false');
INSERT INTO renter_cards VALUES('mtaing@outlook.com', '1722615820726940', '5305 s kilpatrick ave');
INSERT INTO renter VALUES('kbuchholtz@google.com', 'buchholtz', 'karlyn', ARRAY ['232 n peoria st'], DATE '6/13/2026', '6515 s blackstone ave', '391000', ARRAY ['1409941078780090'], 'false');
INSERT INTO renter_cards VALUES('kbuchholtz@google.com', '1409941078780090', '232 n peoria st');
INSERT INTO renter VALUES('hrenken@google.com', 'renken', 'herma', ARRAY ['8054 s harper ave','4337 s langley ave'], DATE '2/17/2025', '2005 w fullerton ave', '967000', ARRAY ['1756708426577440'], 'false');
INSERT INTO renter_cards VALUES('hrenken@google.com', '1756708426577440', '8054 s harper ave');
INSERT INTO renter VALUES('ggillispie@aol.com', 'gillispie', 'gertrud', ARRAY ['2610 w wilcox st'], DATE '9/26/2026', '11755 s burley ave', '542000', ARRAY ['1045103789816610'], 'true');
INSERT INTO renter_cards VALUES('ggillispie@aol.com', '1045103789816610', '2610 w wilcox st');
INSERT INTO renter VALUES('klavoie@yahoo.com', 'lavoie', 'kelsie', ARRAY ['1016 n lavergne ave'], DATE '11/27/2026', '5719 n rogers ave', '480000', ARRAY ['1324324044246080'], 'true');
INSERT INTO renter_cards VALUES('klavoie@yahoo.com', '1324324044246080', '1016 n lavergne ave');
INSERT INTO renter VALUES('sjenney@outlook.com', 'jenney', 'selena', ARRAY ['12151 s harvard ave','6159 s marshfield ave'], DATE '4/4/2024', '655 w irving park rd #b147', '465000', ARRAY ['1465375208107690'], 'true');
INSERT INTO renter_cards VALUES('sjenney@outlook.com', '1465375208107690', '12151 s harvard ave');
INSERT INTO renter VALUES('tschooley@aol.com', 'schooley', 'teri', ARRAY ['1906 n harding ave'], DATE '9/4/2024', '5317 s maryland ave', '898000', ARRAY ['1390855313586740'], 'true');
INSERT INTO renter_cards VALUES('tschooley@aol.com', '1390855313586740', '1906 n harding ave');
INSERT INTO renter VALUES('lcampana@outlook.com', 'campana', 'lizette', ARRAY ['3824 w grand ave','5715 n rogers ave'], DATE '9/13/2024', '2334 w adams st', '516000', ARRAY ['1890686772415240'], 'false');
INSERT INTO renter_cards VALUES('lcampana@outlook.com', '1890686772415240', '3824 w grand ave');
INSERT INTO renter VALUES('mluby@google.com', 'luby', 'mayra', ARRAY ['1856 w 23rd st','3012 e 78th st'], DATE '9/15/2023', '1906 n harding ave', '555000', ARRAY ['1377220877729180'], 'false');
INSERT INTO renter_cards VALUES('mluby@google.com', '1377220877729180', '1856 w 23rd st');
INSERT INTO renter VALUES('lfinneran@outlook.com', 'finneran', 'luisa', ARRAY ['6740 s wabash ave'], DATE '10/16/2023', '5034 w fulton st', '944000', ARRAY ['1539919796771230'], 'true');
INSERT INTO renter_cards VALUES('lfinneran@outlook.com', '1539919796771230', '6740 s wabash ave');
INSERT INTO renter VALUES('gherrod@google.com', 'herrod', 'genoveva', ARRAY ['450 n racine ave','349 w 115th st'], DATE '11/25/2025', '6159 s marshfield ave', '582000', ARRAY ['1555839585476510'], 'true');
INSERT INTO renter_cards VALUES('gherrod@google.com', '1555839585476510', '450 n racine ave');
INSERT INTO renter VALUES('ttoon@outlook.com', 'toon', 'tandra', ARRAY ['2658 w cortland st'], DATE '2/20/2026', '5231 s wabash ave', '469000', ARRAY ['1358704237427180'], 'true');
INSERT INTO renter_cards VALUES('ttoon@outlook.com', '1358704237427180', '2658 w cortland st');
INSERT INTO renter VALUES('zmangrum@aol.com', 'mangrum', 'zoe', ARRAY ['2343 w dickens ave'], DATE '11/25/2026', '3824 w grand ave', '410000', ARRAY ['1935887309755990'], 'false');
INSERT INTO renter_cards VALUES('zmangrum@aol.com', '1935887309755990', '2343 w dickens ave');
INSERT INTO renter VALUES('msalaam@google.com', 'salaam', 'marquerite', ARRAY ['3807 s lowe ave','2550 w harrison st'], DATE '1/30/2025', '3807 s lowe ave', '321000', ARRAY ['1592881658505950'], 'true');
INSERT INTO renter_cards VALUES('msalaam@google.com', '1592881658505950', '3807 s lowe ave');
INSERT INTO renter VALUES('afonte@aol.com', 'fonte', 'alva', ARRAY ['6159 s marshfield ave'], DATE '12/16/2024', '840 n hamlin ave', '702000', ARRAY ['1967495805878900'], 'true');
INSERT INTO renter_cards VALUES('afonte@aol.com', '1967495805878900', '6159 s marshfield ave');
INSERT INTO renter VALUES('mcage@outlook.com', 'cage', 'maudie', ARRAY ['5255 s racine ave','1856 w 23rd st'], DATE '5/13/2023', '1234 w 96th st', '690000', ARRAY ['1811927608225120'], 'true');
INSERT INTO renter_cards VALUES('mcage@outlook.com', '1811927608225120', '5255 s racine ave');
INSERT INTO renter VALUES('hgreaves@yahoo.com', 'greaves', 'hilde', ARRAY ['1349 w ancona st'], DATE '8/19/2023', '6927 s ashland ave', '634000', ARRAY ['1686185514168510'], 'false');
INSERT INTO renter_cards VALUES('hgreaves@yahoo.com', '1686185514168510', '1349 w ancona st');
INSERT INTO agent VALUES('crippe@swiftreality.com', 'rippe', 'christiana', 'agent', 'swift reality', ARRAY ['2145 s central park ave','2005 w fullerton ave'], '9243189163');
INSERT INTO agent VALUES('bwarr@primerealestateservices.com', 'warr', 'bulah', 'associate broker', 'prime real estate services', ARRAY ['1028 n hamlin ave'], '2250380370');
INSERT INTO agent VALUES('astolte@swiftreality.com', 'stolte', 'azzie', 'realtor', 'swift reality', ARRAY ['4605 s washtenaw ave'], '5410705154');
INSERT INTO agent VALUES('swhistler@historicbrokers.com', 'whistler', 'sharri', 'broker', 'historic brokers', ARRAY ['6927 s ashland ave','5625 s princeton ave'], '8349538208');
INSERT INTO agent VALUES('rholliman@historicpropertymanagement.com', 'holliman', 'rebecka', 'realtor', 'historic property management', ARRAY ['3824 w grand ave','6515 s blackstone ave'], '2789584561');
INSERT INTO agent VALUES('bmuse@elegantnests.com', 'muse', 'bryce', 'agent', 'elegant nests', ARRAY ['5231 s wabash ave','2425 w washburne ave'], '7790000191');
INSERT INTO agent VALUES('mmarcus@historicbrokers.com', 'marcus', 'merideth', 'agent', 'historic brokers', ARRAY ['2550 w harrison st'], '3156427800');
INSERT INTO agent VALUES('nboden@primerealestateservices.com', 'boden', 'nova', 'broker', 'prime real estate services', ARRAY ['2610 w wilcox st','4849 w hubbard st'], '1096938754');
INSERT INTO agent VALUES('gwatchman@vividapartments.com', 'watchman', 'granville', 'salesperson', 'vivid apartments', ARRAY ['5305 s kilpatrick ave','2658 w cortland st'], '2814944015');
INSERT INTO agent VALUES('mdearborn@historicpropertymanagement.com', 'dearborn', 'marquerite', 'agent', 'historic property management', ARRAY ['6515 s knox ave','6740 s wabash ave'], '4333133393');
INSERT INTO agent VALUES('aketcham@historicpropertymanagement.com', 'ketcham', 'arielle', 'agent', 'historic property management', ARRAY ['5317 s maryland ave'], '5547851213');
INSERT INTO agent VALUES('sbuggs@elegantnests.com', 'buggs', 'shona', 'broker', 'elegant nests', ARRAY ['8054 s harper ave','6740 s wabash ave'], '3608216775');
INSERT INTO agent VALUES('aciesla@aboverealtygroup.com', 'ciesla', 'aleta', 'agent', 'above realty group', ARRAY ['3724 w 82nd st'], '1360308915');
INSERT INTO agent VALUES('jwichman@primerealestateservices.com', 'wichman', 'jenni', 'salesperson', 'prime real estate services', ARRAY ['5255 s racine ave','6740 s wabash ave'], '8790392833');
INSERT INTO agent VALUES('cphinney@primerealestateservices.com', 'phinney', 'cuc', 'associate broker', 'prime real estate services', ARRAY ['5231 s wabash ave','1016 n lavergne ave'], '6674464669');
INSERT INTO agent VALUES('dfleig@brightstarrealpropertyservices.com', 'fleig', 'danica', 'salesperson', 'bright star real property services', ARRAY ['1906 n harding ave'], '9568960405');
INSERT INTO agent VALUES('semigh@swiftreality.com', 'emigh', 'shaquana', 'salesperson', 'swift reality', ARRAY ['2343 w dickens ave','4456 n hamlin ave'], '2202166267');
INSERT INTO agent VALUES('bmaster@swiftreality.com', 'master', 'brinda', 'salesperson', 'swift reality', ARRAY ['2145 s central park ave','5317 s maryland ave'], '5539639095');
INSERT INTO agent VALUES('jhinojos@aboverealtygroup.com', 'hinojos', 'janell', 'broker', 'above realty group', ARRAY ['4817 w quincy st #1','232 n peoria st'], '6494240381');
INSERT INTO agent VALUES('kcelestin@elegantnests.com', 'celestin', 'karri', 'realtor', 'elegant nests', ARRAY ['5317 s maryland ave'], '1298937447');
INSERT INTO agent VALUES('ebouley@swiftreality.com', 'bouley', 'enid', 'realtor', 'swift reality', ARRAY ['4605 s washtenaw ave','1856 w 23rd st'], '6173143689');
INSERT INTO agent VALUES('dklenke@historicbrokers.com', 'klenke', 'desire', 'associate broker', 'historic brokers', ARRAY ['6515 s knox ave','5305 s kilpatrick ave'], '4962891864');
INSERT INTO agent VALUES('bchamberlain@historicpropertymanagement.com', 'chamberlain', 'brian', 'realtor', 'historic property management', ARRAY ['11755 s burley ave','6927 s ashland ave'], '1019084195');
INSERT INTO agent VALUES('kfarabaugh@historicbrokers.com', 'farabaugh', 'kristeen', 'salesperson', 'historic brokers', ARRAY ['5317 s maryland ave','349 w 115th st'], '8207798383');
INSERT INTO agent VALUES('gvanatta@seemlessnooks.com', 'vanatta', 'gilda', 'associate broker', 'seemless nooks', ARRAY ['11755 s burley ave'], '9215448291');
INSERT INTO agent VALUES('pbumbrey@historicbrokers.com', 'bumbrey', 'princess', 'realtor', 'historic brokers', ARRAY ['5255 s racine ave','1906 n harding ave'], '5282863597');
INSERT INTO agent VALUES('etownson@brightstarrealpropertyservices.com', 'townson', 'earlie', 'agent', 'bright star real property services', ARRAY ['4605 s washtenaw ave'], '2010997217');
INSERT INTO agent VALUES('flaliberte@vividapartments.com', 'laliberte', 'fumiko', 'salesperson', 'vivid apartments', ARRAY ['2343 w dickens ave','5317 s maryland ave'], '6623110051');
INSERT INTO agent VALUES('schason@elegantnests.com', 'chason', 'sherie', 'salesperson', 'elegant nests', ARRAY ['1016 n lavergne ave'], '1235135906');
INSERT INTO agent VALUES('gmaravilla@historicpropertymanagement.com', 'maravilla', 'gricelda', 'realtor', 'historic property management', ARRAY ['6515 s knox ave','4605 s washtenaw ave'], '8104585065');
INSERT INTO agent VALUES('sknoll@distinguishedrealty.com', 'knoll', 'serafina', 'salesperson', 'distinguished realty', ARRAY ['5719 n rogers ave','2343 w dickens ave'], '6117926173');
INSERT INTO agent VALUES('syounkin@brightstarrealpropertyservices.com', 'younkin', 'sachiko', 'realtor', 'bright star real property services', ARRAY ['1906 n harding ave'], '3179126953');
INSERT INTO agent VALUES('jnoles@aboverealtygroup.com', 'noles', 'jena', 'agent', 'above realty group', ARRAY ['8054 s harper ave','1906 n harding ave'], '2332242371');
INSERT INTO agent VALUES('swiser@historicpropertymanagement.com', 'wiser', 'shelby', 'realtor', 'historic property management', ARRAY ['6927 s ashland ave','6927 s ashland ave'], '2287740702');
INSERT INTO agent VALUES('mspies@brightstarrealpropertyservices.com', 'spies', 'margareta', 'agent', 'bright star real property services', ARRAY ['2343 w dickens ave','5719 n rogers ave'], '2944294692');
INSERT INTO agent VALUES('dfurey@primerealestateservices.com', 'furey', 'dinorah', 'broker', 'prime real estate services', ARRAY ['6740 s wabash ave','11755 s burley ave'], '7260878020');
INSERT INTO agent VALUES('klechuga@distinguishedrealty.com', 'lechuga', 'kiyoko', 'broker', 'distinguished realty', ARRAY ['2343 w dickens ave'], '7203394025');
INSERT INTO agent VALUES('dkreger@primerealestateservices.com', 'kreger', 'danny', 'broker', 'prime real estate services', ARRAY ['2425 w washburne ave','2610 w wilcox st'], '9238592715');
INSERT INTO agent VALUES('nfenwick@historicbrokers.com', 'fenwick', 'nolan', 'broker', 'historic brokers', ARRAY ['4817 w quincy st #1','2610 w wilcox st'], '5096210106');
INSERT INTO agent VALUES('lunrein@seemlessnooks.com', 'unrein', 'lesli', 'realtor', 'seemless nooks', ARRAY ['4605 s washtenaw ave'], '4888423367');
INSERT INTO agent VALUES('dbartle@distinguishedrealty.com', 'bartle', 'donya', 'agent', 'distinguished realty', ARRAY ['4605 s washtenaw ave','5719 n rogers ave'], '5167692387');
INSERT INTO agent VALUES('pflanery@swiftreality.com', 'flanery', 'palma', 'broker', 'swift reality', ARRAY ['450 n racine ave','4817 w quincy st #1'], '5633489099');
INSERT INTO agent VALUES('kclothier@distinguishedrealty.com', 'clothier', 'kenny', 'broker', 'distinguished realty', ARRAY ['5231 s wabash ave'], '2577293135');
INSERT INTO agent VALUES('klossing@historicpropertymanagement.com', 'lossing', 'kristen', 'realtor', 'historic property management', ARRAY ['5719 n rogers ave','1906 n harding ave'], '8393802888');
INSERT INTO agent VALUES('kfelix@elegantnests.com', 'felix', 'karoline', 'agent', 'elegant nests', ARRAY ['4817 w quincy st #1'], '7491283686');
INSERT INTO agent VALUES('avoll@swiftreality.com', 'voll', 'alan', 'salesperson', 'swift reality', ARRAY ['450 n racine ave','450 n racine ave'], '3994603223');
INSERT INTO agent VALUES('gwoolfolk@brightstarrealpropertyservices.com', 'woolfolk', 'glenda', 'salesperson', 'bright star real property services', ARRAY ['1906 n harding ave','5231 s wabash ave'], '4083336200');
INSERT INTO agent VALUES('ahosack@aboverealtygroup.com', 'hosack', 'alyson', 'associate broker', 'above realty group', ARRAY ['450 n racine ave','5231 s wabash ave'], '9481650982');
INSERT INTO agent VALUES('fsines@historicpropertymanagement.com', 'sines', 'francis', 'salesperson', 'historic property management', ARRAY ['5231 s wabash ave'], '4564046905');
INSERT INTO agent VALUES('rbagnall@brightstarrealpropertyservices.com', 'bagnall', 'riley', 'associate broker', 'bright star real property services', ARRAY ['450 n racine ave','450 n racine ave'], '9650526779');

