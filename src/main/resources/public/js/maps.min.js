ymaps.ready(init);

function init() {
  var myMap = new ymaps.Map("map", {
    center: [41.0082, 28.9784],
    zoom: 18,
    <!--Скрыть элементы управления: controls: []	 -->
    controls: []
  }, {
    searchControlProvider: 'yandex#search'
  });

  //Media Queries
  /*
        		if (window.matchMedia("(max-width: 1500px)").matches) {
        			myMap.setCenter([50.436179, 30.5215])
        		};
        		if (window.matchMedia("(max-width: 992px)").matches) {
        			myMap.setCenter([50.436179, 30.5218])
        		};
        		if (window.matchMedia("(max-width: 767px)").matches) {
        			myMap.setCenter([50.436179, 30.5200])
        		};
				
				*/
  // Создаем геообъект с типом геометрии "Точка".
  myGeoObject = new ymaps.GeoObject({
    // Описание геометрии.
    geometry: {
      type: "Point",
      coordinates: [41.0082, 28.9784]
    },
    // Свойства.

  }, {
    // Опции.
    // Иконка метки будет растягиваться под размер ее содержимого.
    preset: 'islands#blackStretchyIcon',
    // Метку можно перемещать.
    draggable: false,
    // Необходимо указать данный тип макета.
    iconLayout: 'default#image',
    // Своё изображение иконки метки.
    iconImageHref: 'images/map-pin.svg',
    // Размеры метки.
    iconImageSize: [44, 44],
    // Смещение левого верхнего угла иконки относительно
    // её "ножки" (точки привязки).
    //iconImageOffset: [-7, -110]
  });

  myMap.behaviors
    .disable('scrollZoom')


  myMap.geoObjects
    .add(myGeoObject)

}
