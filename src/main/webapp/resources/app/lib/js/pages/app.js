var CargoSpring = angular.module('CargoSpring');
CargoSpring.service('PageService', function () {

    this.totalPageNumber = function (pageRecords, totalRecords) {
        var totalPageNumber = 1;
        if (typeof totalRecords != 'undefined') {
            totalPageNumber = Math.floor((totalRecords + pageRecords - 1) / pageRecords);
        }
        return (totalPageNumber == 0) ? 1 : totalPageNumber;
    };

    this.isPrevDisabled = function (currentPage) {
        return currentPage === 1 ? "disabled" : "";
    };

    this.isNextDisabled = function (currentPage, totalPageCountt) {
        return currentPage === totalPageCountt ? "disabled" : "";
    };

});