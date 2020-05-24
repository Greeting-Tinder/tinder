<!doctype html>
<html lang="en" style="overflow-x: visible">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">
    <title>People list</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body style="overflow-x: auto">
<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#foreach people in likedPeoples>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img width="50" style="height:auto" src="${people.imgURL}"/>  
                                        </div>
                                    </td>
                                    <td class="align-middle">
                                        ${people.username}
                                    </td>
                                    <td class="align-middle">
                                        ${people.job}
                                    </td>
                                    <td class="align-middle">
                                        <form method="get" action="/messages/${people.id}">
                                            <button type="submit" class="btn btn-outline-success btn-block">
                                                <span>Chat</span>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </#foreach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>