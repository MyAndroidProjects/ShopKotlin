# Simple Shop (ShopKotlin)

* [О приложении](#about)
* [Архитектура](#architecture)
* [Описание](#description)
* [Дополнительные библиотеки](#libraries)
* [Screenshots](#screenshots)
  * [Каталог](#catalog)
  * [Корзина](#shopping_cart)


### <a name="about"></a>О приложении:
Приложение - упрощенная имитация интернет магазина. 
Состоит из двух activity: MainActivity и ShoppingCartActivity. MainActivity отвечает за отображение каталога и товаров по категориям с помощью фрагментов: CatalogFragment (отображение каталога), ProductListFragment (отображение списка продуктов в категории), ProductInfoFragment (отображение более подробной информации о товаре) и NavigationViewFragment (переключение между категориями товаров или возврат в каталог); ShoppingCartActivity - отображает корзину интернет магазина, также использует NavigationViewFragment.
Два языка: английский (по умолчанию) и русский.

### <a name="architecture"></a>Архитектура:
Приложение реализует паттерн **MVP**. В контракте прописаны интерфейсы View, Presenter, Navigator, Model. При необходимости взаимодействия презентера с другими презентарами, либо с контекстом (например, поменять фрагмент в activity) через интерфейс Navigator передается сигнал об этом в NavigationManager (у которого есть свой контракт), который уже связывается либо с нужной activity (для команд, использующих context приложения), либо с нужным презентером. Model с которой взаимодействуют презентеры для получения данных представляет собой менеджер: class DatabasesManager, взаимодействующий с базами данных. Приложение содержит две базы данных: база товаров по категориям и база товаров находящихся в корзине магазина. 
Схематичное изображение взаимодействий:

![architecture scheme](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/architecture540.png)

### <a name="description"></a>Описание:
При первом открытии приложения (контролируется при помощи SharedPreferences) создается база данных, для каждой категории товара - отдельная таблица. Открывается MainActivity (содержит Collapsing Toolbar Layout Floating Action Button) и  с запущенным CatalogFragment  из которого можно перейти в соответствующую категорию товара , также для перехода между категориями товаров используется navigationView, выполненный отдельным фрагментом, который также используется в ShoppingCartActivity. При выборе категории товара открывается ProductListFragment, который запрашивает список товаров из базы данных (посредством своего презентера) и отображает его в recyclerView. При выборе карточки товара открывается ProductInfoFragment с более подробной информацией по продукту, а также появляются две дополнительные floating action button: поместить товар в корзину и вернуться назад к списку. При добавлении в корзину появляется snack bar с наименованием добавленного товара, отображенное на тулбаре количество товара увеличивается, приложение возвращается назад к списку, в базу данных корзины добавляется запись товара. Из любого места приложения можно попасть в корзину магазина нажав на floating action button с иконкой корзины или на toolbar (на аналогичную иконку с количеством товаров в корзине). 
На экране корзины отображен toolbar с иконкой меню и количеством товаров, список товаров, загруженный из базы данных корзины (посредством своего презентера) , информация с итоговыми числами, и кнопками "назад" "очистить" и "купить". Запись товара снабжена кнопкой "убрать". Соответственно товар можно убрать из корзины, полностью очистить корзину, либо "приобрести" товар, всё вышеперечисленное сопровождается диалоговыми окнами, а при завершении операции - snackbar с соответствующим сообщением. При удалении всех товаров из корзины, либо при переходе в пустую корзину появится диалоговое окно в одной кнопкой, с последующим возвратом в каталог. Из корзины можно вернуться назад либо сответствующими кнопками, либо выбрав в navigationView необходимый пункт меню.

### <a name="libraries"></a>Дополнительные библиотеки:
* constraint-layout
* design
* gridlayout-v7
* cardview-v7
* recyclerview-v7
* anko-commons
* anko-design
* anko-sqlite
* picasso
***
### <a name="screenshots"></a>Screenshots:
#### <a name="catalog"></a>Каталог:
![ru_port_1](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_port_1.png) ![ru_port_8](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_port_8.png) ![ru_port_7](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_port_7.png) ![en_port_7](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/en_port_7.png) 
![ru_land_1](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_land_1.png) ![ru_land_12](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_land_12.png) ![en_land_3](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/en_land_3.png) ![ru_land_5](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_land_5.png) 

#### <a name="shopping_cart"></a>Корзина:
![ru_port_21](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_port_21.png) ![ru_port_25](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_port_25.png) ![ru_port_12](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_port_12.png) ![en_port_8](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/en_port_8.png) 
![en_land_2](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/en_land_2.png) ![en_land_5](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/en_land_5.png) ![ru_land_9](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_land_9.png) ![ru_land_10](https://github.com/MyAndroidProjects/ShopKotlin/blob/master/readmeImages/ru_land_10.png) 
