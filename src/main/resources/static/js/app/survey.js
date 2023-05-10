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
            survey_id: $('#survey_id').val(),
            name: $('#name').val(),
            gender: $('#gender').val(),
            email: $('#email').val(),
            birth: $('#birth').val(),
            phone: $('#phone').val(),
            education: $('#education').val(),
            q_1: $('#q_1').val(),
            q_2: $('#q_2').val(),
            q_3: $('#q_3').val(),
            q_4: $('#q_4').val(),
            q_5: $('#q_5').val(),
            q_6: $('#q_6').val(),
            q_7: $('#q_7').val(),
            q_8: $('#q_8').val(),
            q_9: $('#q_9').val(),
            q_10: $('#q_10').val(),
            q_11: $('#q_11').val()
        };

        $.ajax({
            type: 'POST',
            url: '/mypage/surveys',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('설문지가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            name: $('#name').val(),
            gender: $('#gender').val(),
            email: $('#email').val(),
            birth: $('#birth').val(),
            phone: $('#phone').val(),
            education: $('#education').val(),
            q_1: $('#q_1').val(),
            q_2: $('#q_2').val(),
            q_3: $('#q_3').val(),
            q_4: $('#q_4').val(),
            q_5: $('#q_5').val(),
            q_6: $('#q_6').val(),
            q_7: $('#q_7').val(),
            q_8: $('#q_8').val(),
            q_9: $('#q_9').val(),
            q_10: $('#q_10').val(),
            q_11: $('#q_11').val()
        };

        var survey_id = $('#survey_id').val();

        $.ajax({
            type: 'PUT',
            url: '/mypage/surveys/'+survey_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('설문지가 수정되었습니다.');
            window.location.href = '/mypage';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var survey_id = $('#survey_id').val();

        $.ajax({
            type: 'DELETE',
            url: '/mypage/surveys/'+survey_id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('설문지가 삭제되었습니다.');
            window.location.href = '/mypage';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();