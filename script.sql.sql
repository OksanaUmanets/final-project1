--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-15 14:24:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 17674)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.category OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17673)
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO postgres;

--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 214
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- TOC entry 217 (class 1259 OID 17681)
-- Name: image; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.image (
    id integer NOT NULL,
    file_name character varying(255),
    product_id integer NOT NULL
);


ALTER TABLE public.image OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17680)
-- Name: image_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.image_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.image_id_seq OWNER TO postgres;

--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 216
-- Name: image_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.image_id_seq OWNED BY public.image.id;


--
-- TOC entry 219 (class 1259 OID 17688)
-- Name: order_person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_person (
    id integer NOT NULL,
    date_time timestamp(6) without time zone,
    opnumber character varying(255),
    opsumm real,
    status smallint,
    person_id integer NOT NULL
);


ALTER TABLE public.order_person OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17687)
-- Name: order_person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_person_id_seq OWNER TO postgres;

--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 218
-- Name: order_person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_person_id_seq OWNED BY public.order_person.id;


--
-- TOC entry 221 (class 1259 OID 17695)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer NOT NULL,
    login character varying(100),
    password character varying(255),
    role character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17694)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq OWNER TO postgres;

--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 220
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_id_seq OWNED BY public.person.id;


--
-- TOC entry 223 (class 1259 OID 17704)
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    date_time timestamp(6) without time zone,
    description text NOT NULL,
    price real NOT NULL,
    seller character varying(255) NOT NULL,
    title text NOT NULL,
    category_id integer NOT NULL,
    warehouse_id integer NOT NULL,
    CONSTRAINT product_price_check CHECK ((price >= (1)::double precision))
);


ALTER TABLE public.product OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17714)
-- Name: product_cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_cart (
    id integer NOT NULL,
    person_id integer,
    product_id integer
);


ALTER TABLE public.product_cart OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 17713)
-- Name: product_cart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_cart_id_seq OWNER TO postgres;

--
-- TOC entry 3410 (class 0 OID 0)
-- Dependencies: 224
-- Name: product_cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_cart_id_seq OWNED BY public.product_cart.id;


--
-- TOC entry 222 (class 1259 OID 17703)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- TOC entry 3411 (class 0 OID 0)
-- Dependencies: 222
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 227 (class 1259 OID 17721)
-- Name: warehouse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.warehouse (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.warehouse OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 17720)
-- Name: warehouse_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.warehouse_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.warehouse_id_seq OWNER TO postgres;

--
-- TOC entry 3412 (class 0 OID 0)
-- Dependencies: 226
-- Name: warehouse_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.warehouse_id_seq OWNED BY public.warehouse.id;


--
-- TOC entry 229 (class 1259 OID 17728)
-- Name: оrder_product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."оrder_product" (
    id integer NOT NULL,
    count integer NOT NULL,
    date_time timestamp(6) without time zone,
    price real NOT NULL,
    order_person_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public."оrder_product" OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 17727)
-- Name: оrder_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."оrder_product_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."оrder_product_id_seq" OWNER TO postgres;

--
-- TOC entry 3413 (class 0 OID 0)
-- Dependencies: 228
-- Name: оrder_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."оrder_product_id_seq" OWNED BY public."оrder_product".id;


--
-- TOC entry 3208 (class 2604 OID 17677)
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- TOC entry 3209 (class 2604 OID 17684)
-- Name: image id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image ALTER COLUMN id SET DEFAULT nextval('public.image_id_seq'::regclass);


--
-- TOC entry 3210 (class 2604 OID 17691)
-- Name: order_person id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_person ALTER COLUMN id SET DEFAULT nextval('public.order_person_id_seq'::regclass);


--
-- TOC entry 3211 (class 2604 OID 17698)
-- Name: person id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person ALTER COLUMN id SET DEFAULT nextval('public.person_id_seq'::regclass);


--
-- TOC entry 3212 (class 2604 OID 17707)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 3213 (class 2604 OID 17717)
-- Name: product_cart id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart ALTER COLUMN id SET DEFAULT nextval('public.product_cart_id_seq'::regclass);


--
-- TOC entry 3214 (class 2604 OID 17724)
-- Name: warehouse id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouse ALTER COLUMN id SET DEFAULT nextval('public.warehouse_id_seq'::regclass);


--
-- TOC entry 3215 (class 2604 OID 17731)
-- Name: оrder_product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."оrder_product" ALTER COLUMN id SET DEFAULT nextval('public."оrder_product_id_seq"'::regclass);


--
-- TOC entry 3386 (class 0 OID 17674)
-- Dependencies: 215
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.category (id, name) VALUES (1, 'Детективы');
INSERT INTO public.category (id, name) VALUES (2, 'Фэнтези');
INSERT INTO public.category (id, name) VALUES (3, 'Романы');


--
-- TOC entry 3388 (class 0 OID 17681)
-- Dependencies: 217
-- Data for Name: image; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.image (id, file_name, product_id) VALUES (1, 'b4e59068-568c-492c-b931-3ee0b2111c6c.1.webp', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (2, '30eb762f-bb49-4aff-8585-253b4cb584a3.', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (3, '59f13b5a-d237-4798-b551-18d183e6a941.', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (4, 'e562cdea-8e4d-47c4-b03f-ad8238273f20.', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (5, 'ed005a71-9619-44a7-aef2-923c3c6c456e.', 1);
INSERT INTO public.image (id, file_name, product_id) VALUES (6, 'c371f73a-f025-45ba-ba64-e92b4333f93b.2.webp', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (7, 'efd93cbd-62c3-424c-9a00-1130bd30b7cb.', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (8, '2730beaf-784c-4d2b-9877-e9792141a91f.', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (9, '302d641a-9cf4-47f7-8886-864f18fd4475.', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (10, 'be6db1c1-f232-408b-9e7d-8d693b77bf63.', 2);
INSERT INTO public.image (id, file_name, product_id) VALUES (11, 'ac8cbf3b-4d40-4e62-8af3-5f633d14f8a2.3.webp', 3);
INSERT INTO public.image (id, file_name, product_id) VALUES (12, 'b93b771d-e5ce-4f8d-b14f-4776ab9c2986.', 3);
INSERT INTO public.image (id, file_name, product_id) VALUES (13, '96f02d0e-4d1a-4fd6-b206-11246055b104.', 3);
INSERT INTO public.image (id, file_name, product_id) VALUES (14, '53c07a13-e61d-43e4-89d9-e1514afb5d8e.', 3);
INSERT INTO public.image (id, file_name, product_id) VALUES (15, '00cfb831-6726-48e5-990b-afe171b1cc75.', 3);
INSERT INTO public.image (id, file_name, product_id) VALUES (16, '158127ff-00cc-459f-9e90-fdfee17b8bf9.4.webp', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (17, '0f4de43a-3327-499f-a846-5d657c9fceb3.', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (18, '9228bcf3-c51e-41d2-87c8-a817fd5f6447.', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (19, 'bb7bf426-ddbc-4f3c-b357-c2c50ecef22c.', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (20, 'b14463ca-79b2-4e97-b2e4-84fb34c2dc06.', 4);
INSERT INTO public.image (id, file_name, product_id) VALUES (21, 'ba6ae6e9-b1e6-4f85-8383-931153708180.5.webp', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (22, '969e705f-c13b-4582-a53a-efcd87ac517c.', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (23, '6e54075f-16cf-4263-b2bd-2d2de5ba5981.', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (24, '4b505f45-fa15-4d2e-95db-d7a1ae25d456.', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (25, '7042ec27-8223-43b2-9f32-7a38799d63f9.', 5);
INSERT INTO public.image (id, file_name, product_id) VALUES (26, '6d7b702d-a2cd-44fa-ba02-1c7bae50438f.6.webp', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (27, '7a4c7f1d-4a30-429d-95b2-46f5e0d54d3c.', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (28, '1c74e639-e6fb-4c98-b3e3-a90161f9bb85.', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (29, '7bf199e3-c63a-4bce-a9ac-ebd6249b72df.', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (30, '20319512-bb96-4a81-a295-9a0e6195d214.', 6);
INSERT INTO public.image (id, file_name, product_id) VALUES (31, 'c3951ea7-0c18-4ecf-9f8a-2a294dae48d6.7.webp', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (32, '55469254-8a38-4db7-847d-62655613d458.', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (33, '05d3b32a-d7e1-4bf5-837e-ae5f96fce16e.', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (34, '68e094a9-c052-4e6d-a262-64d7cf1a81d1.', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (35, '3ef7b3a7-c68f-4f0e-bf55-49d959eaa2b1.', 7);
INSERT INTO public.image (id, file_name, product_id) VALUES (36, '95346c5c-1c1a-449f-9c0c-1e76d7039dd5.8.webp', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (37, '844b54ef-e7ad-4e55-8590-b2598132c8d3.', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (38, '2f9dcd6b-ea1f-4e0e-a014-a6ac30c87100.', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (39, 'c6ab229e-85da-43c0-b33d-9dadb55b127d.', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (40, '47856ea6-1e3c-46bf-afa1-60a2a53452db.', 8);
INSERT INTO public.image (id, file_name, product_id) VALUES (41, '12db6627-4441-4fd5-8dda-2c7b1e618e5b.9.webp', 9);
INSERT INTO public.image (id, file_name, product_id) VALUES (42, '31323396-03a2-4ca8-9422-9c68c0a9c438.', 9);
INSERT INTO public.image (id, file_name, product_id) VALUES (43, '8ebbbcb1-6fc2-44f0-b836-08cefc3018d7.', 9);
INSERT INTO public.image (id, file_name, product_id) VALUES (44, 'b55e13a1-6b8b-49e1-b883-6e800dfbe18b.', 9);
INSERT INTO public.image (id, file_name, product_id) VALUES (45, 'fcab4c59-d574-42e4-81b8-73c0f1397f5e.', 9);


--
-- TOC entry 3390 (class 0 OID 17688)
-- Dependencies: 219
-- Data for Name: order_person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.order_person (id, date_time, opnumber, opsumm, status, person_id) VALUES (1, '2023-05-14 22:40:00', '5c49f939-a730-47f2-b72d-514f9d105bd4', 2230, 0, 1);
INSERT INTO public.order_person (id, date_time, opnumber, opsumm, status, person_id) VALUES (3, '2023-05-15 14:10:15.332588', 'dfa2e8dd-60a4-4ee2-9239-945c5106f8b9', 1950, 1, 4);
INSERT INTO public.order_person (id, date_time, opnumber, opsumm, status, person_id) VALUES (2, '2023-05-15 14:09:00', 'ee00b26c-b32b-408f-9a6c-0933fd14ae26', 430, 2, 3);


--
-- TOC entry 3392 (class 0 OID 17695)
-- Dependencies: 221
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.person (id, login, password, role) VALUES (1, 'user1', '$2a$10$D3f2OXjnXKMkgggz6NRAj.7/.0Jkkz5w3Fzm67c8zcTprvyqtirNO', 'ROLE_USER');
INSERT INTO public.person (id, login, password, role) VALUES (2, 'admin', '$2a$10$Ln.HvSitpyohOwXETLp8Due96bfqwrnHt4WzjqG15F8cFwXOsIavm', 'ROLE_ADMIN');
INSERT INTO public.person (id, login, password, role) VALUES (3, 'user2', '$2a$10$TzhNpYVKPEmpncCbCJl5J.uriuVJHKXNkUH82IkTGTNERFt1AKkKe', 'ROLE_USER');
INSERT INTO public.person (id, login, password, role) VALUES (4, 'user3', '$2a$10$dDZQTGjj2e5/HfF2sFntsOMPKtepLkqna3By.gLl4lWnFMHSM4EpC', 'ROLE_USER');


--
-- TOC entry 3394 (class 0 OID 17704)
-- Dependencies: 223
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (1, '2023-05-14 22:01:21.887651', 'Отбывающий пожизненное заключение Бенджамин Фишер, серийный убийца, на счету которого кровь десятков невинных жертв, обещает следователю Дэниелу Эллису показать места захоронения убитых женщин, но с одним условием: его дочь Рени должна поехать с ними. Рени, раньше работавшая психологом в ФБР, ни разу не встречалась с отцом за все годы его заключения. У молодой женщины ужасная психологическая травма: ведь именно она ребенком заманивала молодых женщин в смертельную ловушку, наивно полагая, что папа "так играет".
Но вот пришло время перевернуть страницу жизни для всех: для Рени, для семей погибших, и для Дэниела, который тоже не в силах расстаться с прошлым, поскольку уверен, что его мать стала одной из жертв Фишера.', 550, 'ИП Иванов А.А.', 'Энн Фрейзер: Найди меня', 1, 2);
INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (2, '2023-05-14 22:02:55.261231', 'Она собирается прославиться, и ничего не сможет ей помешать.
Флоренс Дэрроу твердо верит в то, что станет известной писательницей.
После увольнения из издательства за роман с женатым начальником и попытку шантажировать его эти романом случай дарит ей работу персонального ассистента всемирно известной молодой писательницы, живущей отшельницей и скрывающейся под псевдонимом Мод Диксон.
В поездке по Марокко Флоренс не справляется с управлением и их автомобиль падает с моста в реку. Она оказывается в больнице с потерей памяти, а ее нанимательница бесследно исчезает. Все убеждают Флоренс, что в машине она была одна.
Спустя несколько дней знаменитая писательница Мод Диксон покидает больницу… Новая хозяйка известного имени не подозревает, что к нему прилагаются зловещий секрет и смертельная опасность.', 430, 'ИП Иванов А.А.', 'Александра Эндрюс: Кто такая Мод Диксон?', 1, 2);
INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (3, '2023-05-14 22:04:13.242046', 'Кинопремия Magritte Awards в 8 номинациях за франко-бельгийскую экранизацию этого романа. Ожидается голливудский римейк с Энн Хэтэуэй и Джессикой Честейн.

Что отделяет дружбу от ненависти? Иногда лишь садовая изгородь…

Тихий пригород, прекрасный таунхаус с великолепными садами. Здесь живут бок о бок две супружеские пары с маленькими сыновьями-одногодками. Крепко дружат и души не чают друг в друге.

Пока трагедия не уничтожает гармонию.

Погиб малыш Максим. Обезумевшая от горя Тифэн уверена, что в смерти сына виновата ее подруга-соседка, Летиция. Та тоже не может прийти в себя от шока, но считает виновной в случившемся саму мать. Отношения между соседями быстро портятся. И чем дальше заходит их мучительное противостояние, тем больше у Летиции оснований опасаться мести потерявшей сына женщины. Кажется, Тифэн пойдет на все, чтобы бывшая подруга испытала то же бездонное страдание, что и она. А вдруг сумасшедшая соседка захочет сотворить что-то страшное с...', 630, 'ИП Иванов А.А.', 'Барбара Абель: Инстинкт матери', 1, 1);
INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (4, '2023-05-14 22:05:47.855654', 'После фиаско в колдовстве гигантских размеров Гарри Поттер сбегает от Дурслеев из Литтл Уинджинга на ночном автобусе в предчувствии серьезной беды. Но министерство магии занято ловлей более крупной рыбы – Сириуса Блэка, преданного последователя Лорда Вольдеморта и печально известного заключенного, сбежавшего из тюрьмы Азкабан. Говорили, что он охотится за Гарри Поттером и министр магии отправил дементоров Азкабана проверить школьные угодья. В третий год обучения Гарри в «Хогварце» его преследуют темные слухи и смертельные знамения, в то время как он узнает новую правду о своем прошлом и сталкивается лицом к лицу с одним из самых преданных слуг Черного Лорда…

Долгожданное иллюстрированное издание третьей книги классической серии Дж.К. Роулинг взрывается словно по волшебству колдовскими моментами, созданными карандашами и красками Джимом Кеем, лауреатом премии Кейт Гринуэй.', 1950, 'ИП Иванов А.А.', 'Джоан Роулинг: Гарри Поттер и узник Азкабана', 2, 1);
INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (5, '2023-05-14 22:06:55.458217', '"Иллюстрации Джима Кея поразили меня до глубины души. Мне нравится его взгляд на мир Гарри Поттера, и для меня большая честь, что Джим согласился украсить этот мир своим талантом".
Дж.К. РОУЛИНГ
Книга, покорившая мир, эталон литературы для читателей всех возрастов, синоним успеха. Книга, сделавшая Джоан Роулинг самым читаемым писателем современности. Книга, ставшая культовой уже для нескольких поколений. "Гарри Поттер и Философский камень" - история начинается.
Подробнее: https://www.labirint.ru/books/529471/', 1800, 'ИП Иванов А.А.', 'Джоан Роулинг: Гарри Поттер и Философский камень', 2, 1);
INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (6, '2023-05-14 22:08:09.619981', 'Когда на Бирючинной улице внезапно появляется маленький домовый эльф Добби и начинает требовать от Гарри, чтобы тот не возвращался в "Хогварц", Гарри почти уверен, что все это - происки его заклятого врага Драко Малфоя. Но после, уже в школе, он видит, что зловещие надписи на стене темного коридора словно бы вторят пророчествам Добби: вот-вот здесь произойдет страшное…

На второй год обучения в "Хогварце" Гарри вместе с Роном и Гермионой пытается разыскать мифическую Тайную комнату и ее смертоносный секрет, а попутно встречается с магическими животными, находит заколдованный дневник и узнает некоторые факты из прошлого Огрида.

Эта книга, идеально сочетающая в себе труд гениального автора и гениального иллюстратора, доставит наслаждение как тем, кто впервые открывает для себя классическую серию произведений Дж.К. Роулинг, так и ее верным поклонникам.', 1750, 'ИП Иванов А.А.', 'Джоан Роулинг: Гарри Поттер и Тайная комната', 2, 3);
INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (8, '2023-05-14 22:12:29.477234', 'Слух о том, что Элена владеет даром Искателя душ, будоражит Ситию: шутка ли, ведь молодая чародейка способна обрести небывалую силу, а ее давние связи с Иксией, враждебным северным государством, поневоле внушают опасения.
Пока Совет Ситии спорит о дальнейшей судьбе Элены, сама она получает тревожную весть: могущественный колдун Ферде готовит заговор, мечтая завладеть и Севером, и Югом. А значит, неспокойная судьба опять зовет Элену в дорогу. Удастся ли ей выстоять или придется пожертвовать собой ради счастья близких?', 430, 'ИП Иванов А.А.', 'Мария Снайдер: Испытание огнем', 1, 3);
INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (7, NULL, 'Целые дни шестнадцатилетняя Зола чинит на рынке чужие портскрины и андроиды. Она лучший механик Нового Пекина, и ее слава достигла королевского дворца. Но немногие знают, что они киборг. Давным-давно, после несчастного случая, маленькую Золу спасли, вмонтировав в ее тело металлические части тела и электронную нервную систему. Теперь, едва узнав об этом, люди сторонятся ее, а мачеха с двумя дочками без конца осыпает упреками.
Но однажды на рынок приходит прекрасный принц Кай, которому нужно починить старенького андроида, и у Золы начинается новая жизнь.', 1200, 'ИП Иванов А.А.', 'Марисса Мейер: Лунные хроники. Золушка', 3, 1);
INSERT INTO public.product (id, date_time, description, price, seller, title, category_id, warehouse_id) VALUES (9, NULL, 'После долгих лет, проведенных в холодной и неласковой Иксии, Элена держит путь на юг: там, в жарких Иллиэйских джунглях, ее ждет встреча с давно потерянной семьей, а затем молодой чародейке предстоит изучать свой магический дар под руководством опытных наставников. Однако радужные перспективы неожиданно оборачиваются кошмаром, когда Элену обвиняют в шпионаже в пользу Севера. Теперь ей придется не только отбиваться от врагов, но и убедить в своей честности друзей.', 420, 'ИП Иванов А.А.', 'Мария Снайдер: Испытание магией', 3, 3);


--
-- TOC entry 3396 (class 0 OID 17714)
-- Dependencies: 225
-- Data for Name: product_cart; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3398 (class 0 OID 17721)
-- Dependencies: 227
-- Data for Name: warehouse; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.warehouse (id, name) VALUES (1, 'Основной');
INSERT INTO public.warehouse (id, name) VALUES (2, 'Магазин Пионер');
INSERT INTO public.warehouse (id, name) VALUES (3, 'Магазин Солнышко');


--
-- TOC entry 3400 (class 0 OID 17728)
-- Dependencies: 229
-- Data for Name: оrder_product; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."оrder_product" (id, count, date_time, price, order_person_id, product_id) VALUES (1, 1, '2023-05-14 22:40:32.719244', 430, 1, 2);
INSERT INTO public."оrder_product" (id, count, date_time, price, order_person_id, product_id) VALUES (2, 1, '2023-05-14 22:40:32.731213', 1800, 1, 5);
INSERT INTO public."оrder_product" (id, count, date_time, price, order_person_id, product_id) VALUES (3, 1, '2023-05-15 14:09:36.749951', 430, 2, 2);
INSERT INTO public."оrder_product" (id, count, date_time, price, order_person_id, product_id) VALUES (4, 1, '2023-05-15 14:10:15.337575', 1950, 3, 4);


--
-- TOC entry 3414 (class 0 OID 0)
-- Dependencies: 214
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 1, true);


--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 216
-- Name: image_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.image_id_seq', 45, true);


--
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 218
-- Name: order_person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_person_id_seq', 3, true);


--
-- TOC entry 3417 (class 0 OID 0)
-- Dependencies: 220
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 4, true);


--
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 224
-- Name: product_cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_cart_id_seq', 5, true);


--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 222
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 9, true);


--
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 226
-- Name: warehouse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.warehouse_id_seq', 1, false);


--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 228
-- Name: оrder_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."оrder_product_id_seq"', 4, true);


--
-- TOC entry 3218 (class 2606 OID 17679)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 3220 (class 2606 OID 17686)
-- Name: image image_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT image_pkey PRIMARY KEY (id);


--
-- TOC entry 3222 (class 2606 OID 17693)
-- Name: order_person order_person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_person
    ADD CONSTRAINT order_person_pkey PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 17702)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 3230 (class 2606 OID 17719)
-- Name: product_cart product_cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT product_cart_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 17712)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3228 (class 2606 OID 17735)
-- Name: product uk_qka6vxqdy1dprtqnx9trdd47c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT uk_qka6vxqdy1dprtqnx9trdd47c UNIQUE (title);


--
-- TOC entry 3232 (class 2606 OID 17726)
-- Name: warehouse warehouse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.warehouse
    ADD CONSTRAINT warehouse_pkey PRIMARY KEY (id);


--
-- TOC entry 3234 (class 2606 OID 17733)
-- Name: оrder_product оrder_product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."оrder_product"
    ADD CONSTRAINT "оrder_product_pkey" PRIMARY KEY (id);


--
-- TOC entry 3237 (class 2606 OID 17746)
-- Name: product fk1mtsbur82frn64de7balymq9s; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fk1mtsbur82frn64de7balymq9s FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- TOC entry 3241 (class 2606 OID 17766)
-- Name: оrder_product fk1myga43hex2dg2205f0gb03d6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."оrder_product"
    ADD CONSTRAINT fk1myga43hex2dg2205f0gb03d6 FOREIGN KEY (order_person_id) REFERENCES public.order_person(id);


--
-- TOC entry 3236 (class 2606 OID 17741)
-- Name: order_person fkep8v4imh31iqqdxnt5isiuhq2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_person
    ADD CONSTRAINT fkep8v4imh31iqqdxnt5isiuhq2 FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- TOC entry 3235 (class 2606 OID 17736)
-- Name: image fkgpextbyee3uk9u6o2381m7ft1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.image
    ADD CONSTRAINT fkgpextbyee3uk9u6o2381m7ft1 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3239 (class 2606 OID 17761)
-- Name: product_cart fkhpnrxdy3jhujameyod08ilvvw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fkhpnrxdy3jhujameyod08ilvvw FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3238 (class 2606 OID 17751)
-- Name: product fkk6edvfdkq61mjhltx856ncs3x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkk6edvfdkq61mjhltx856ncs3x FOREIGN KEY (warehouse_id) REFERENCES public.warehouse(id);


--
-- TOC entry 3242 (class 2606 OID 17771)
-- Name: оrder_product fkmob21icn6xkupe0baqw5iw892; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."оrder_product"
    ADD CONSTRAINT fkmob21icn6xkupe0baqw5iw892 FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- TOC entry 3240 (class 2606 OID 17756)
-- Name: product_cart fksgnkc1ko2i1o9yr2p63ysq3rn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_cart
    ADD CONSTRAINT fksgnkc1ko2i1o9yr2p63ysq3rn FOREIGN KEY (person_id) REFERENCES public.person(id);


-- Completed on 2023-05-15 14:24:57

--
-- PostgreSQL database dump complete
--

