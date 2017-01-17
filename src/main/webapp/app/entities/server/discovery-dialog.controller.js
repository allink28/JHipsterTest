(function() {
    'use strict';

    angular
        .module('jHipsterTestApp')
        .controller('DiscoveryDialogController', DiscoveryDialogController);

    DiscoveryDialogController.$inject = ['$timeout', '$scope', '$http', '$stateParams', '$uibModalInstance', 'entity', 'Server'];

    function DiscoveryDialogController ($timeout, $scope, $http, $stateParams, $uibModalInstance, entity, Server) {
        var vm = this;

        vm.server = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            console.log("discovery's clear function called");
            $uibModalInstance.dismiss('cancel');
        }

        function request () {
            console.log('submitDiscoveryRequest function called');
            //todo  Call discovery endpoint
            $http({
                method: 'GET',
                url: '/api/commands/servers'
            }).then(function successCallback(response) {
                console.log("Successcallback has response: " + response);
            }, function errorCallback(response) {
                console.log("Error callback! Response: " + response);
            });

        }

        function save () {
            console.log('discovery-dialog save function called');
            request();
//            vm.isSaving = true;
//            if (vm.server.id !== null) {
//                Server.update(vm.server, onSaveSuccess, onSaveError);
//            } else {
//                Server.save(vm.server, onSaveSuccess, onSaveError);
//            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jHipsterTestApp:serverUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
