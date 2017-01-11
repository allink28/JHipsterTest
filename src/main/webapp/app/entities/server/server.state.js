(function() {
    'use strict';

    angular
        .module('jHipsterTestApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('server', {
            parent: 'entity',
            url: '/server',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Servers'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/server/servers.html',
                    controller: 'ServerController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('server-detail', {
            parent: 'entity',
            url: '/server/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Server'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/server/server-detail.html',
                    controller: 'ServerDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Server', function($stateParams, Server) {
                    return Server.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'server',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('server-detail.edit', {
            parent: 'server-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/server/server-dialog.html',
                    controller: 'ServerDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Server', function(Server) {
                            return Server.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('discoveryRequest', {
            parent: 'server',
            url: '/discoveryRequest/{id}',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/server/discovery-dialog.html',
                    controller: 'DiscoveryDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Server', function(Server) {
                            return Server.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('server', null, { reload: 'server' });
                }, function() {
                    $state.go('server');
                });
            }]
        })
        .state('server.new', {
            parent: 'server',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/server/server-dialog.html',
                    controller: 'ServerDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                uri: null,
                                username: null,
                                password: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('server', null, { reload: 'server' });
                }, function() {
                    $state.go('server');
                });
            }]
        })
        .state('server.edit', {
            parent: 'server',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/server/server-dialog.html',
                    controller: 'ServerDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Server', function(Server) {
                            return Server.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('server', null, { reload: 'server' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('server.delete', {
            parent: 'server',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/server/server-delete-dialog.html',
                    controller: 'ServerDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Server', function(Server) {
                            return Server.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('server', null, { reload: 'server' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
