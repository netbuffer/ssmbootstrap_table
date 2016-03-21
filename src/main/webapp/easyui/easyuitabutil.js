/**
 * 向tab中添加tab
 * @param domid tab容器domid
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag
 */
function addTabs(domid,event, treeId, treeNode, clickFlag) {
    if (!$("#"+domid).tabs('exists', treeNode.name)) {
        $("#"+domid).tabs('add', {
            id: treeId,
            title: treeNode.name,
            selected: true,
            href: treeNode.dataurl,
            closable: true
        });
    } else $("#"+domid).tabs('select', treeNode.name);
}

/**
 * 向tab中刪除tab
 * @param menu
 * @param type
 * @returns {Boolean}
 */
function closeTab(domid,menu, type) {
    var allTabs = $("#"+domid).tabs('tabs');
    var allTabtitle = [];
    $.each(allTabs, function (i, n) {
        var opt = $(n).panel('options');
        if (opt.closable)
            allTabtitle.push(opt.title);
    });
    var curTabTitle = $(menu).data("tabTitle");
    var curTabIndex = $("#"+domid).tabs("getTabIndex", $("#"+domid).tabs("getTab", curTabTitle));
    switch (type) {
        case 1:
            $("#"+domid).tabs("close", curTabIndex);
            return false;
            break;
        case 2:
            for (var i = 0; i < allTabtitle.length; i++) {
                $("#"+domid).tabs('close', allTabtitle[i]);
            }
            break;
        case 3:
            for (var i = 0; i < allTabtitle.length; i++) {
                if (curTabTitle != allTabtitle[i])
                    $("#"+domid).tabs('close', allTabtitle[i]);
            }
            $("#"+domid).tabs('select', curTabTitle);
            break;
        case 4:
            for (var i = curTabIndex; i < allTabtitle.length; i++) {
                $("#"+domid).tabs('close', allTabtitle[i]);
            }
            $("#"+domid).tabs('select', curTabTitle);
            break;
        case 5:
            for (var i = 0; i < curTabIndex-1; i++) {
                $("#"+domid).tabs('close', allTabtitle[i]);
            }
            $("#"+domid).tabs('select', curTabTitle);
            break;
        case 6: //刷新
            var panel = $("#"+domid).tabs("getTab", curTabTitle).panel("refresh");
            break;
    }
}