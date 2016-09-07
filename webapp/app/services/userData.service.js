(function() {
  angular
    .module('refiereApp.services')
    .factory('UserDataService', UserDataService);

  UserDataService.$inject = [];

  function UserDataService() {

    var userInfo = {};
    var service = {
      setUserInfoData: setUserInfoData,
      setUserName: setUserName,
      resetUserData: resetUserData,
      getRoleIdentifier: getRoleIdentifier,
      getUserName: getUserName,
      getCompanyID: getCompanyID,
      getCompanyAddress: getCompanyAddress,
      getCompanyEmail: getCompanyEmail,
      getCompanyName: getCompanyName,
      getCompanyTelephone: getCompanyTelephone,
      getAllUserData: getAllUserData,
      setAuthData: setAuthData,
      getAuthData: getAuthData,
      logout: logout
    };
    return service;

    function setUserInfoData(userInfoData) {
      userInfo = userInfoData;
    }

    function setUserName(login) {
      userInfo.name = login;
    }

    function setAuthData(basicAuth) {
      userInfo.authData = basicAuth;
    }

    function getAllUserData() {
      return userInfo;
    }

    function getUserName() {
      return userInfo.name;
    }

    function getRoleIdentifier() {
      return userInfo.RoleIdentifier;
    }

    function getCompanyID() {
      return userInfo.company.id;
    }

    function getCompanyAddress() {
      return userInfo.company.address;
    }

    function getCompanyEmail() {
      return userInfo.company.email;
    }

    function getCompanyName() {
      return userInfo.company.name;
    }

    function getCompanyTelephone() {
      return userInfo.company.phone;
    }

    function resetUserData() {
      userInfo = {};
    }

    function getAuthData() {
      return userInfo.authData;
    }
    
    function logout() {
    	userInfo = {};
    }

  }
})();
