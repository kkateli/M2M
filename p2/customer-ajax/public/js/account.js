/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";
let module = angular.module('LoginModule', ['ngResource']);

module.factory('accountApi', function ($resource) {
          return $resource('http://localhost:8882/customers');
});

//module.factory('putAccountApi', function ($resource) {
//          return $resource('http://localhost:8082/api/customers/account/:username',
//              null, {update: {method: 'PUT'}});
//          });


module.controller('LoginController', function (accountApi) {
    let ctrl = this;
    
    this.addAccount = function (accountToAdd){
        accountApi.save({},accountToAdd, function(){
            alert("Account Created");
        });
    };
    
}); 