(function() {
    'use strict';

    angular
        .module('jHipsterTestApp')
        .controller('ServerDialogController', ServerDialogController);

    ServerDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Server'];

    function ServerDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Server) {
        var vm = this;

        vm.server = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            console.log("server-dialog's clear function called");
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.server.id !== null) {
                Server.update(vm.server, onSaveSuccess, onSaveError);
            } else {
                Server.save(vm.server, onSaveSuccess, onSaveError);
            }
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
