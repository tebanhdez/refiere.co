(function() {
  angular
    .module('refiereApp.services')
    .factory('SessionModel', sessionModel);

    function sessionModel() {
      var model = {
        password: '',
        username: ''
      };

      return model;
    }
})();
