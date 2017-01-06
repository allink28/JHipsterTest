(function() {
    'use strict';

    angular
        .module('jHipsterTestApp')
        .controller('ServerDetailController', ServerDetailController);

    ServerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Server'];

    function ServerDetailController($scope, $rootScope, $stateParams, previousState, entity, Server) {
        var vm = this;

        vm.server = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jHipsterTestApp:serverUpdate', function(event, result) {
            vm.server = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
