(function() {
    'use strict';
    angular
        .module('jHipsterTestApp')
        .factory('Server', Server);

    Server.$inject = ['$resource'];

    function Server ($resource) {
        var resourceUrl =  'api/servers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) { //'data' is initially the server id, name, uri, etc. in json format
                    if (data) {
                        data = angular.fromJson(data); //converts Json to Object
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
