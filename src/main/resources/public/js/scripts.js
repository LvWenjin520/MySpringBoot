(function($) {
    $(document).ready(function() {
        "use strict";

        // WORKS SLIDER
        var swiper = new Swiper('.works-slider', {
            slidesPerView: 'auto',
            centeredSlides: true,
            spaceBetween: 80,
        });


        // SLIDER
        $.exists = function(selector) {
            return ($(selector).length > 0);
        }
        ms_home_slider();

        function ms_home_slider() {
            if ($.exists('.swiper-container')) {
                var swiper = new Swiper('.swiper-container', {
                    loop: false,
                    speed: 1000,
					touchRatio: 0,
                    grabCursor: false,
                    mousewheel: true,
                    keyboard: true,
                    simulateTouch: true,
                    parallax: true,
                    effect: 'slide',
                    direction: 'vertical',
                    pagination: {
                        el: '.swiper-pagination',
                        clickable: true,
                    },
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',
                    }
                });
                $('.slider-fraction span:first-child').text('1');
                $('.slider-fraction span:last-child').text(swiper.slides.length);
                swiper.on('slideChange', function() {
                    $('.slider-fraction span:first-child').text(swiper.activeIndex + 1);
                });

            }
        }


        // TREE MENU
        $('.site-navigation ul li.dropdown span').on('click', function(e) {
            $(this).parent().children('.site-navigation ul li ul').slideToggle(300);
            return true;
        });


        // SCROLL DOWN
        $(".bottom-bar .scroll-down a").on('click', function(e) {
            $('html, body').animate({
                scrollTop: $("section").offset().top
            }, 500);
        });


        // SOUND TEXT TOGGLE
        $(".sound .equalizer").on('click', function(e) {
            $(".sound b").text(function(i, v) {
                return v === 'dancing bars' ? 'bars stop' : 'dancing bars'
            })
        });


        // PARALLAX
        $.stellar({
            horizontalScrolling: false,
            verticalOffset: 0,
            responsive: true
        });


        // PAGE TRANSITION
        $('body a').on('click', function(e) {
            if (typeof $(this).data('fancybox') == 'undefined') {
                e.preventDefault();
                var url = this.getAttribute("href");
                if (url.indexOf('#') != -1) {
                    var hash = url.substring(url.indexOf('#'));
                    if(hash != '#'){
                    	if ($('body' + hash).length != 0) {
                            $('.page-transition').removeClass("active");
                            $(".sandiwch").toggleClass("open");
                            $(".site-menavigation").removeClass("active");
                        }
                    }
                    
                } else {
                    $('.page-transition').toggleClass("active");
                    setTimeout(function() {
                        window.location = url;
                    }, 1000);
                }
            }
        });


        // SANDWICH MENU
        $('.sandwich').on('click', function(e) {
            if ($("body").hasClass("display-nav")) {
                $(".sandwich .sand span:nth-child(1)").css("transition-delay", "0.6s");
                $(".sandwich .sand span:nth-child(2)").css("transition-delay", "0.75s");
                $(".page-header .container").css("transition-delay", "0.8s");
                $(".video-bg .container").css("transition-delay", "0.8s");
                $(".slider .slide-content").css("transition-delay", "0.8s");
                $(".kinetic-slider ").css("transition-delay", "0.8s");

                window.setTimeout(function() {
                    $(".sandwich .sand span:nth-child(1)").css("transition-delay", "0s");
                    $(".sandwich .sand span:nth-child(2)").css("transition-delay", "0s");
                }, 1000);

            } else {

                $(".sandwich .sand span:nth-child(1)").css("transition-delay", "0s");
                $(".sandwich .sand span:nth-child(2)").css("transition-delay", "0.15s");
                $(".page-header .container").css("transition-delay", "0s");
                $(".video-bg .container").css("transition-delay", "0s");
                $(".slider .slide-content").css("transition-delay", "0s");
                $(".kinetic-slider").css("transition-delay", "0s");

            }
            $("body").toggleClass("display-nav");
        });


        // DATA BACKGROUND IMAGE
        var pageSection = $("*");
        pageSection.each(function(indx) {
            if ($(this).attr("data-background")) {
                $(this).css("background-image", "url(" + $(this).data("background") + ")");
            }
        });


        /* MAGNET CURSOR*/
        var cerchio = document.querySelectorAll('.magnet-link');
        cerchio.forEach(function(elem) {
            $(document).on('mousemove touch', function(e) {
                magnetize(elem, e);
            });
        })

        function magnetize(el, e) {
            var mX = e.pageX,
                mY = e.pageY;
            const item = $(el);

            const customDist = item.data('dist') * 20 || 80;
            const centerX = item.offset().left + (item.width() / 2);
            const centerY = item.offset().top + (item.height() / 2);

            var deltaX = Math.floor((centerX - mX)) * -0.35;
            var deltaY = Math.floor((centerY - mY)) * -0.35;

            var distance = calculateDistance(item, mX, mY);

            if (distance < customDist) {
                TweenMax.to(item, 0.5, {
                    y: deltaY,
                    x: deltaX,
                    scale: 1
                });
                item.addClass('magnet');
            } else {
                TweenMax.to(item, 0.6, {
                    y: 0,
                    x: 0,
                    scale: 1
                });
                item.removeClass('magnet');
            }
        }

        function calculateDistance(elem, mouseX, mouseY) {
            return Math.floor(Math.sqrt(Math.pow(mouseX - (elem.offset().left + (elem.width() / 2)), 2) + Math.pow(mouseY - (elem.offset().top + (elem.height() / 2)), 2)));
        }

        function lerp(a, b, n) {
            return (1 - n) * a + n * b
        }

        // Inizio Cursor
        class Cursor {
            constructor() {
                this.bind()
                //seleziono la classe del cursore
                this.cursor = document.querySelector('.js-cursor')

                this.mouseCurrent = {
                    x: 0,
                    y: 0
                }

                this.mouseLast = {
                    x: this.mouseCurrent.x,
                    y: this.mouseCurrent.y
                }

                this.rAF = undefined
            }

            bind() {
                ['getMousePosition', 'run'].forEach((fn) => this[fn] = this[fn].bind(this))
            }

            getMousePosition(e) {
                this.mouseCurrent = {
                    x: e.clientX,
                    y: e.clientY
                }
            }

            run() {
                this.mouseLast.x = lerp(this.mouseLast.x, this.mouseCurrent.x, 0.2)
                this.mouseLast.y = lerp(this.mouseLast.y, this.mouseCurrent.y, 0.2)

                this.mouseLast.x = Math.floor(this.mouseLast.x * 100) / 100
                this.mouseLast.y = Math.floor(this.mouseLast.y * 100) / 100

                this.cursor.style.transform = `translate3d(${this.mouseLast.x}px, ${this.mouseLast.y}px, 0)`

                this.rAF = requestAnimationFrame(this.run)
            }

            requestAnimationFrame() {
                this.rAF = requestAnimationFrame(this.run)
            }

            addEvents() {
                window.addEventListener('mousemove', this.getMousePosition, false)
            }

            on() {
                this.addEvents()

                this.requestAnimationFrame()
            }

            init() {
                this.on()
            }
        }

        const cursor = new Cursor()

        cursor.init();

        $('a, .sandwich, .equalizer, .swiper-pagination-bullet, .swiper-button-prev, .swiper-button-next, .main-nav').hover(function() {
            $('.cursor').toggleClass('light');
        });


    });
    // END JQUERY	


    // MASONRY
    function masonry_init() {
        $('.masonry').masonry({
            itemSelector: '.masonry-grid',
            columnWidth: '.masonry-grid',
            percentPosition: true
        });
    }

    window.onload = masonry_init;


    // EQUALIZER TOGGLE
    /*var source = "audio/audio01.mp3";
    var audio = new Audio(); // use the constructor in JavaScript, just easier that way
    audio.addEventListener("load", function() {
        audio.play();
    }, true);
    audio.src = source;
    audio.autoplay = true;
    audio.loop = true;
    audio.volume = 0.2;

    $('.equalizer').click();
    var playing = true;
    $('.equalizer').on('click', function(e) {
        if (playing == false) {
            audio.play();
            playing = true;

        } else {
            audio.pause();
            playing = false;
        }
    });*/


    // EQUALIZER
    function randomBetween(range) {
        var min = range[0],
            max = range[1];
        if (min < 0) {
            return min + Math.random() * (Math.abs(min) + max);
        } else {
            return min + Math.random() * max;
        }
    }

    $.fn.equalizerAnimation = function(speed, barsHeight) {
        var $equalizer = $(this);
        setInterval(function() {
            $equalizer.find('span').each(function(i) {
                $(this).css({
                    height: randomBetween(barsHeight[i]) + 'px'
                });
            });
        }, speed);
        $equalizer.on('click', function() {
            $equalizer.toggleClass('paused');
        });
    }

    var barsHeight = [
        [2, 10],
        [5, 14],
        [11, 8],
        [4, 18],
        [8, 3]
    ];
    $('.equalizer').equalizerAnimation(180, barsHeight);

    // COUNTER
    $(document).scroll(function() {
        $('.odometer').each(function() {
            var parent_section_postion = $(this).closest('section').position();
            var parent_section_top = parent_section_postion.top;
            if ($(document).scrollTop() > parent_section_top - 300) {
                if ($(this).data('status') == 'yes') {
                    $(this).html($(this).data('count'));
                    $(this).data('status', 'no')
                }
            }
        });
    });


    // PRELOADER
    let settings = {
        progressSize: 320,
        progressColor: '#ffffff',
        lineWidth: 2,
        lineCap: 'round',
        preloaderAnimationDuration: 1000,
        startDegree: -90,
        finalDegree: 270
    }

    function setAttributes(elem, attrs) {

        for (let key in attrs) {
            elem.setAttribute(key, attrs[key]);
        }

    }

    let preloader = document.createElement('div'),
        canvas = document.createElement('canvas'),
        size;

    (function() {

        let width = window.innerWidth,
            height = window.innerHeight;

        if (width > height) {

            size = Math.min(settings.progressSize, height / 2);

        } else {

            size = Math.min(settings.progressSize, width - 50);

        }

    })();

    setAttributes(preloader, {
        class: "preloader",
        id: 'preloader',
        style: 'transition: opacity ' + settings.preloaderAnimationDuration / 1000 + 's'
    });
    setAttributes(canvas, {
        class: 'progress-bar',
        id: 'progress-bar',
        width: settings.progressSize,
        height: settings.progressSize
    });


    preloader = document.getElementById('preloader');

    let progressBar = document.getElementById('progress-bar'),
        images = document.images,
        imagesAmount = images.length,
        imagesLoaded = 0,
        barCtx = progressBar.getContext('2d'),
        circleCenterX = progressBar.width / 2,
        circleCenterY = progressBar.height / 2,
        circleRadius = circleCenterX - settings.lineWidth,
        degreesPerPercent = 3.6,
        currentProgress = 0,
        showedProgress = 0,
        progressStep = 0,
        progressDelta = 0,
        startTime = null,
        running;

    (function() {

        return requestAnimationFrame ||
            mozRequestAnimationFrame ||
            webkitRequestAnimationFrame ||
            oRequestAnimationFrame ||
            msRequestAnimationFrame ||
            function(callback) {
                setTimeout(callback, 1000 / 60);
            };

    })();

    Math.radians = function(degrees) {
        return degrees * Math.PI / 180;
    };


    progressBar.style.opacity = settings.progressOpacity;
    barCtx.strokeStyle = settings.progressColor;
    barCtx.lineWidth = settings.lineWidth;
    barCtx.lineCap = settings.lineCap;
    let angleMultiplier = (Math.abs(settings.startDegree) + Math.abs(settings.finalDegree)) / 360;
    let startAngle = Math.radians(settings.startDegree);
    document.body.style.overflowY = 'hidden';
    preloader.style.backgroundColor = settings.preloaderBackground;


    for (let i = 0; i < imagesAmount; i++) {

        let imageClone = new Image();
        imageClone.onload = onImageLoad;
        imageClone.onerror = onImageLoad;
        imageClone.src = images[i].src;

    }

    function onImageLoad() {

        if (running === true) running = false;

        imagesLoaded++;

        if (imagesLoaded >= imagesAmount) hidePreloader();

        progressStep = showedProgress;
        currentProgress = ((100 / imagesAmount) * imagesLoaded) << 0;
        progressDelta = currentProgress - showedProgress;

        setTimeout(function() {

            if (startTime === null) startTime = performance.now();
            running = true;
            animate();

        }, 10);

    }

    function animate() {

        if (running === false) {
            startTime = null;
            return;
        }

        let timeDelta = Math.min(1, (performance.now() - startTime) / settings.preloaderAnimationDuration);
        showedProgress = progressStep + (progressDelta * timeDelta);

        if (timeDelta <= 1) {


            barCtx.clearRect(0, 0, progressBar.width, progressBar.height);
            barCtx.beginPath();
            barCtx.arc(circleCenterX, circleCenterY, circleRadius, startAngle, (Math.radians(showedProgress * degreesPerPercent) * angleMultiplier) + startAngle);
            barCtx.stroke();
            requestAnimationFrame(animate);

        } else {
            startTime = null;
        }

    }

    function hidePreloader() {

        setTimeout(function() {

            $("body").addClass("page-loaded");

            document.body.style.overflowY = '';

        }, settings.preloaderAnimationDuration + 100);

    }
    var resizeTimer;


})(jQuery);