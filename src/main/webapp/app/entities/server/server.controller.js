(function() {
    'use strict';

    angular
        .module('jHipsterTestApp')
        .controller('ServerController', ServerController);

    ServerController.$inject = ['$scope', '$state', 'Server'];

    function ServerController ($scope, $state, Server) {
        var vm = this;

        vm.servers = [];

        loadAll();

        function loadAll() {
            Server.query(function(result) {
                vm.servers = result;
                vm.searchQuery = null;
            });
        }
    }
})();
