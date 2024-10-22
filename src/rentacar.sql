PGDMP                      |            rentacar    16.3    16.3 (    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    25345    rentacar    DATABASE     �   CREATE DATABASE rentacar WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1254';
    DROP DATABASE rentacar;
                postgres    false            �            1259    25372    brand    TABLE     [   CREATE TABLE public.brand (
    brand_id integer NOT NULL,
    brand_name text NOT NULL
);
    DROP TABLE public.brand;
       public         heap    postgres    false            �            1259    25371    brand_brand_id_seq    SEQUENCE     �   CREATE SEQUENCE public.brand_brand_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.brand_brand_id_seq;
       public          postgres    false    217            �           0    0    brand_brand_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.brand_brand_id_seq OWNED BY public.brand.brand_id;
          public          postgres    false    216            �            1259    25404    car    TABLE     �   CREATE TABLE public.car (
    car_id integer NOT NULL,
    car_model_id integer NOT NULL,
    car_color text NOT NULL,
    car_km integer NOT NULL,
    car_plate text NOT NULL,
    is_available boolean DEFAULT true
);
    DROP TABLE public.car;
       public         heap    postgres    false            �            1259    25403    car_car_id_seq    SEQUENCE     �   CREATE SEQUENCE public.car_car_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.car_car_id_seq;
       public          postgres    false    221            �           0    0    car_car_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.car_car_id_seq OWNED BY public.car.car_id;
          public          postgres    false    220            �            1259    25390    model    TABLE     �   CREATE TABLE public.model (
    model_id integer NOT NULL,
    model_brand_id integer NOT NULL,
    model_name text NOT NULL,
    model_type text NOT NULL,
    model_year text NOT NULL,
    model_fuel text NOT NULL,
    model_gear text NOT NULL
);
    DROP TABLE public.model;
       public         heap    postgres    false            �            1259    25389    model_model_id_seq    SEQUENCE     �   CREATE SEQUENCE public.model_model_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.model_model_id_seq;
       public          postgres    false    219            �           0    0    model_model_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.model_model_id_seq OWNED BY public.model.model_id;
          public          postgres    false    218            �            1259    25433    reservation    TABLE     �  CREATE TABLE public.reservation (
    id integer NOT NULL,
    car_id integer NOT NULL,
    customer_name text NOT NULL,
    customer_id_no text NOT NULL,
    customer_phone text NOT NULL,
    customer_email text,
    start_date date NOT NULL,
    end_date date NOT NULL,
    days integer NOT NULL,
    daily_rate double precision NOT NULL,
    price double precision NOT NULL,
    note text,
    status text NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    25432    reservation_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.reservation_id_seq;
       public          postgres    false    224            �           0    0    reservation_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;
          public          postgres    false    223            �            1259    25346    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_pass text NOT NULL,
    user_role text
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    25431    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            .           2604    25375    brand brand_id    DEFAULT     p   ALTER TABLE ONLY public.brand ALTER COLUMN brand_id SET DEFAULT nextval('public.brand_brand_id_seq'::regclass);
 =   ALTER TABLE public.brand ALTER COLUMN brand_id DROP DEFAULT;
       public          postgres    false    217    216    217            0           2604    25407 
   car car_id    DEFAULT     h   ALTER TABLE ONLY public.car ALTER COLUMN car_id SET DEFAULT nextval('public.car_car_id_seq'::regclass);
 9   ALTER TABLE public.car ALTER COLUMN car_id DROP DEFAULT;
       public          postgres    false    220    221    221            /           2604    25393    model model_id    DEFAULT     p   ALTER TABLE ONLY public.model ALTER COLUMN model_id SET DEFAULT nextval('public.model_model_id_seq'::regclass);
 =   ALTER TABLE public.model ALTER COLUMN model_id DROP DEFAULT;
       public          postgres    false    218    219    219            2           2604    25436    reservation id    DEFAULT     p   ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);
 =   ALTER TABLE public.reservation ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            �          0    25372    brand 
   TABLE DATA           5   COPY public.brand (brand_id, brand_name) FROM stdin;
    public          postgres    false    217   -       �          0    25404    car 
   TABLE DATA           _   COPY public.car (car_id, car_model_id, car_color, car_km, car_plate, is_available) FROM stdin;
    public          postgres    false    221   q-       �          0    25390    model 
   TABLE DATA           u   COPY public.model (model_id, model_brand_id, model_name, model_type, model_year, model_fuel, model_gear) FROM stdin;
    public          postgres    false    219   �-       �          0    25433    reservation 
   TABLE DATA           �   COPY public.reservation (id, car_id, customer_name, customer_id_no, customer_phone, customer_email, start_date, end_date, days, daily_rate, price, note, status) FROM stdin;
    public          postgres    false    224   �.       �          0    25346    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    215   T0       �           0    0    brand_brand_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.brand_brand_id_seq', 20, true);
          public          postgres    false    216            �           0    0    car_car_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.car_car_id_seq', 47, true);
          public          postgres    false    220            �           0    0    model_model_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.model_model_id_seq', 37, true);
          public          postgres    false    218            �           0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 12, true);
          public          postgres    false    223            �           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 5, true);
          public          postgres    false    222            6           2606    25379    brand brand_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (brand_id);
 :   ALTER TABLE ONLY public.brand DROP CONSTRAINT brand_pkey;
       public            postgres    false    217            :           2606    25411    car car_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_pkey PRIMARY KEY (car_id);
 6   ALTER TABLE ONLY public.car DROP CONSTRAINT car_pkey;
       public            postgres    false    221            8           2606    25397    model model_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_pkey PRIMARY KEY (model_id);
 :   ALTER TABLE ONLY public.model DROP CONSTRAINT model_pkey;
       public            postgres    false    219            <           2606    25440    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    224            4           2606    25352    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    215            >           2606    25412    car car_car_model_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.car
    ADD CONSTRAINT car_car_model_id_fkey FOREIGN KEY (car_model_id) REFERENCES public.model(model_id);
 C   ALTER TABLE ONLY public.car DROP CONSTRAINT car_car_model_id_fkey;
       public          postgres    false    4664    219    221            =           2606    25398    model model_model_brand_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.model
    ADD CONSTRAINT model_model_brand_id_fkey FOREIGN KEY (model_brand_id) REFERENCES public.brand(brand_id);
 I   ALTER TABLE ONLY public.model DROP CONSTRAINT model_model_brand_id_fkey;
       public          postgres    false    4662    219    217            ?           2606    25441 #   reservation reservation_car_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_car_id_fkey FOREIGN KEY (car_id) REFERENCES public.car(car_id);
 M   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_car_id_fkey;
       public          postgres    false    4666    221    224            �   ^   x�3�J�K,�)�2����..OLO��2�t�/J�2��/H��2�ɯ�/I�2����KI�2��M���N��\��~��
�>>�~.\1z\\\ z@�      �   q   x�M˫�@ Q=�1d�c��A��hY���`v���4?(9¼�C��~�3֊�r���,�q.��Wi�W*XG=��qQ��J[:��8�B4�KމZ�nRJ?�� �      �   �   x�}�MN�0���Sp���i�&1T����n7������A�S�9F*P�Q�O��f&B�������A7o`�q�:�;�RGm	����0��Y�ѻw�MC88�]3��V�\	�.�>�6��޴�P���0͂�?-C�Zͯ�_aΨ9�]V���B*YJT�
�z��N��yR(���'�Ӱ��W�P'�Q�a�z[�z�I�b𽾩�y����	�?�I��/���o��}�      �   \  x���]n�0ǟ�Sp�|��4�IH0iЇI{A4A$����[q6;���&�!�]���0���GB*my1��1}�a�٬�vdߗ�mk�U� �T}�����
Co<�,����v��L���2��ܻf}���C>l0��)�;�|�$����7���jZ-�@+<d� 2���QR�\A.)sj�P=�W3V�Ì�Y9-{�j�(����pV;�ַMm��v��n{��%=ȗK�#���ݵ����S )t��d1�H��.� �b�@� m�0����B�B:��7v���3��A#�8��@��*�	�n����4� ����h��Ż���-�$�.��1�>6*�      �   3   x�3�LL���3�R�����E).���Qh��x����k
��I�=... r0@     