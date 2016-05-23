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
      getCompanyAddress: getCompanyAddress,
      getCompanyEmail: getCompanyEmail,
      getCompanyName: getCompanyName,
      getCompanyTelephone: getCompanyTelephone,
      getAllUserData: getAllUserData
    };
    return service;

    function setUserInfoData(userInfoData) {
      userInfo = userInfoData;
    }

    function setUserName(login) {
      userInfo.name = login;
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

  }
})();
