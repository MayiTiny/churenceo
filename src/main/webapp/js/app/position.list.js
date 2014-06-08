define(function(require, exports, module) {
    var jQuery = require("jquery"),
        Backbone = require("backbone"),
        lavalamp = require("lavalamp");
    require("jquery.pagination");
    require("dressplug");
    require("share");
    require("dateformat");
    require("bas64");
    require("carouse");
    require("dialog");
    require("inputtopic");
    $(function() {
        var positonModel,
            positonCollectionList,
            positonList,
            positonView;
        positonModel = Backbone.Model.extend({
            defaults: {
                secondCategory: " ",
                workLocation: " ",
                departmentName: " "
            }
        }),
        tpl = require("tpl/tpl");
        positonCollectionList = Backbone.Collection.extend({
            model: positonModel,
            url: pageConfig.getPositionListUrl,
            clear: function() {
                _.each(this.models, function(item) {
                    item.unset(item.id);
                })
            }
        });
        positonList = new positonCollectionList();
        positonView = Backbone.View.extend({
            tagName: 'tbody',

            template: _.template(tpl.positionList),
            render: function() {
                var htmlObj = $(this.template(this.model.toJSON()));
                return htmlObj;
            }

        });

        var positonAppView = Backbone.View.extend({
            el: $("#J-position-list"),
            events: {
                "click a.more-icon": "showMore",
                "click .dress-box": "searchPosition",
                "click a.position-child": "showChildPosition",
                "click a.close": "hiddenChildPosition",
                "click a[data-check-type]": "checkSelect",
                "click .search-btn": "seachAction",
                "click a.share": "sharePositon",
                "click input.apply": "apply",
                "click input.favorite": "favorite"
            },
            getUrl: pageConfig.getPositionListUrl,
            positions: positonList,
            pageObj: null,
            searchParam: {
                pageSize: pageConfig.pageSize,
                t: Math.random()
            },
            // 绑定collection的相关事件
            initialize: function() {
                var _this = this,
                    pagePlug, url = window.location.search;
                //初始化keywrok
                if (url.indexOf("?") != -1) {
                    var str = url.substr(1),
                        strs = str.split("&");
                    if (strs.length > 0) {
                        for (i = 0, l = strs.length; i < l; i++) {
                            var args = strs[i].split("=");
                            if (args[0] && args[0] == 'keyWord') {
                                $("#keyword").val(decode64(args[1]));
                                _this.searchParam["keyWord"] = decode64(args[1]);
                            }
                        }
                    }
                }
                _this.positions.bind('add', this.addOne, this);
                // 调用fetch的时候触发reset                
                _this.positions.bind('reset', this.addAll, this);

                _this.positions.fetch({
                    url: _this.getUrl,
                    data: _this.searchParam,
                    success: function(o, reps) {
                        _this.noData(reps);
                        _this.pageObj = pagePlug = evaluatePage("J-pagination", function(page) {
                            $("#J-list-box").html("");
                            _this.searchParam["pageIndex"] = page;
                            _this.positions.fetch({
                                url: _this.getUrl,
                                type: "POST",
                                data: _this.searchParam,
                                success: function(o, reps) {
                                    _this.noData(reps);
                                    pagePlug.setPaging({
                                        total: reps.returnValue.totalRecord,
                                        page: page,
                                        pageSize: reps.returnValue.pageSize
                                    });
                                },
                                error: function(o, reps) {
                                    _this.fetchError(reps);
                                }
                            });
                        });
                        pagePlug.setPaging({
                            total: reps.returnValue.totalRecord,
                            page: 1,
                            pageSize: reps.returnValue.pageSize
                        });
                    },
                    error: function(o, reps) {
                        _this.fetchError(reps);
                    }
                });
            },
            addOne: function(employee) {
                employee.set({
                    "id": employee.get("id") || positonList.length
                });
                var view = new positonView({
                    model: employee
                });
                $("#J-list-box").append(view.render());
                $("#J-list-no-data").hide();

            },
            addAll: function() {
                this.positions.each(this.addOne);
            },
            seachAction: function(e) {
                var _this = this,
                    $target = $(e.target);
                _this.searchParam["keyWord"] = $("#keyword").val();
                _this.searchParam["location"] = $("#dress-check").val();
                _this.searchParam["second"] = $("#position-check").val();
                _this.searchParam["first"] = $("#position-first-check").val();
                _this.searchParam["pageIndex"] = 1;
                $target.attr("disabled", true);
                $("#J-list-box").html("");
                _this.positions.fetch({
                    url: _this.getUrl,
                    data: _this.searchParam,
                    type: "POST",
                    success: function(o, reps) {
                        $target.attr("disabled", false);
                        _this.noData(reps);
                        _this.pageObj.setPaging({
                            total: reps.returnValue.totalRecord,
                            page: 1,
                            pageSize: reps.returnValue.pageSize
                        });
                    },
                    error: function(o, reps) {
                        $target.attr("disabled", false);
                        _this.fetchError(reps);
                    }
                });
            },
            fetchError: function(reps) {
                var noData = $("#J-list-no-data"),
                    pageObj = $("#J-pagination");
                if (reps && reps.isSuccess == "false") {
                    noData.show().find("span").html("亲，抱歉系统超时！");
                } else {
                    noData.show().find("span").html("亲，抱歉系统超时！");
                }
                pageObj.hide();
                //bootbox.alert(reps.errors[0].msg,"error");
            },
            noData: function(reps) {
                var noData = $("#J-list-no-data"),
                    listObj = $("#J-list-box"),
                    pageObj = $("#J-pagination");

                if (reps.returnValue.totalRecord <= 0) {
                    noData.show().children("span").html("亲，没有搜索到相关应聘职位！");
                    listObj.html("");
                    pageObj.hide();
                } else {
                    noData.hide();
                    pageObj.show();
                }
            },
            showMore: function(e) {
                var target = e.target,
                    moreObj = $(target).closest("tr").next("tr");
                if (target.rel && target.rel == "1") {
                    moreObj.hide();
                    target.rel = "0";
                    target.className = "more-icon";
                } else {
                    moreObj.show();
                    target.rel = "1";
                    target.className = "more-icon down-icon";
                }

            },
            searchPosition: function(e) {
                var target = e.target,
                    _this = this;
                _this.searchParam["dress"] = target.rel;
                _this.positions.fetch({
                    url: _this.getUrl,
                    data: _this.searchParam,
                    success: function(o, reps) {
                        _this.noData(reps);
                        pagePlug.setPaging({
                            total: reps.returnValue.totalRecord,
                            page: page,
                            pageSize: reps.returnValue.pageSize
                        });
                    },
                    error: function(o, reps) {
                        _this.fetchError(reps);
                    }
                });

            },
            showChildPosition: function(e) {
                var target = e.target,
                    _this = this,
                    childPosition = $("#position-child div"),
                    index = $(target).attr("data-child-id") - 1,
                    hideObj = $("#position-first-check"),
                    secondHideObj = $("#position-check");
                if ($(target).attr("data-child-id") != "all") {
                    if ($(target).attr("data-show") && $(target).attr("data-show") == "1") {
                        $(target).removeClass("current");
                        childPosition.eq(index).hide();
                        childPosition.eq(index).children("a.current").each(function() {
                            _this.attachValue(secondHideObj, this.rel, "del");
                            $(this).removeClass("current");
                        });
                        this.attachValue(hideObj, target.rel, "del");
                        $(target).attr("data-show", "0");
                    } else {
                        target.className += " current";
                        childPosition.eq(index).show();
                        this.attachValue(hideObj, target.rel, "add");
                        $(target).attr("data-show", "1");
                    }

                }
                $(".search-btn").click();

            },
            hiddenChildPosition: function(e) {
                var _this = this,
                    target = e.target,
                    index = $(target).closest("div").index() + 1,
                    hideObj = $("#position-check"),
                    firstHideObj = $("#position-first-check");
                $("a[data-child-id=" + index + "]").removeClass("current");
                $(target).closest("div").hide();
                $(target).closest("div").children("a").removeClass("current");
                $(target).closest("div").children("a").each(function() {
                    _this.attachValue(hideObj, this.rel, "del");
                });
                _this.attachValue(firstHideObj, $("a[data-child-id=" + index + "]").attr("rel"), "del");
                $(".search-btn").click();
            },
            checkSelect: function(e) {
                var target = e.target,
                    rel = target.rel,
                    type = $(target).attr("data-check-type"),
                    hideObj = type == "dress" ? $("#dress-check") : $("#position-check");

                if (target.className.indexOf("current") >= 0) {
                    target.className = "";
                    this.attachValue(hideObj, rel, "del");
                    if ($(target).attr("data-del") == "1") {
                        $(target).remove();
                        $(".check-more-dress").attr("data-current", parseInt($(".check-more-dress").attr("data-current")) - 1);
                    }
                } else {
                    target.className = "current";
                    this.attachValue(hideObj, rel, "add");
                }
                $(".search-btn").click();
            },
            attachValue: function(hideObj, val, type) {
                var value = hideObj.val();
                var valueArr = [];
                var ifJson = arguments[3] && arguments[3] == true ? true : false;
                if ($.trim(value) != '') {
                    if (ifJson) {
                        //value = '['+value+']';
                        try {
                            valueArr = $.evalJSON(value);
                        } catch (e) {
                            valueArr = [];
                        }
                    } else {
                        valueArr = value.split(",");
                    }
                }
                if (type == "add") {
                    valueArr.push(val);
                } else {
                    var leng = valueArr.length;
                    if (leng > 0) {
                        for (var i = 0; i < leng; i++) {
                            if (ifJson) {
                                if (valueArr[i] != undefined && $.trim(val) == $.trim(valueArr[i]["id"])) {
                                    //valueArr.splice(i,1);
                                    valueArr[i]['del'] = 'true';
                                }
                            } else {
                                if ($.trim(val) == $.trim(valueArr[i])) {
                                    valueArr.splice(i, 1);
                                }
                            }
                        }
                    }
                }
                if (ifJson) {
                    hideObj.val($.toJSON(valueArr));
                } else {
                    hideObj.val(valueArr.join(","));
                }
            },
            sharePositon: function(e) {
                var target = e.target,
                    parentObj = $(target).parent();
                share(target.rel, parentObj.attr("data-share-title"), parentObj.attr("data-share-url"))
            },
            apply: function(e) {
                var target = e.target,
                    _this = $(target);
                var positionId = _this.attr("positionId");

                _this.attr("disabled", true);
                jQuery.ajax({
                    url: "/zhaopin/getApplyCount/doApplyCount.json?positionId=" + positionId + "&_tb_token_=" + pageConfig.token + "&t=" + Math.random(),
                    success: function(data) {
                        var json = jQuery.parseJSON(data);
                        _this.attr("disabled", false);
                        if (json.isSuccess == false && json.returnValue == 400) {
                            var loginBox = sysLogin({
                                loginDomain: pageConfig.loginDomain,
                                callBack: function(args) {
                                    if (args.action && (args.action == "hasLoginResult" || args.action == "loginResult")) {
                                        if (args.st) {
                                            //window.open("https://account.yunos.com/dologin.htm?st="+args.st);
                                            $.get(pageConfig.loginUrl, {
                                                st: args.st,
                                                appName: "hrjob"
                                            }, function(data) {
                                                if (data.isSuccess != true) {
                                                    sysAlert({
                                                        msg: data.exceptionDesc
                                                    });
                                                    return false;
                                                } else {
                                                    if (data.returnValue == "noResume") {
                                                        var href = "/zhaopin/resume_info.htm";
                                                        if(positionId && positionId > 0) {
                                                            href = "/zhaopin/resume_info.htm?positionId=" + positionId;
                                                        }
                                                        sysConfirm({
                                                            msg: "你还没有创建简历，现在就去。。。",
                                                            callBack: function() {
                                                                window.location.href = href
                                                            }
                                                        });
                                                    } else if (data.returnValue == "ModResume") {
                                                        sysAlert({
                                                            msg: "亲，如果您的联系方式有变，请尽快到个人中心更新您的简历联系方式哦！"
                                                        });
                                                    } else {
                                                        $(".search-btn").trigger("click");
                                                    }
                                                }

                                                $("#J-login").html(args.loginId + '&nbsp; | &nbsp;<a href="' + pageConfig.loginOut + '">退出</a>')
                                                loginBox.remove();

                                            })

                                        }
                                    }
                                }
                            });
                            return;
                        }
                        if (json.isSuccess == false && json.returnValue == 200) {
                            window.location.href = "/zhaopin/resumeInfo.htm?positionId="+positionId;
                            return;
                        }
                        if (json.isSuccess) {
                            var applyTimes = json.returnValue;
                            var count = 10 - applyTimes;
                            if (applyTimes >= 10) {
                                sysAlert({
                                    msg: "你己申请10个职位，本月不能再申请！"
                                });
                                return;
                            }

                            sysConfirm({
                                msg: "你己申请" + applyTimes + "个职位,本月你还能再申请" + count + "个，请慎重选择！",
                                callBack: function() {
                                    if (_this.hasClass('dreamer')) {
                                        var html = $(_.template(tpl.dreamer)());
                                        var textarea = html.find('textarea');
                                        textarea.on('blur change keyup paste', function(e) {
                                            var item = e.currentTarget;
                                            var it = $(item);
                                            var value = $(item).val();
                                            if (it.attr('required')) {
                                                var length = value.length;
                                                if (!length) {
                                                    it.addClass('has-error')
                                                    it.next('strong.error-msg').remove();
                                                    $('<strong>').addClass('error-msg').text(it.attr('data-errormessage-required')).insertAfter(it);
                                                } else {
                                                    it.next('strong.error-msg').remove();
                                                    it.removeClass('has-error')
                                                }
                                            }
                                            if(it.attr('data-max-length')){
                                                var length = value.length;
                                                var len = it.attr('data-max-length')
                                                if (length>len) {
                                                    it.addClass('has-error')
                                                    it.next('strong.error-msg').remove();
                                                    $('<strong>').addClass('error-msg').text(it.attr('data-maxlength-msg')).insertAfter(it);
                                                } else {
                                                    it.next('strong.error-msg').remove();
                                                    it.removeClass('has-error')
                                                }
                                            }
                                        })
                                        sysConfirm({
                                            msg: html,
                                            width: 514,
                                            height: 460,
                                            title: '',
                                            confirmTpl: '提交',
                                            cancelTpl: '放弃',
                                            cls: 'dreamer-popup',
                                            autoHide:false,
                                            callBack: function() {
                                                textarea.trigger('blur');
                                                if (html.find('.has-error').length) {
                                                    return false;
                                                }
                                                var data = html.serialize();
                                                jQuery.ajax({
                                                    url: "/zhaopin/socialApply/doApply.json?positionId=" + positionId + "&_tb_token_=" + pageConfig.token + "&t=" + Math.random(),
                                                    type: "POST",
                                                    data: data,
                                                    success: function(data) {
                                                        var json = jQuery.parseJSON(data);
                                                        if (json.isSuccess) {
                                                            if (json.returnValue == 200) {
                                                                window.location.href = "/zhaopin/resumeInfo.htm?positionId"+positionId;
                                                            }
                                                            _this.attr("class", "big-gray-button");
                                                            sysAlert({
                                                                msg: "申请成功！"
                                                            });
                                                            _this.val("已申请");
                                                        } else {
                                                            _this.attr("disabled", false);
                                                            sysAlert({
                                                                msg: json.exceptionDesc
                                                            });
                                                        }
                                                    }
                                                });
                                            }
                                        })
                                    } else {
                                        jQuery.ajax({
                                            url: "/zhaopin/socialApply/doApply.json?positionId=" + positionId + "&_tb_token_=" + pageConfig.token + "&t=" + Math.random(),
                                            success: function(data) {
                                                var json = jQuery.parseJSON(data);
                                                if (json.isSuccess) {
                                                    if (json.returnValue == 200) {
                                                        window.location.href = "/zhaopin/resumeInfo.htm?positionId" + positionId;
                                                    }
                                                    _this.attr("class", "big-gray-button");
                                                    sysAlert({
                                                        msg: "申请成功！"
                                                    });
                                                    _this.val("已申请");
                                                } else {
                                                    _this.attr("disabled", false);
                                                    sysAlert({
                                                        msg: json.exceptionDesc
                                                    });
                                                }
                                            }
                                        });
                                    }
                                }
                            });


                        } else {
                            _this.attr("disabled", false);
                            sysAlert({
                                msg: json.exceptionDesc
                            });
                        }
                    }
                });
            },
            favorite: function(e) {
                var target = e.target,
                    _this = $(target);
                var positionId = _this.attr("positionId");

                jQuery.ajax({
                    url: "/zhaopin/socialFavorite/doFavorite.json?positionId=" + positionId + "&_tb_token_=" + pageConfig.token + "&t=" + Math.random(),
                    success: function(data) {
                        var json = {};
                        try {
                            json = eval("(" + data + ")");
                        } catch (e) {};

                        if (json.isSuccess == false && json.returnValue == 400) {
                            var loginBox = sysLogin({
                                loginDomain: pageConfig.loginDomain,
                                callBack: function(args) {
                                    if (args.action && (args.action == "hasLoginResult" || args.action == "loginResult")) {
                                        if (args.st) {
                                            //window.open("https://account.yunos.com/dologin.htm?st="+args.st);
                                            $.get(pageConfig.loginUrl, {
                                                st: args.st,
                                                appName: "hrjob"
                                            }, function(data) {
                                                if (data.isSuccess != true) {
                                                    sysAlert({
                                                        msg: data.exceptionDesc
                                                    });
                                                    return false;
                                                } else {
                                                    if (data.returnValue == "noResume") {
                                                        sysConfirm({
                                                            msg: "你还没有创建简历，现在就去。。。",
                                                            callBack: function() {
                                                                window.location.href = "/zhaopin/resume_info.htm"
                                                            }
                                                        });
                                                    } else if (data.returnValue == "ModResume") {
                                                        sysAlert({
                                                            msg: "亲，如果您的联系方式有变，请尽快到个人中心更新您的简历联系方式哦！"
                                                        });
                                                    } else {
                                                        $(".search-btn").trigger("click");
                                                    }
                                                }
                                                $("#J-login").html(args.loginId + '&nbsp; | &nbsp;<a href="' + pageConfig.loginOut + '">退出</a>')
                                                loginBox.remove();
                                            })

                                        }
                                    }
                                }
                            });
                            return;
                        }
                        if (json.isSuccess) {
                            _this.attr("class", "big-gray-button");
                            sysAlert({
                                msg: "收藏成功！"
                            });
                            _this.val("已收藏");
                        } else {
                            _this.attr("disabled", false);
                            sysAlert({
                                msg: json.exceptionDesc
                            });
                        }
                    }
                });
            }
        });

        var positionObj = new positonAppView();
        $(".check-more-dress").selectDress({
            ifCheckOne: false,
            maxCity: 10,
            callBack: function(city) {
                if (city && city.length > 0) {
                    for (var i = 0, l = city.length; i < l; i++) {
                        if ($("a[rel=" + city[i]["name"] + "]").length > 0) {
                            return;
                        }
                        $(".check-more-dress").attr("data-current", parseInt($(".check-more-dress").attr("data-current")) + 1);
                        $("#search-dress").append("<a href='javascript:;' data-del='1' data-check-type='dress' class='current' rel='" + city[i]["name"] + "'>" + city[i]["name"] + "  </a>");
                        positionObj.attachValue($("#dress-check"), city[i]["name"], "add");
                    }
                    $(".search-btn").trigger("click");
                }

            }
        });

        $("#J_Slidebox").imgScroll({
            type: 'gradual',
            zoomTime: 4000,
            timeout: 1000
        });
        seajs.use("app/enter.search");
        $("input.search-text").inputTopic();
        lavalamp.init("menu", "slide");
    });

    function evaluatePage(id, callback) {
        //初始化分页控件
        var pageObj = $("#" + id).pagination({
            page: 1,
            size: pageConfig.pageSize,
            redirectUrl: '#page/',
            onPageChanged: function(page) {
                if (typeof callback == "function") {
                    callback(page);
                }
            }

        });
        return pageObj.data("pagination");
    }

});
