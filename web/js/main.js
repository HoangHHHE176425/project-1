(function ($) {
    "use strict";

    // Dropdown on mouse hover
    $(document).ready(function () {
        function toggleNavbarMethod() {
            if ($(window).width() > 992) {
                $('.navbar .dropdown').on('mouseover', function () {
                    $('.dropdown-toggle', this).trigger('click');
                }).on('mouseout', function () {
                    $('.dropdown-toggle', this).trigger('click').blur();
                });
            } else {
                $('.navbar .dropdown').off('mouseover').off('mouseout');
            }
        }
        toggleNavbarMethod();
        $(window).resize(toggleNavbarMethod);
    });

    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });

    // Vendor carousel
    $('.vendor-carousel').owlCarousel({
        loop: true,
        margin: 29,
        nav: false,
        autoplay: true,
        smartSpeed: 1000,
        responsive: {
            0: { items: 2 },
            576: { items: 3 },
            768: { items: 4 },
            992: { items: 5 },
            1200: { items: 6 }
        }
    });

    // Related carousel
    $('.related-carousel').owlCarousel({
        loop: true,
        margin: 29,
        nav: false,
        autoplay: true,
        smartSpeed: 1000,
        responsive: {
            0: { items: 1 },
            576: { items: 2 },
            768: { items: 3 },
            992: { items: 4 }
        }
    });

    // Product Quantity
    $('.quantity button').on('click', function () {
        var button = $(this);
        var oldValue = button.parent().parent().find('input').val();
        var newVal;
        if (button.hasClass('btn-plus')) {
            newVal = parseFloat(oldValue) + 1;
        } else {
            newVal = (oldValue > 0) ? parseFloat(oldValue) - 1 : 0;
        }
        button.parent().parent().find('input').val(newVal);
    });

})(jQuery);

// Function to sort products
function sortProducts(sortOption) {
    const urlParams = new URLSearchParams(window.location.search);
    let keywords = urlParams.get('keywords');
    let service = urlParams.get('service');
    let pageSize = urlParams.get('pageSize');

    if (service === null) {
        service = "searchByKeywords";
    }

    if (keywords === null) {
        keywords = "";
    }

    if (pageSize === null) {
        pageSize = 6;
    }

    // Set pageNumber to 1 when sorting
    const pageNumber = 1;

    window.location.href = "customer?service=" + service + "&keywords=" + keywords + "&sortBy=" + sortOption + "&pageNumber=" + pageNumber + "&pageSize=" + pageSize;
}

// Function to filter products
function filter(value) {
    console.log(value);
    const urlParams = new URLSearchParams(window.location.search);
    let keywords = urlParams.get('keywords');
    let service = urlParams.get('service');
    let sortOption = urlParams.get('sortBy');
    let filterByPrice = urlParams.get('filterByPrice');
    let filterByBrand = urlParams.get('filterByBrand');

    if (service === null) {
        service = "searchByKeywords";
    }

    if (keywords === null) {
        keywords = "";
    }

    if (sortOption === null) {
        sortOption = "unsorted";
    }

    if (value.toString().includes("price")) {
        if (filterByBrand === null) {
            filterByBrand = "brand-all";
        }
        window.location.href = "customer?service=" + service + "&keywords=" + keywords
                + "&sortBy=" + sortOption + "&filterByBrand=" + filterByBrand + "&filterByPrice=" + value;
    }

    if (value.toString().includes("brand")) {
        if (filterByPrice === null) {
            filterByPrice = "price-all";
        }
        window.location.href = "customer?service=" + service + "&keywords=" + keywords
                + "&sortBy=" + sortOption + "&filterByBrand=" + value + "&filterByPrice=" + filterByPrice;
    }
}

function filterStatus(value) {
    const urlParams = new URLSearchParams(window.location.search);
    let service = urlParams.get('service');
    
    if (service === null) {
        service = "filterStatus";
    }
    
    window.location.href = "manageBill?service=" + service + "&filter=" + value;
}


    // Function to get a cookie value by name
    function getCookie(name) {
        let cookieArr = document.cookie.split(";");
        for (let i = 0; i < cookieArr.length; i++) {
            let cookiePair = cookieArr[i].split("=");
            if (name === cookiePair[0].trim()) {
                return decodeURIComponent(cookiePair[1]);
            }
        }
        return null;
    }

    // Set the hidden input fields with cookie values
    document.getElementById("userId").value = getCookie("userId");
    document.getElementById("roleId").value = getCookie("roleId");
