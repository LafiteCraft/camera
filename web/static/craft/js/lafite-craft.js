var ajaxCraft = (function($){
    var $$ = $;
    var baseUrl = '';
    var findByIdUrl = '';
    var findUrl = '';
    var saveUrl = '';
    var saveListUrl = '';
    var updateUrl = '';
    var updateListUrl = '';
    var removeUrl = '';
    var removeListUrl = '';
    /*初始化路径*/
    var init = function (basePath) {
        var that = this;
        baseUrl = basePath;
        findByIdUrl = basePath + '/find_by_id';
        findUrl = basePath + '/find';
        saveUrl = basePath + '/save';
        saveListUrl = basePath + '/save_list';
        updateUrl = basePath + '/update';
        updateListUrl = basePath + '/update_list';
        removeUrl = basePath + '/remove';
        removeListUrl = basePath + '/remove_list';
        return that;
    }
    /*通过ID查询*/
    var findById = function (id, callback) {
        $$.post(findByIdUrl, {'id': id}, function (result) {
            if(result.error){
                throw error.message;
            }else {
                callback(result);
            }
        });
    }

    var find = function (pageNum, pageSize, where, callback) {
        $$.post(findUrl, {
            'pageNum': pageNum,
            'pageSize': pageSize,
            'where': JSON.stringify(where)
        }, function (data) {
            if(data.error){
                throw error.message;
            }else {
                callback(data);
            }
        });
    }

    /*保存*/
    var save = function (entity, callback) {
        $$.post(saveUrl, {'entity': JSON.stringify(entity)}, function (result) {
            if(result.error){
                throw error.message;
            }else {
                callback(result);
            }
        });
    }

    /*保存多个*/
    var saveList = function (entityList, callback) {
        $$.post(saveListUrl, {'entityList': JSON.stringify(entityList)}, function (result) {
            if(result.error){
                throw error.message;
            }else {
                callback(result);
            }
        });
    }

    /*修改*/
    var update = function (entity, callback) {
        $$.post(updateUrl, {'entity': JSON.stringify(entity)}, function (result) {
            if(result.error){
                throw error.message;
            }else {
                callback(result);
            }
        });
    }

    /*修改多个*/
    var updateList = function (entityList, callback) {
        $$.post(updateListUrl, {'entityList': JSON.stringify(entityList)}, function (result) {
            if(result.error){
                throw error.message;
            }else {
                callback(result);
            }
        });
    }

    /*删除*/
    var remove = function (id, callback) {
        $$.post(removeUrl, {'id': id}, function (result) {
            if(result.error){
                throw error.message;
            }else {
                callback(result);
            }
        });
    }

    /*删除多个*/
    var removeList = function (idList, callback) {
        $$.post(removeListUrl, {'ids': JSON.stringify(idList)}, function (result) {
            if(result.error){
                throw error.message;
            }else {
                callback(result);
            }
        });
    }


    return {
        init: init,
        findById: findById,
        find: find,
        save: save,
        saveList: saveList,
        remove: remove,
        removeList: removeList,
        update: update,
        updateList: updateList
    }
})($);
