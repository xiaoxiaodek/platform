/**
 * Created by upsmart on 17-12-11.
 */
$(function() {
    let data;
    let template;
    const principal = {
        // items表示传入商务，运营，技术
        pick_search: function(role) {
            let self = this;
            $.ajax({
                url: '/user/selectUserByRole',
                data: JSON.stringify({"role": role}),
                type: 'POST',
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                success: function(e) {
                    console.log(e);
                    data = e.data;
                    if (data.length > 0) {
                        for (i in data) {
                            template += '<li><span>' + data[i] + '</span></li>';
                        }
                        template += "</ul></div>";
                        $('div.right_col').append(template);
                        $('.pick').on('click', function() {
                            self.pick_select(event, role);
                        });
                    }
                    //成功后取消绑定，避免多次点击
                    $('.principalPicker').off('click');
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });

        },

        pick_select: function(e, role) {
            console.log(e.target);
            e.preventDefault();
            if (e.target.tagName == 'span' || e.target.tagName == 'SPAN') {
                switch (role) {
                    case 2:
                        $('input[name="commerceUname"]').val(e.target.innerHTML);
                        checkRes1($('input[name="commerceUname"]').val());
                        break;
                    case 3:
                        $('input[name="onlineUname"]').val(e.target.innerHTML);
                        checkRes3($('input[name="onlineUname"]').val());
                        break;
                    case 4:
                        $('input[name="techUname"]').val(e.target.innerHTML);
                        checkRes2($('input[name="techUname"]').val());
                        break;
                    default:
                        break;
                };
                $('.pick').remove();
                this.init();
            }

        },

        init: function() {
            data = {};
            template = '<div class="pick"><ul>';
            let self = this;
            $('.principalPicker').on('click', function() {
                if (this.name != "" && this.name != undefined) {
                    switch (this.name) {
                        case 'commerceUname':
                            self.pick_search(2);
                            break;
                        case 'onlineUname':
                            self.pick_search(3);
                            break;
                        case 'techUname':
                            self.pick_search(4);
                            break;
                        default:
                            break;

                    }
                }
            });
        }
    };

    //初始化
    principal.init();
});
