(function() {
  'use strict';

  angular
    .module('refiereApp', [
      'refiereApp.chore',
      'refiereApp.dashboard',
	  'refiereApp.registerNewReferred',
      'refiereApp.login',
      'refiereApp.register',
      'refiereApp.services',
      'refiereApp.campaign',
      'ui.router']);
})();

