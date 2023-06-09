var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            profile_id: $('#profile_id').val(),
            career: $('#career').val(),
            education: $('#education').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/profiles',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('프로필이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            career: $('#career').val(),
            education: $('#education').val(),
            content: $('#content').val()
        };

        var profile_id = $('#profile_id').val();

        $.ajax({
            type: 'PUT',
            url: '/profiles/'+profile_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('프로필이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var profile_id = $('#profile_id').val();

        $.ajax({
            type: 'DELETE',
            url: '/profiles/'+profile_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('프로필이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();