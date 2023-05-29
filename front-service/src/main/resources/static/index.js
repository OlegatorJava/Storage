angular.module('app', ['ngStorage']).controller('myController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8099/app';

    $scope.loadAllProducts = function (){
        $http.get(contextPath + "/product")
            .then(function (response){
                $scope.productList = response.data;
        });

    };

    $scope.loadAllProductsInCart = function (){
        $http.get("http://localhost:8090/app/cart")
            .then(function (response){
                console.log(response.data);
                $scope.cart = response.data;
            });

    };

    $scope.addInCart = function (id){
        $http.get("http://localhost:8090/app/cart/add/ "+ id)
            .then(function (response){
                console.log("Все ОК");
                $scope.loadAllProductsInCart();
            });
    }

    $scope.changeQuantity = function (product_id, quantity_delta){
        $http({
            url: "http://localhost:8090/app/cart/change",
            method: 'GET',
            params: {
                product_id: product_id,
                delta: quantity_delta
            }
            }).then(function (response){
                console.log("Изменили количество");
                $scope.loadAllProductsInCart();
            });
    }

    $scope.clearCart = function (){
        $http.get("http://localhost:8090/app/cart/clear")
            .then(function (response){
                console.log("Очистили корзину");
                $scope.loadAllProductsInCart();
            });
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8099/app/auth', $scope.user)
            .then(function successCallback(response) {
                if(response.data.token){
                    console.log(response.data.token)
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback(response){
                console.log("Ошибка: " + response)
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
    };
    $scope.clearUser = function (){
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };
    $scope.isUserLoggedIn = function () {
        if($localStorage.springWebUser){
            return true;
        }else{
            return false;
        }
    };

    if($localStorage.springWebUser){
        try {
            let jwt = $localStorage.springWebUser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currenttime = parseInt(new Date().getTime() / 1000);
            console.log(currenttime)
            if(currenttime > payload.exp){
                console.log("Токен истек");
                delete $localStorage.springWebUser;
                $http.defaults.headers.common.Authorization = '';
            }
        }catch (e){

        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    $scope.checkout = function () {
        $http({
            url: contextPath + '/order/add',
            method: 'GET',
            params: {
                username: $localStorage.springWebUser.username
            }
        }).then(function successCallback(response){
                $scope.clearCart()
            });
    };


    $scope.loadAllProducts();
    $scope.loadAllProductsInCart();

});