<%--
  Created by IntelliJ IDEA.
  User: ckzhang
  Date: 14-12-20
  Time: 下午1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
        <form action="account/signin" method="post" >



            <input type="text" name="email"/>
            <input type="text" name="password"/>

            <input type="text" name="source" value="web" style="display:none;"/>


            <input type="submit" value="upload"/>
        </form>


</body>
</html>
